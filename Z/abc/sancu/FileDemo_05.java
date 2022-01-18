package abc.sancu;

import java.io.File;

public class FileDemo_05 {
	// �ж�ɾ��ָ�����ļ����ļ����Ƿ�ɹ����ɹ��򷵻�true���򷵻�false
	public static boolean deleteAnyone(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("�ļ�ɾ��ʧ�ܣ�" + fileName + "�ļ������ڣ�");
			return false;
		} else {
			if (file.isFile()) {
				return FileDemo_05.deleteFiles(fileName);
			} else {
				return FileDemo_05.deleteDir(fileName);
			}
		}
	}
        public static boolean ɾ���ļ�(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("�ļ�ɾ��ʧ�ܣ�" + fileName + "�ļ������ڣ�");
			return false;
		} else {
			if (file.isFile()) {
				return FileDemo_05.deleteFiles(fileName);
			} else {
				return FileDemo_05.deleteDir(fileName);
			}
		}
	}
	// �ж�ɾ��ָ�����ļ��Ƿ�ɹ����ɹ��򷵻�true���򷵻�false
	public static boolean deleteFiles(String fileName) {
		File file = new File(fileName);
		// ����ļ�·����Ӧ���ļ����ڣ�������һ���ļ�����ֱ��ɾ����
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				//System.out.println("�ļ���" + fileName + "ɾ���ɹ���");
				return true;
			} else {
				//System.out.println("�ļ�" + fileName + "ɾ��ʧ�ܣ�");
				return false;
			}
		} else {
			//System.out.println("�ļ�ɾ��ʧ�ܣ�" + fileName + "�ļ������ڣ�");
			return false;
		}
	}
	// �ж�ɾ��ָ����Ŀ¼���ļ��У��Լ�Ŀ¼�µ��ļ��Ƿ�ɹ����ɹ��򷵻�true���򷵻�false
	public static boolean deleteDir(String dir) {
		// ���dir�����ļ��ָ�����β���Զ�����ļ��ָ�����
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		// ���dir��ʾ���ļ������ڣ����߲���һ���ļ��У����˳�
		if (!dirFile.exists() || (!dirFile.isDirectory())) {
			System.out.println("Ŀ¼ɾ��ʧ�ܣ�" + dir + "Ŀ¼�����ڣ�");
			return false;
		}
		boolean flag = true;
		// ɾ��ָ��Ŀ¼�������ļ���������Ŀ¼��
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// ɾ���ļ�
			if (files[i].isFile()) {
				flag = FileDemo_05.deleteFiles(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
			// ɾ����Ŀ¼
			else if (files[i].isDirectory()) {
				flag = FileDemo_05.deleteDir(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}
		if (!flag) {
			System.out.println("ɾ��Ŀ¼ʧ�ܣ�");
			return false;
		}
		// ɾ����ǰĿ¼
		if (dirFile.delete()) {
			System.out.println("Ŀ¼��" + dir + "ɾ���ɹ���");
			return true;
		} else {
			return false;
		}
	}
	public static void main(String[] args) {
		// ɾ���ļ�
		System.out.println("����deleteFiles�����Ľ�����£�");
		String file = "D:\\temp\\aa\\bb\\ccFile.txt";
		FileDemo_05.deleteFiles(file);
		System.out.println();
		// ɾ��Ŀ¼
		System.out.println("����deleteDir�����Ľ�����£�");
		String dir = "D:\\temp\\key";
		FileDemo_05.deleteDir(dir);
		System.out.println();
		// ɾ���ļ���Ŀ¼
		System.out.println("����deleteAnyone�����Ľ�����£�");
		dir = "D:\\temp\\read";
		FileDemo_05.deleteAnyone(dir);
	}
}
