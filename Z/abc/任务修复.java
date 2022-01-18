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

public class �����޸� {

    private static �����޸� instance = null;
    private static boolean CANLOG;
    private Properties itempb_cfg;
    private String PM;
    private String PMM;

    private static Logger log = LoggerFactory.getLogger(�����޸�.class);

    public �����޸�() {
        itempb_cfg = new Properties();
        try {
            InputStreamReader is = new FileReader("scripts\\zevms\\QUEST.ini");
            itempb_cfg.load(is);
            is.close();
            PM = itempb_cfg.getProperty("PM");
            PMM = itempb_cfg.getProperty("PMM");
        } catch (Exception e) {
            log.error("Could not configuration", e);
        }
    }

    public String getPM() {
        return PM;
    }

    public String getPMM() {
        return PMM;
    }

    public boolean isCANLOG() {
        return CANLOG;
    }

    public void setCANLOG(boolean CANLOG) {
        �����޸�.CANLOG = CANLOG;
    }

    public static �����޸� getInstance() {
        if (instance == null) {
            instance = new �����޸�();
        }
        return instance;
    }
}
