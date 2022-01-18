/*
�����˺���װ��
 */
package abc;

import constants.*;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class �̳Ǽ���ļ� {

    private static �̳Ǽ���ļ� instance = null;
    private static boolean CANLOG;
    private Properties itempb_cfg;
    private String id;
    private String idz;
    private static Logger log = LoggerFactory.getLogger(�̳Ǽ���ļ�.class);

    public �̳Ǽ���ļ�() {
        itempb_cfg = new Properties();
        try {
            InputStreamReader is = new FileReader("�����ļ�\\�̳Ǽ��.ini");
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
        �̳Ǽ���ļ�.CANLOG = CANLOG;
    }

    public static �̳Ǽ���ļ� getInstance() {
        if (instance == null) {
            instance = new �̳Ǽ���ļ�();
        }
        return instance;
    }
}
