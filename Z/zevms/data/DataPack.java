package data;

import java.io.*;
import javax.swing.*;
public class DataPack implements Serializable
{
	public int optype=0; //1�ǵ�½ 2�Ƿ������� 3���뿪 4�����û���½ 5��ϵͳ�㲥��Ϣ

	public Object con=null;
	public String from=null;      //������Ϣ�û�������
	public String to=null;        //���͵����û�������
	public ImageIcon expr=null;   //Ҫ���͵ı���ͼƬ
	public String biaoqing=null;  //Ҫ���͵ı�������
	public String yanse=null;	  //Ҫ�������ֵ���ɫ
	public String txt=null;	      //Ҫ���͵���Ϣ����
	public boolean si=false;      //�Ƿ���˽��
	public DataPack()
	{
	}
	public void init()
	{
		optype=0;
		con=null;
		from=null;
		to=null;
		expr=null;
		biaoqing=null;
		yanse=null;
		txt=null;
		si=false;
	}
}