package abc.sancu;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class DeleteFileVisitor extends SimpleFileVisitor<Path> {

    private String sourcePath;
    private String fileSuffixes;
    private boolean delSubFile;
    private boolean keepEmptyFolder;
    private String fileSuffix, tmpString;
    private File fileTmp;
    private boolean tmpBuer;
    private int delFileTotalCount;
    private int tryCount;
    private Path rootPath;

    public DeleteFileVisitor(String oldPath, String fileSuffixes, boolean delSubFile, boolean keepEmptyFolder) {
        sourcePath = oldPath;
        if (fileSuffixes != null) {
            this.fileSuffixes = fileSuffixes.trim().toLowerCase();
        }
        this.delSubFile = delSubFile;
        this.keepEmptyFolder = keepEmptyFolder;
        this.rootPath = Paths.get(sourcePath);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if (delSubFile || isRootPath(dir)) {
            return FileVisitResult.CONTINUE;
        } else {
            return FileVisitResult.SKIP_SUBTREE;
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if (fileSuffixes != null) {
            tmpString = file.toFile().getName();
            if (!isDelBySuffix(tmpString)) {
                return FileVisitResult.CONTINUE;
            }

            if (!fileSuffixes.contains(fileSuffix)) {
                // ������ָ���ļ���ʽ������
                return FileVisitResult.CONTINUE;
            }
        }
        fileTmp = file.toFile();
        tmpBuer = delFile(fileTmp);
        if (tmpBuer) {
            delFileTotalCount++;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        if (isRootPath(dir)) {
            //��Ŀ¼
            if (!keepEmptyFolder) {
                dir.toFile().delete();
            }
        } else //��Ŀ¼
        {
            if (!keepEmptyFolder) {
                dir.toFile().delete();
            }
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        exc.printStackTrace();
        return super.visitFileFailed(file, exc);
    }

    private boolean delFile(File file) {
        tryCount = 0;
        boolean tmpBuer = false;
        while (!(tmpBuer = file.delete()) && tryCount < 4) {
            //�����Ĵ�ɾ�����Ĵι�������ɾ����������
            try {
                Thread.sleep(500 * 1);
                tryCount++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return tmpBuer;
    }

    public int getResult() {
        return delFileTotalCount;
    }

    /**
     * �Ƿ��Ŀ¼
     *
     * @param path
     * @return
     */
    private boolean isRootPath(Path path) {
        if (path == null) {
            return false;
        }
        return this.rootPath.equals(path);
    }

    /**
     * ����ָ����׺��ʽ�ַ����ж�һ���ļ��Ƿ�Ҫɾ��
     *
     * @param fileName ���ж����ļ�����
     * @return ���Ҫɾ������true�����򷵻�false
     */
    private boolean isDelBySuffix(String fileName) {
        if (fileName == null) {
            return false;
        }

        if (fileSuffixes == null) {
            //ɾ������
            return true;
        } else {
            //ɾ��ָ���ļ�
            if (fileName.lastIndexOf(".") >= 0) {
//					��ȡ��ǰ�ļ���׺
                fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
            } else {
                fileSuffix = "";
            }
            if ("".equals(fileSuffixes)) {
                //ɾ���޺�׺�ļ�
                if ("".equals(fileSuffix)) {
                    return true;
                }
            } else //ɾ��ָ����׺�ļ�
            {
                if (fileSuffixes.contains("#" + fileSuffix + "#")) {
                    //�ں�׺ǰ���#����Ϊ�˱���һЩ���У����磺ʹ����Ҫɾ��recmp3��׺��ʽ����ô��������ַ���Ϊ#recmp3#,��ʱ������mp3�ļ�����ǰ�󲻼�#������£���contains�ͻ�����
                    return true;
                }
            }
        }

        return false;
    }
}
