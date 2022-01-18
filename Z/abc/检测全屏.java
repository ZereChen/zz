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

public class ���ȫ�� {

    private static ���ȫ�� instance = null;
    private static boolean CANLOG;
    private Properties itempb_cfg;
    private String qp1[];
    private String qp2[];
    private String qp3[];
    private String qp4[];
    private String qp5[];

    private static Logger log = LoggerFactory.getLogger(���ȫ��.class);

    public ���ȫ��() {
        itempb_cfg = new Properties();
        try {
            InputStreamReader is = new FileReader("Load\\Bin\\��Ǵ���Ba.ini");
            itempb_cfg.load(is);
            is.close();
            qp1 = itempb_cfg.getProperty("qp1").split(",");
            qp2 = itempb_cfg.getProperty("qp2").split(",");
            qp3 = itempb_cfg.getProperty("qp3").split(",");
            qp4 = itempb_cfg.getProperty("qp4").split(",");
            qp5 = itempb_cfg.getProperty("qp5").split(",");

        } catch (Exception e) {
            log.error("Could not configuration", e);
        }
    }

    public String[] getqp1() {
        return qp1;
    }

    public String[] getqp2() {
        return qp2;
    }

    public String[] getqp3() {
        return qp3;
    }

    public String[] getqp4() {
        return qp4;
    }

    public String[] getqp5() {
        return qp5;
    }

   

    public boolean isCANLOG() {
        return CANLOG;
    }

    public void setCANLOG(boolean CANLOG) {
        ���ȫ��.CANLOG = CANLOG;
    }

    public static ���ȫ�� getInstance() {
        if (instance == null) {
            instance = new ���ȫ��();
        }
        return instance;
    }
}
