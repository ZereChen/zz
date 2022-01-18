package scripting;

import java.io.*;
import java.net.URL;
import tools.HexTool;
import tools.Pair;

public class LieDetectorScript {

    private static final String IMG_DIRECTORY = "scripts/lieDetector";
    private static final String CAPTCHA_VERIFIER = "98818D40B83AECCFB7AFD7FD9653E1037519AC61";
    private static final String CAPTCHA_SERVER = "http://localhost/captcha.php?verify=98818D40B83AECCFB7AFD7FD9653E1037519AC61";

    public static Pair<String, String> getImageBytes() {//��image�õ�byte[]
        try {
            URL url = new URL("http://localhost/captcha.php?verify=98818D40B83AECCFB7AFD7FD9653E1037519AC61");

            InputStream inputStream = url.openStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();//�½�����
            //�õ�һ��int[]���� ȡ�õ�������������Ϣ
            byte[] buffer = new byte[1024];
            int n = 0;
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);//��buffer��ͼƬ������ģʽд����
            }
            String imgByte = HexTool.toString(output.toByteArray());//�����л�ȡ��������
            return new Pair(imgByte.substring(39, imgByte.length()), output.toString().split("CAPTCHA")[0]);//��֤��
        } catch (IOException ex) {
            File directory = new File("scripts/lieDetector");
            if (!directory.exists()) {
                System.err.println("lieDetector folder does not exist!");
                return null;
            }
            String[] filename = directory.list();
            String answer = filename[server.Randomizer.nextInt(filename.length)];
            answer = answer.substring(0, answer.length() - 4);
            try {
                return new Pair(HexTool.toString(getBytesFromFile(new File("scripts/lieDetector/" + answer + ".jpg"))), answer);
            } catch (IOException e) {
            }
        }
        return null;
    }

    public static byte[] getBytesFromFile(File file)
            throws IOException {
        byte[] bytes = null;
        try {
            InputStream is = new FileInputStream(file);
            long length = file.length();
            if (length > 2147483647L) {
                return null;
            }
            bytes = new byte[(int) length];
            int offset = 0;
            int numRead = 0;
            while ((offset < bytes.length) && ((numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)) {
                offset += numRead;
            }
            if (offset < bytes.length) {
                System.err.println("[Lie Detector Script] Could not completely read file " + file.getName());
                return null;
            }
        } catch (IOException e) {
            return null;
        }
        return bytes;
    }
}
