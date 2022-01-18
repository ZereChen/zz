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

public class ��Ļ�ؼ��� {

    private static ��Ļ�ؼ��� instance = null;
    private static boolean CANLOG;
    private Properties itempb_cfg;
    private String PM;

    private static Logger log = LoggerFactory.getLogger(��Ļ�ؼ���.class);

    public ��Ļ�ؼ���() {
        itempb_cfg = new Properties();
        try {
            InputStreamReader is = new FileReader("�����ļ�\\�����ļ�\\��Ļ�ؼ���.ini");
            itempb_cfg.load(is);
            is.close();
            PM = itempb_cfg.getProperty("PM");
        } catch (Exception e) {
            log.error("Could not configuration", e);
        }
    }

    public String getPM() {
        return PM;
    }

    public boolean isCANLOG() {
        return CANLOG;
    }

    public void setCANLOG(boolean CANLOG) {
        ��Ļ�ؼ���.CANLOG = CANLOG;
    }

    public static ��Ļ�ؼ��� getInstance() {
        if (instance == null) {
            instance = new ��Ļ�ؼ���();
        }
        return instance;
    }
}
