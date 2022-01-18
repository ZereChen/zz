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

public class �ؼ������� {

    private static �ؼ������� instance = null;
    private static boolean CANLOG;
    private Properties itempb_cfg;
    private String PM;
    private String PMM;

    private static Logger log = LoggerFactory.getLogger(�ؼ�������.class);

    public �ؼ�������() {
        itempb_cfg = new Properties();
        try {
            InputStreamReader is = new FileReader("�����ļ�\\�ؼ�������.ini");
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
        �ؼ�������.CANLOG = CANLOG;
    }

    public static �ؼ������� getInstance() {
        if (instance == null) {
            instance = new �ؼ�������();
        }
        return instance;
    }
}
