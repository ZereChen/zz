package tools;

import client.Class2;
import client.MapleCharacter;
import client.inventory.IItem;
import java.io.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileoutputUtil {

    private static final SimpleDateFormat sdfT = new SimpleDateFormat("yyyy��MM��dd��HH�rmm��ss��");
    // Logging output file
    public static final String fixdam_mg = "����˼�¼��Ϣ/ħ���˺�����.txt",
            fixdam_ph = "����˼�¼��Ϣ/�����˺�����.txt",
            MobVac_log = "����˼�¼��Ϣ/��ҽ�ɫ����.txt",
            hack_log = "����˼�¼��Ϣ/����ʹ�����.txt",
            ban_log = "����˼�¼��Ϣ/ϵͳ�Զ����.txt",
            Acc_Stuck = "����˼�¼��Ϣ/���˺�.txt",
            Login_Error = "����˼�¼��Ϣ/��¼����.txt",
            //Timer_Log = "Log_Timer_Except.rtf",
            //MapTimer_Log = "Log_MapTimer_Except.rtf",
            IP_Log = "����˼�¼��Ϣ/�˺�IP.txt",
            //GMCommand_Log = "Log_GMCommand.rtf",
            Zakum_Log = "����˼�¼��Ϣ/��ս����.txt",
            Horntail_Log = "����˼�¼��Ϣ/��ս��������.txt",
            Pinkbean_Log = "����˼�¼��Ϣ/��սƷ����.txt",
            ScriptEx_Log = "����˼�¼��Ϣ/�ű��쳣.txt",
            PacketEx_Log = "����˼�¼��Ϣ/����쳣.txt" 

            + "";
    // End
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat sdf_ = new SimpleDateFormat("yyyy-MM-dd");

    public static void logToZev(final String path, final String content) {
        Class2.Pf(path, content);
    }

    public static void logToFile_chr(MapleCharacter chr, final String file, final String msg) {
        logToFile(file, "\r\n" + FileoutputUtil.CurrentReadable_Time() + " �˺š�" + chr.getClient().getAccountName() + "����ɫ����" + chr.getName() + "���ȼ���" + chr.getLevel() + "����ͼ��" + chr.getMapId() + "��" + msg, false);
    }//��(" + chr.getId() + ")��

    public static void logToFile(final String file, final String msg) {
        logToFile(file, msg, false);
    }

    public static void logToFile(final String file, final String msg, boolean notExists) {
        FileOutputStream out = null;
        try {
            File outputFile = new File(file);
            if (outputFile.exists() && outputFile.isFile() && outputFile.length() >= 10 * 1024 * 1000) {
                outputFile.renameTo(new File(file.substring(0, file.length() - 4) + "_" + sdfT.format(Calendar.getInstance().getTime()) + file.substring(file.length() - 4, file.length())));
                outputFile = new File(file);
            }
            if (outputFile.getParentFile() != null) {
                outputFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(file, true);
            if (!out.toString().contains(msg) || !notExists) {
                OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
                osw.write(msg);
                osw.flush();
            }
        } catch (IOException ess) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignore) {
            }
        }
    }

    public static void packetLog(String file, String msg) {
        boolean notExists = false;
        FileOutputStream out = null;
        try {
            File outputFile = new File(file);
            if (outputFile.exists() && outputFile.isFile() && outputFile.length() >= 1024 * 1000) {
                outputFile.renameTo(new File(file.substring(0, file.length() - 4) + "_" + sdfT.format(Calendar.getInstance().getTime()) + file.substring(file.length() - 4, file.length())));
                outputFile = new File(file);
            }
            if (outputFile.getParentFile() != null) {
                outputFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(file, true);
            if (!out.toString().contains(msg) || !notExists) {
                OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
                osw.write(msg);
                osw.flush();
            }
        } catch (IOException ess) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignore) {
            }
        }
    }

    public static void log(final String file, final String msg) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file, true);
            out.write(("\n------------------------ " + CurrentReadable_Time() + " ------------------------\n").getBytes());
            out.write(msg.getBytes());
        } catch (IOException ess) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignore) {
            }
        }
    }

    public static void outputFileError(final String file, final Throwable t) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file, true);
            out.write(("\n------------------------ " + CurrentReadable_Time() + " ------------------------\n").getBytes());
            out.write(getString(t).getBytes());
        } catch (IOException ess) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignore) {
            }
        }
    }

    public static String CurrentReadable_Date() {
        return sdf_.format(Calendar.getInstance().getTime());
    }

    public static String CurrentReadable_Time() {
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static String getString(final Throwable e) {
        String retValue = null;
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            retValue = sw.toString();
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (sw != null) {
                    sw.close();
                }
            } catch (IOException ignore) {
            }
        }
        return retValue;
    }

    public static String NowTime() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//���Է�����޸����ڸ�ʽ    
        String hehe = dateFormat.format(now);
        return hehe;
    }

    public static Object logToFile(String ����˼�¼��Ϣ�ٱ���齱��¼txt, String string, String _���˳���ʵ����̫������, IItem item, byte b, int channel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
