/*
�����˺���װ��
 */
package abc;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ��Ʒ������� {

    private static ��Ʒ������� instance = null;
    private static boolean CANLOG;
    private Properties itempb_cfg;
    private String id;
    private String idz;
    private static Logger log = LoggerFactory.getLogger(��Ʒ�������.class);

    public ��Ʒ�������() {
        itempb_cfg = new Properties();
        try {
            InputStreamReader is = new FileReader("�����ļ�\\��Ʒ�������.ini");
            itempb_cfg.load(is);
            is.close();
            id = itempb_cfg.getProperty("id");
            idz = itempb_cfg.getProperty("id2");
        } catch (Exception e) {
            log.error("Could not configuration", e);
        }
    }

    public String getid() {
        return id;
    }

    public String getidz() {
        return idz;
    }

    public boolean isCANLOG() {
        return CANLOG;
    }

    public void setCANLOG(boolean CANLOG) {
        ��Ʒ�������.CANLOG = CANLOG;
    }

    public static ��Ʒ������� getInstance() {
        if (instance == null) {
            instance = new ��Ʒ�������();
        }
        return instance;
    }
}
