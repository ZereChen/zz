package gui.ͼƬ.xiazai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;  
import java.io.RandomAccessFile;  

import javax.swing.JProgressBar;
  
public class DownThread extends Thread {  
      
    // �����ֽ����飨ȡˮ����Ͳ���ĳ���  
    private final int BUFF_LEN = 100;  
      
    // �������ص�ԭʼ��ʼ��  
    private long start;  
    
    // �������ص�ʵ����ʼ��  
    private long downloadstart;  
      
    // �������صĽ�����  
    private long end;  
      
    // ������Դ��Ӧ��������  
    private InputStream inputStream;  
      
    // �����ص����ֽ������raf��  
    private RandomAccessFile raf;  
    
    // ������
    private JProgressBar jpb;
    
    // �ϵ����������ļ�
    private String cfgFileName;
      
    // ���������������������������������ʼ�㡢������  
    public DownThread(String name, long start, long end, InputStream is, RandomAccessFile raf, JProgressBar jp, String fileName) {  
        // ������̸߳������ص��ֽ�λ��  
        System.out.println(start + "---->" + end);  
        this.setName(name);
        this.start = start;  
        this.downloadstart = start;
        this.end = end;  
        this.inputStream = is;  
        this.raf = raf;  
        this.jpb = jp;
        this.cfgFileName = fileName;
    }  
  
    public void run() {  
        try {
        	// �ж��Ƿ���Ҫ�ϵ�����
            FileReader reader = new FileReader(cfgFileName);
            BufferedReader br = new BufferedReader(reader);
           
            String str = null;
           
            while((str = br.readLine()) != null)
            {                         
            	if (!str.equals("Finish"))
            	{
            		downloadstart = Long.parseLong(str);
            		break;
            	}
            	else
            	{
                    jpb.setMinimum(0);
                    jpb.setMaximum(1);
                    jpb.setValue(1);
            		return;
            	}
            }
            
            br.close();
            reader.close();            
            // ���ؿ�ʼλ������
            inputStream.skip(downloadstart);  
            raf.seek(downloadstart);             
            // �������ļ���д����̵߳���ʼ����λ��
    		writeToFile(cfgFileName, Long.toString(downloadstart));		
            // �����ȡ���������ݵĵĻ������飨��Ͳ��  
            byte[] buff = new byte[BUFF_LEN];  
            // ���̸߳���������Դ�Ĵ�С  
            long contentLen = end - start;  
            // ���������Ҫ��ȡ���ξͿ�����ɱ��̵߳�����  
            long times = contentLen / BUFF_LEN + 4;  
            // ʵ�ʶ�ȡ���ֽ���  
            int hasRead = 0;         
            jpb.setMinimum(0);
            jpb.setMaximum((int) times);     
            
            System.out.println(times+"�����������Ǹ�ɶ");
            
            for (int i = 0; i <times; i++) {
            	if (!ThreadController.start)
            		break;
            	
            	if (downloadstart > start + i*BUFF_LEN)
            		continue;           	
                hasRead = inputStream.read(buff);
                downloadstart += hasRead;
                // �����ȡ���ֽ���С��0�����˳�ѭ����  
                if (hasRead < 0) {
                	writeToFile(cfgFileName, "Finish");
                    break;  
                }  
                raf.write(buff, 0, hasRead);
            	// ��������д�������ļ�
                if (i == times-1)
                	writeToFile(cfgFileName, "Finish");
                else
                	writeToFile(cfgFileName, Long.toString(downloadstart)); 	
            	// ��������ʾ             
					jpb.setValue(i+1);             
            }  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
        // ʹ��finally�����رյ�ǰ�̵߳��������������  
        finally {  
            try {  
                if (inputStream != null) {  
                    inputStream.close();  
                }  
                if (raf != null) {  
                    raf.close();  
                }  
            } catch (Exception ex) {  
                ex.printStackTrace();  
            }  
        }  
    }
    
    private static void writeToFile(String fileName, String content)
    {
    	 try {
             // write string to file
             FileWriter writer = new FileWriter(fileName);
             BufferedWriter bw = new BufferedWriter(writer);
             bw.write(content);          
             bw.close();
             writer.close();
    	 }
    	 catch(FileNotFoundException e) {
                   e.printStackTrace();
         }
         catch(IOException e) {
               e.printStackTrace();
         }
	}
}