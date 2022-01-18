/*
�̳�������Ʒ
 */
package abc;

import constants.*;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OtherSettings2 {

    private static OtherSettings2 instance = null;
    private static boolean CANLOG;
    private Properties itempb_cfg;
    private String itempb_id[];
    private String itemjy_id[];
    private String itemgy_id[];
    //private String ��������[];
    private String mappb_id[];
    private static Logger log = LoggerFactory.getLogger(OtherSettings2.class);

    public OtherSettings2() {
        itempb_cfg = new Properties();
        try {
            InputStreamReader is = new FileReader("�̳Ǽ��2.ini");//�����̳���Ʒ
            itempb_cfg.load(is);
            is.close();
            itempb_id = itempb_cfg.getProperty("ID").split(",");
            itemjy_id = itempb_cfg.getProperty("ID2").split(",");
            //itemgy_id = itempb_cfg.getProperty("gysj", "0").split(",");
            //�������� = itempb_cfg.getProperty("pbsh", "0").split(",");
        } catch (Exception e) {
            log.error("Could not configuration", e);
        }
    }

    public String[] getItempb_id() {
        return itempb_id;
    }

    public String[] getItemgy_id() {
        return itemgy_id;
    }

    /* public String[] ��������() {a 
        return ��������;
    }*/
    public String[] getItemjy_id() {
        return itemjy_id;
    }

    public String[] getMappb_id() {
        return mappb_id;
    }

    public boolean isCANLOG() {
        return CANLOG;
    }

    public void setCANLOG(boolean CANLOG) {
        OtherSettings2.CANLOG = CANLOG;
    }

    public static OtherSettings2 getInstance() {
        if (instance == null) {
            instance = new OtherSettings2();
        }
        return instance;
    }
}

///*
//�̳�������Ʒ
//*/
//package constants;
//
//import java.io.FileReader;
//import java.io.InputStreamReader;
//import java.util.Properties;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class OtherSettings {
//
//    private static OtherSettings instance = null;
//    private static boolean CANLOG;
//    private Properties itempb_cfg;
//    private String itempb_id[];
//    /*private String itemjy_id[];
//    private String itemgy_id[];
//    //private String ��������[];
//    private String mappb_id[];*/
//    private static Logger log = LoggerFactory.getLogger(OtherSettings.class);
//
//    public OtherSettings() {
//        itempb_cfg = new Properties();
//        try {
//            InputStreamReader is = new FileReader("ZEVMS.ini");
//            itempb_cfg.load(is);
//            is.close();
//            itempb_id = itempb_cfg.getProperty("������ʾ").split(",");
//            /**itemjy_id = itempb_cfg.getProperty("cashjy", "0").split(",");
//            itemgy_id = itempb_cfg.getProperty("������ʾ", "0").split(",");*/
//            //�������� = itempb_cfg.getProperty("pbsh", "0").split(",");
//        } catch (Exception e) {
//            log.error("Could not configuration", e);
//        }
//    }
//
//    public String[] getItempb_id() {
//        return itempb_id;
//    }
//
////    public String[] getItemgy_id() {
////        return itemgy_id;
////    }
////
////   /* public String[] ��������() {
////        return ��������;
////    }*/
////
////    public String[] getItemjy_id() {
////        return itemjy_id;
////    }
////
////    public String[] getMappb_id() {
////        return mappb_id;
////    }
//
//    public boolean isCANLOG() {
//        return CANLOG;
//    }
//
//    public void setCANLOG(boolean CANLOG) {
//        OtherSettings.CANLOG = CANLOG;
//    }
//
//    public static OtherSettings getInstance() {
//        if (instance == null) {
//            instance = new OtherSettings();
//        }
//        return instance;
//    }
//}
