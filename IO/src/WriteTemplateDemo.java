import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-06-27
 */
public class WriteTemplateDemo {
    public static void 字节数据() throws IOException {
        byte[] buf = new byte[8192];
        // 把 buf 填充了有效数据

        try(OutputStream os = new FileOutputStream("测试目录\\out.png")) {
            for (int i = 0; i < 8; i++) {
                os.write(buf, i * 1024, 1024);
            }

            os.flush();
        }
    }

    public static void 字符数据() throws IOException {
        char[] buf = new char[8192];
        String s = "...";

        try(OutputStream os = new FileOutputStream("测试目录\\输出.txt")) {
            try(Writer writer = new OutputStreamWriter(os, "UTF-8")) {
                // 这是通过 字符数组 进行写入
                /*for (int i = 0; i < 8; i++) {
                    writer.write(buf, i * 1024, 1024);
                }
                writer.flush();*/

                // 通过 String 进行写入的
                try(PrintWriter printWriter = new PrintWriter(writer)) {
                    printWriter.println(s);
                    printWriter.printf("想写什么写什么");

                    printWriter.flush();
                }
            }
        }
    }
}
