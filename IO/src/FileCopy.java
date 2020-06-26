import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 文件的拷贝
 * User: HHH.Y
 * Date: 2020-06-27
 */
public class FileCopy {
    public static void main(String[] args) throws IOException {
        String sourcePath = "测试目录\\中文.txt";
        String destPath = "测试目录\\中文-复制.txt";

        // 所谓的复制: 读取源的内容, 写入目标文件中
        try(InputStream is = new FileInputStream(sourcePath)) {
            try(OutputStream os = new FileOutputStream(destPath)) {
                byte[] buffer = new byte[8192];
                int n;
                while ((n = is.read(buffer)) != -1) {
                    os.write(buffer, 0, n);
                }

                os.flush();
            }
        }
    }
}
