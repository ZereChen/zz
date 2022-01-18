 /*
���ݿ�
 */
package database;

import static abc.Game.˵��;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;
import javazoom.jl.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gui.δ����.���ݿ���ʾ;
import server.Randomizer;

public class DatabaseConnection {

    private static final HashMap<Integer, ConWrapper> connections = new HashMap();
    private final static Logger log = LoggerFactory.getLogger(DatabaseConnection.class);
    private static String dbDriver = "", dbUrl = "", dbUser = "", dbPass = "";
    private static final long connectionTimeOut = 30 * 60 * 1000;
    private static final ReentrantLock lock = new ReentrantLock();
    public static ���ݿ���ʾ ���ݿ���ʾ;

    public static int getConnectionsCount() {
        return connections.size();
    }

    public static void close() {
        try {
            Thread cThread = Thread.currentThread();
            Integer threadID = (int) cThread.getId();
            ConWrapper ret = connections.get(threadID);
            if (ret != null) {
                Connection c = ret.getConnection();
                if (!c.isClosed()) {
                    c.close();
                }
                lock.lock();
                try {
                    connections.remove(threadID);
                } finally {
                    lock.unlock();
                }
            }

        } catch (SQLException ex) {
        }
    }
    private static final String[] ��ʾ��Ϣ = {
        "�����������˺�ʱ��û�з�Ӧ���ǿ��������ݿ�û������"
    };

    public static Connection getConnection() {
        //System.out.println("С��ʾ:" + ��ʾ��Ϣ[Randomizer.nextInt(��ʾ��Ϣ.length)]);
        if (!isInitialized()) {
            //Զ���������ݿ�();
            InitDB();
        }
        Thread cThread = Thread.currentThread();
        Integer threadID = (int) cThread.getId();
        ConWrapper ret;

        ret = connections.get(threadID);

        if (ret == null) {
            Connection retCon = connectToDB();
            ret = new ConWrapper(threadID, retCon);
            lock.lock();
            try {
                connections.put(threadID, ret);
            } finally {
                lock.unlock();
            }
        }

        Connection c = ret.getConnection();
        try {
            if (c.isClosed()) {
                Connection retCon = connectToDB();
                lock.lock();
                try {
                    connections.remove(threadID);
                    connections.put(threadID, ret);
                } finally {
                    lock.unlock();
                }
                ret = new ConWrapper(threadID, retCon);
            }
        } catch (Exception e) {
        } finally {
        }

        return ret.getConnection();
    }

    public static Connection getConnection2() {
        if (!isInitialized()) {
            Զ���������ݿ�();
        }
        Thread cThread = Thread.currentThread();
        Integer threadID = (int) cThread.getId();
        ConWrapper ret;

        ret = connections.get(threadID);

        if (ret == null) {
            Connection retCon = connectToDB();
            ret = new ConWrapper(threadID, retCon);
            lock.lock();
            try {
                connections.put(threadID, ret);
            } finally {
                lock.unlock();
            }
        }
        Connection c = ret.getConnection();
        try {
            if (c.isClosed()) {
                Connection retCon = connectToDB();
                lock.lock();
                try {
                    connections.remove(threadID);
                    connections.put(threadID, ret);
                } finally {
                    lock.unlock();
                }
                ret = new ConWrapper(threadID, retCon);
            }
        } catch (Exception e) {
        } finally {
        }

        return ret.getConnection();
    }

    static class ConWrapper {

        private final int tid;
        private long lastAccessTime;
        private Connection connection;

        public ConWrapper(int tid, Connection con) {
            this.tid = tid;
            this.lastAccessTime = System.currentTimeMillis();
            this.connection = con;
        }

        public boolean close() {
            boolean ret = false;

            if (connection == null) {
                ret = false;
            } else {

                try {
                    lock.lock();
                    try {
                        if (expiredConnection() || this.connection.isValid(10)) {
                            try {
                                this.connection.close();
                                ret = true;
                            } catch (SQLException e) {
                                ret = false;
                            }
                        }
                        connections.remove(tid);
                    } finally {
                        lock.unlock();
                    }
                } catch (SQLException ex) {
                    ret = false;
                }
            }
            return ret;
        }

        public Connection getConnection() {
            if (expiredConnection()) {
                try {
                    connection.close();
                } catch (SQLException err) {
                }
                this.connection = connectToDB();
            }
            lastAccessTime = System.currentTimeMillis();
            return this.connection;
        }

        public boolean expiredConnection() {
            return System.currentTimeMillis() - lastAccessTime >= connectionTimeOut;
        }
    }

    private static Connection connectToDB() {

        try {
            Properties props = new Properties();
            props.put("user", dbUser);
            props.put("password", dbPass);
            props.put("autoReconnect", "true");
            props.put("characterEncoding", "UTF8");
            props.put("connectTimeout", "2000000");
            props.put("serverTimezone", "Asia/Taipei");
            Connection con = DriverManager.getConnection(dbUrl, props);

            PreparedStatement ps;
            ps = con.prepareStatement("SET time_zone = '+08:00'");
            ps.execute();
            ps.close();

            return con;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public static boolean isInitialized() {
        return !dbUser.equals("");
    }

    public static boolean ������ݿ��Ƿ�����() {

        ByteArrayOutputStream baos = null;
        InputStream os = null;
        String s = "";
        try {
            Process p = Runtime.getRuntime().exec("cmd /c tasklist");
            baos = new ByteArrayOutputStream();
            final Runtime runtime = Runtime.getRuntime();
            Properties �O���n = System.getProperties();
            Process process = null;
            os = p.getInputStream();
            byte b[] = new byte[256];
            while (os.read(b) > 0) {
                baos.write(b);
            }
            s = baos.toString();
            //˵��();
            //�Ƽ�();
            if (s.indexOf("mysqld.exe") >= 0) {
            } else {
                final String cmd = "rundll32 url.dll FileProtocolHandler file:" + �O���n.getProperty("user.dir") + "\\MYSQL";
                try {
                    process = runtime.exec(cmd);
                } catch (final Exception e) {
                    System.out.println("Error exec!");
                }
                System.out.println("δ�������ݿ⣬���ֶ��������ݿ⣬�����������");
                System.out.println("���ݿ��ַ��" + �O���n.getProperty("user.dir") + "\\MYSQL\\�������ݿ�.exe");
                String ��Ч���� = �O���n.getProperty("user.dir") + "\\dist\\lib\\data base.mp3";
                try {
                    BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(��Ч����));
                    Player player = new Player(buffer);
                    player.play();
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void ���ݿ���ʾ() {
        if (���ݿ���ʾ != null) {
            ���ݿ���ʾ.dispose();
        }
        ���ݿ���ʾ = new ���ݿ���ʾ();
        ���ݿ���ʾ.setVisible(true);
    }

    public static void InitDB() {
        //������ݿ��Ƿ�����();
        dbDriver = "com.mysql.jdvc.Driver";
        String db = "zevms";
        String ip = "127.0.0.1";
        String port = "3306";
        dbUser = "root";
        dbPass = "zevms";
        dbUrl = "jdbc:mysql://" + ip + ":" + port + "/" + db + "?autoReconnect=true&characterEncoding=UTF8&maxReconnects=2147483640&connectTimeout=3600 000&socketTimeout=3600000";//+ "?autoReconnect=true&characterEncoding=UTF8&connectTimeout=120000000";
//        String db = ServerProperties.getProperty("database", "twms");
//        String ip = ServerProperties.getProperty("ip", "localhost");
//        String port = ServerProperties.getProperty("port", "3306");
//        dbUrl = "jdbc:mysql://" + ip + ":" + port + "/" + db + "?autoReconnect=true&characterEncoding=UTF8&maxReconnects=2147483640&connectTimeout=3600000&socketTimeout=3600000";//+ "?autoReconnect=true&characterEncoding=UTF8&connectTimeout=120000000";
//        dbUser = ServerProperties.getProperty("user", "twms113");
//        dbPass = ServerProperties.getProperty("password", "twms113");
    }

    public static void Զ���������ݿ�() {
        //System.err.println("Զ���������ݿ�");
        dbDriver = "com.mysql.jdvc.Driver";
        String db = "zevms";//ZEVMSms
        String ip = "123.207.53.97";
        String port = "3306";
        dbUser = "root";
        dbPass = "ZEVMSms";//��Ȩ�˺�
        dbUrl = "jdbc:mysql://" + ip + ":" + port + "/" + db + "?autoReconnect=true&characterEncoding=UTF8&maxReconnects=2147483640&connectTimeout=3600&socketTimeout=3600000";//+ "?autoReconnect=true&characterEncoding=UTF8&connectTimeout=120000000";
    }

    public static void closeTimeout() {
        int i = 0;
        lock.lock();
        List<Integer> keys = new ArrayList(connections.keySet());
        try {
            for (Integer tid : keys) {
                ConWrapper con = connections.get(tid);
                if (con.close()) {
                    i++;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void closeAll() {
        synchronized (connections) {
            for (ConWrapper con : connections.values()) {
                try {
                    con.connection.close();
                } catch (SQLException ex) {
                }
            }
        }
    }
    public final static Runnable CloseSQLConnections = new Runnable() {

        @Override
        public void run() {
            DatabaseConnection.closeTimeout();
        }
        
    };
}
