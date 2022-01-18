package abc.sancu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;

public class DeleteFileUtil {

    public static void main(String[] args) {
        String path = "F:\\test";//Ҫɾ�����ļ�����Ŀ¼
        String fileSuffixes = "";//ָ��Ҫɾ�����ļ���׺��ʽ(#��ʽ1#��ʽ2#��ʽ3#...��ʽn#),��Ϊnull����ʾɾ������
        boolean delSubFile = true;//�Ƿ�ɾ�����ļ�
        boolean keepEmptyFolder = true;//�Ƿ������ļ���
        DeleteFileUtil util = DeleteFileUtil.getDeleteFileUtil(path, fileSuffixes, delSubFile, keepEmptyFolder);
        int count = util.handler();
        System.out.println("ɾ��" + count + "���ļ�");
    }

    private SimpleFileVisitor<Path> sFileVisitor;
    private String datasOrginalPath;

    private DeleteFileUtil(int type, String oldPath, String fileSuffixes, boolean delSubFile,
            boolean keepEmptyFolder) {
        this.datasOrginalPath = oldPath;
        sFileVisitor = new DeleteFileVisitor(oldPath, fileSuffixes, delSubFile, keepEmptyFolder);
    }

    public int handler() {
        Path start = Paths.get(datasOrginalPath);
        try {
            Files.walkFileTree(start, sFileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DeleteFileVisitor dfv = (DeleteFileVisitor) sFileVisitor;
        return dfv.getResult();
    }

    /**
     *
     * @param originalPath Ҫɾ�����ļ���·��
     * @param fileSuffixes
     * ָ��Ҫɾ�����ļ���׺��ʽ(#��ʽ1#��ʽ2#��ʽ3#...��ʽn#),��Ϊnull����ʾɾ������,��Ϊ""���ַ�������ʾɾ�������޺�׺�ļ�
     * @param delSubFile �Ƿ�ɾ�����ļ�
     * @param keepEmptyFolder �Ƿ������ļ���
     * @return ����ɾ������
     */
    public static DeleteFileUtil getDeleteFileUtil(String originalPath, String fileSuffixes,
            boolean delSubFile, boolean keepEmptyFolder) {
        DeleteFileUtil ihonestFileUtil = new DeleteFileUtil(2, originalPath, fileSuffixes,
                delSubFile, keepEmptyFolder);
        return ihonestFileUtil;
    }
    
     public static DeleteFileUtil ɾ���ļ�(String originalPath, String fileSuffixes,
            boolean delSubFile, boolean keepEmptyFolder) {
        DeleteFileUtil ihonestFileUtil = new DeleteFileUtil(2, originalPath, fileSuffixes,
                delSubFile, keepEmptyFolder);
        return ihonestFileUtil;
    }

}
