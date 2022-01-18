package gui.ͼƬ.xiazai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;  
import java.io.RandomAccessFile;  
import java.net.URL;  
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JProgressBar;
  
public class MultiDown {
	
    public void MultiDownload(String name, String sourceFile, String targetFile, int threadNum, int intMain, 
    		ArrayList<JProgressBar> jpList/*������*/) {
    	File tf = new File(targetFile);
    	if (tf.exists())
    		return;
    	
        //���弸���߳�ȥ����  
        int DOWN_THREAD_NUM = threadNum;
        String DL_FILE_NAME = targetFile + ".dl";//��ʱ�ļ�
        
        String CFG_FILE_NAME = targetFile + ".cfg";//��¼���ؽ���
        
        InputStream[] isArr = new InputStream[DOWN_THREAD_NUM];  
        RandomAccessFile[] outArr = new RandomAccessFile[DOWN_THREAD_NUM];  
        try {
            // ����һ��URL����  
            URL url = new URL(sourceFile);  
            // �Դ�URL����򿪵�һ��������  
            isArr[0] = url.openStream();  
            //��ȡ�ļ���С
            long fileLen = getFileLength(url);  
            
            System.out.println(sourceFile + "�Ĵ�С" + fileLen);  
            
            // ������ļ���������һ��RandomAccessFile�����  
            //�������ж�ȡ��������д�루��ѡ���������ȡ�ļ�������һ���������ļ������ڶ��������ǣ�����ָ�����Դ��ļ��ķ���ģʽ  
            //"rw"�����ǿɶ���д��  
            outArr[0] = new RandomAccessFile(DL_FILE_NAME, "rw");

            outArr[0].setLength(fileLen);
            
            // ÿ�߳�Ӧ�����ص��ֽ���  
            long numPerThred = fileLen / DOWN_THREAD_NUM;
            // ����������Դ������ʣ�µ�����ȡģ  
            long left = fileLen % DOWN_THREAD_NUM;
            
            List<DownThread> list = new ArrayList<DownThread>();
            
            for (int i = 0; i < DOWN_THREAD_NUM; i++) {
                // �ϵ����ص������ļ�
                File fileConfig = new File(CFG_FILE_NAME + i);
                if(!fileConfig.exists())
                	fileConfig.createNewFile();
                
                // Ϊÿ���̴߳�һ����������һ��RandomAccessFile����  
                // ��ÿ���̷ֱ߳���������Դ�Ĳ�ͬ���֡�  
                //isArr[0]��outArr[0]�Ѿ�ʹ�ã��Ӳ�Ϊ0��ʼ  
                if (i != 0) {  
                    // ��URL�򿪶��������  
                    isArr[i] = url.openStream();  
                    // ��ָ������ļ��������RandomAccessFile����  
                    outArr[i] = new RandomAccessFile(DL_FILE_NAME, "rw");  
                }  
                // �ֱ���������߳�������������Դ  
                if (i == DOWN_THREAD_NUM - 1) {  
                    // ���һ���߳�����ָ��numPerThred+left���ֽ�  
                	DownThread dt = new DownThread(name + "-" + i, i * numPerThred, (i + 1) * numPerThred  
                            + left, isArr[i], outArr[i], jpList.get((intMain*3)+i), CFG_FILE_NAME+i);
                	dt.start();
                	list.add(dt);
                } else {  
                    // ÿ���̸߳�������һ����numPerThred���ֽ�  
                	DownThread dt = new DownThread(name + "-" + i, i * numPerThred, (i + 1) * numPerThred,  
                            isArr[i], outArr[i], jpList.get((intMain*3)+i), CFG_FILE_NAME+i);
                	dt.start();
                	list.add(dt);
                } 
            }             
            try  
            {
            	// �ȴ����ؽ��̽���
                for (DownThread my : list)  
                {  
                    my.join();  
                }
                
                // �ж������Ƿ�ȫ������
                boolean finishFlg = true;
                for (int i = 0; i < DOWN_THREAD_NUM; i++) {
                    FileReader reader = new FileReader(CFG_FILE_NAME+i);
                    BufferedReader br = new BufferedReader(reader);
                   
                    String str = null;
                   
                    while((str = br.readLine()) != null)
                    {                         
                    	if (!str.equals("Finish"))
                    	{
                            finishFlg = false;
                    		break;
                    	}
                    }
                    
                    br.close();
                    reader.close();
                }
                
                // �������ȫ������������ļ����ֵĺ�׺.dlȥ������ɾ�������ļ���cfg
                if (finishFlg)
                {
	                File f1 = new File(DL_FILE_NAME);
	                File f2 = new File(targetFile);
	                if (f2.exists())
	                	f2.delete();
	                if (f1.exists())
	                	f1.renameTo(f2);
	                for (int i = 0; i < DOWN_THREAD_NUM; i++) {
	                	File fileConfig = new File(CFG_FILE_NAME+i);
	                	fileConfig.delete();
	                }
                }
            }  
            catch (InterruptedException e)  
            {  
                e.printStackTrace();  
            }
            
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
    }  
  
    // �����ȡָ��������Դ�ĳ��ȵķ���  
    public static long getFileLength(URL url) throws Exception {  
        long length = 0;  
        // �򿪸�URL��Ӧ��URLConnection  
        URLConnection con = url.openConnection(); 
        con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        // ��ȡ����URL��Դ�ĳ���  
        long size = con.getContentLength();  
        length = size;  
        return length;  
    }
}  