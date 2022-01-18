/*
�����˺���װ��
 */
package abc;

import abc.*;
import constants.*;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ע������� {

    private static ע������� instance = null;
    private static boolean CANLOG;
    private Properties itempb_cfg;
    private String QQ;

    private static Logger log = LoggerFactory.getLogger(ע�������.class);

    public ע�������() {
        itempb_cfg = new Properties();
        try {
            InputStreamReader is = new FileReader("�����ļ�\\�˺Ű�����.ini");
            itempb_cfg.load(is);
            is.close();
            QQ = itempb_cfg.getProperty("QQ");
        } catch (Exception e) {
            //String QQ = "71447500";
            log.error("Could not configuration", e);
        }
    }

    public String getQQ() {
        return QQ;
    }

    public boolean isCANLOG() {
        return CANLOG;
    }

    public void setCANLOG(boolean CANLOG) {
        ע�������.CANLOG = CANLOG;
    }

    public static ע������� getInstance() {
        if (instance == null) {
            instance = new ע�������();
        }
        return instance;
    }
}
