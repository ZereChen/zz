package abc.www;

import java.io.*;
public class NewApp006 {
	public static void main(String[] args) throws IOException{
	char charArr[]=new char[1024]; //���������� 1024 ���ַ�������
        FileReader b=new FileReader("C:\\Users\\Administrator\\Desktop\\�½��ı��ĵ�.txt");
        int iNum=b.read(charArr); //�����ݶ��뵽���� a �У��������ַ���
        String str=new String(charArr,0,iNum); //���ַ�������ת�����ַ���
        System.out.println("��ȡ���ַ�����Ϊ��"+iNum+",����Ϊ��\n");
        System.out.println(str);
	}
}
