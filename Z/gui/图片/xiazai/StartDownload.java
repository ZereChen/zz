package gui.ͼƬ.xiazai;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
  
public class StartDownload extends Thread  
{  
	ArrayList<JProgressBar> jpList = null;
	ArrayList<JLabel> jlList = null;
	static String sourceFile;
	static String name1;
	static String path1;
	JLabel label1;
    //���弸���߳�ȥ���ض���ļ�
    final static int FILE_THREAD_NUM = 3;
    //���弸���߳�ȥ���ص����ļ�  
    final static int DOWN_THREAD_NUM = 3;
    
    public static void main(String[] args){
    }
    
    public StartDownload(ArrayList<JProgressBar> jps, ArrayList<JLabel> jls, String path, String name, String path2, JLabel label){
    	jpList = jps;//������
    	jlList = jls;//��ǩ
    	sourceFile=path;//����	
        name1=name;//����		
        path1=path2;//�洢·��
        label1=label;
    }
    
    public void run(){    	
   
    	List<DownloadFile> fileList = new ArrayList<DownloadFile>();
    	
    	buildList(fileList);
    	
        System.out.println("���ؿ�ʼ");  
        label1.setText("���ؿ�ʼ");
        List<FileDownThread> list = new ArrayList<FileDownThread>();
  
        for (int i = 0; i < FILE_THREAD_NUM; i++)  
        {  
        	FileDownThread my = new FileDownThread("�߳� " + i, fileList, DOWN_THREAD_NUM, jpList, jlList);  
        	System.out.println(my);
            my.start();
            list.add(my);
        }
  
        try  
        {  
            for (FileDownThread my : list)  
            {  
                my.join();  
            }  
        }  
        catch (InterruptedException e)  
        {  
            e.printStackTrace();  
        }  
  
        System.out.println("�������");
        
       label1.setText("���ؽ���");
    }

    private static void buildList(List<DownloadFile> fileList){

    	DownloadFile downloadfile1 = new DownloadFile();
    	String a=path1+"\\"+name1;
    	downloadfile1.sourceFile=sourceFile;
    	downloadfile1.targetFile=a;
    	downloadfile1.finished=0;
    	fileList.add(downloadfile1);
    	
    }
} 