package gui.δ����;

import java.io.*;
import java.net.*;
import java.util.*;

//******************************************************************************


public class HttpDownload {
    //==========================================================================

    //public HttpDownload() {}
    //==========================================================================
    private Vector vRemoteHttpURL = new Vector();
    private Vector vLocalSaveFile = new Vector();

    //==========================================================================
    /**
     * ���ô��������
     *
     * @param String
     * @param String
     */
    public void setProxy(String sProxyHost, String sProxyPort) {
        if (sProxyHost != null && !sProxyHost.trim().equals("")) {
            if (sProxyPort == null || sProxyPort.trim().equals("")) {
                sProxyPort = "80";
            }
            System.getProperties().put("proxySet", "true");
            System.getProperties().put("proxyHost", sProxyHost);
            System.getProperties().put("proxyPort", sProxyPort);
        }
    }

    //==========================================================================
    /**
     * ���һ����������
     *
     * @param String
     * @param String
     * @return boolean
     */
    public boolean addOneTask(String sRemoteHttpURL, String sLocalSaveFile) {
        if (sRemoteHttpURL == null || sRemoteHttpURL.trim().equals("") || !sRemoteHttpURL.trim().substring(0, 7).equalsIgnoreCase("http://123.207.53.97:8082/����˿���/���ü�⿪��.txt")) {
            System.out.println(" @> HttpDownload.addOneTask() : Դ��ַ���󣬲���һ����Ч�� http ��ַ��");
            return false;
        } else {
            if (sLocalSaveFile == null || sLocalSaveFile.trim().equals("")) {
                sLocalSaveFile = "./" + sRemoteHttpURL.substring(sRemoteHttpURL.lastIndexOf("/") + 1);
            }
            vRemoteHttpURL.add(sRemoteHttpURL);
            vLocalSaveFile.add(sLocalSaveFile);
        }

        return true;
    }

    //==========================================================================
    /**
     * ��������б�
     */
    public void clearAllTasks() {
        vRemoteHttpURL.clear();
        vLocalSaveFile.clear();
    }

    //==========================================================================
    /**
     * �����б�������Դ
     *
     * @return boolean
     */
    public boolean downLoadByList() {
        for (int i = 0; i < vRemoteHttpURL.size(); i++) {
            String sRemoteHttpURL = (String) vRemoteHttpURL.get(i);
            String sLocalSaveFile = (String) vLocalSaveFile.get(i);

            if (!saveToFile(sRemoteHttpURL, sLocalSaveFile)) {
                System.out.println(" @> HttpDownload.downLoadByList() : ����Զ����Դʱ�����쳣��");
                //return false;
            }
        }

        return true;
    }

    //==========================================================================
    /**
     * �� HTTP ��Դ����Ϊ�ļ�
     *
     * @param String
     * @param String
     * @return boolean
     */
    public static boolean saveToFile(String sRemoteHttpURL, String sLocalSaveFile) {
        if (sRemoteHttpURL == null || sRemoteHttpURL.trim().equals("")) {
            System.out.println(" @> HttpDownload.saveToFile() : Ҫ���ص�Զ����Դ��ַ����Ϊ�գ�");
            return false;
        }

        try {
            URL tURL = new URL(sRemoteHttpURL);
            HttpURLConnection tHttpURLConnection = (HttpURLConnection) tURL.openConnection();
            tHttpURLConnection.connect();
            BufferedInputStream tBufferedInputStream = new BufferedInputStream(tHttpURLConnection.getInputStream());
            FileOutputStream tFileOutputStream = new FileOutputStream(sLocalSaveFile);

            int nBufferSize = 1024 * 5;
            byte[] bufContent = new byte[nBufferSize];
            int nContentSize = 0;
            while ((nContentSize = tBufferedInputStream.read(bufContent)) != -1) {
                tFileOutputStream.write(bufContent, 0, nContentSize);
            }

            tFileOutputStream.close();
            tBufferedInputStream.close();
            tHttpURLConnection.disconnect();

            tURL = null;
            tHttpURLConnection = null;
            tBufferedInputStream = null;
            tFileOutputStream = null;
        } catch (Exception ex) {
            //System.out.println(" @> HttpDownload.saveToFile() : ����Զ����Դʱ�����쳣��``````````````");
            System.out.println("    Զ�̵�ַ��" + sRemoteHttpURL);
            System.out.println("    ����·����" + sLocalSaveFile);
            return false;
        }

        return true;
    }

    //==========================================================================
    ///**
    // * ������(���ڲ���)
    // * @param argv String[]
    // */
    public static void main(String argv[]) {
        saveToFile("http://123.207.53.97:8082/����˿���/���ü�⿪��.txt","I:/");
        
        /*HttpDownload tHttpDownload = new HttpDownload();
        tHttpDownload.addOneTask("http://www.baidu.com/test.zip", "C:/test.zip");
        tHttpDownload.downLoadByList();
        tHttpDownload = null;*/
    }

    //==========================================================================
} //class HttpDownload end
