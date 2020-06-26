import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-06-26
 */
public class FileStreamDemo2 {
    public static void main1(String[] args) {
        // 输入
        /*try(InputStream is = new FileInputStream("测试目录\\hello.txt")) {
            byte[] buf = new byte[1024];
            while (true) {
                int n = is.read(buf); // 将从 InputStream 中读到的数据放入 buf 中
                if(n == -1) {
                    break;
                }

                // 有效数据保存在 buf[0, n)
                for (int i = 0; i < n; i++) {
                    byte b = buf[i];
                    System.out.printf("%c%n", b);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // 1. 如果文件不存在, 会进行文件的创建(失败的条件等同于平时创建文件失败的条件)
        // 2. 如果文件存在, 会进行 "覆盖" 方式的写入
        try(OutputStream os = new FileOutputStream("测试目录\\world.txt")) {
            // 1. 单字节写入方式
            /*os.write('H');
            os.write('\r');
            os.write('\n');
            os.write('W');*/

            // 2. 批量方式写入
            byte[] buf = new byte[8];
            buf[0] = 'H';
            buf[1] = '\r';
            buf[2] = '\n';
            buf[3] = 'W';
            os.write(buf, 0, 4);

            // 3. 无论是哪种方式写入, 都一定需要做 flush() 操作
            os.flush();  // 强制要求把系统(软件部分 JVM/OS)中缓冲的数据, 刷新到真正的硬件中
                         // 为了提升速度, 很多 Output 的类实现中, 都会包含缓冲区
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main2(String[] args) throws IOException {
        // 带有中文字符的写入
        try(InputStream is = new FileInputStream("测试目录\\中文.txt")) {
            byte[] buf = new byte[1024];
            int n;
            while ((n = is.read(buf)) != -1) {
                /*for (int i = 0; i < n; i++) {
                    System.out.printf("|%d|%02x|%n", buf[i], buf[i]);
                }*/

                // 假设 buf 中读取的中文, 没有出现被拆断的形式
                String s = new String(buf, 0, n, "UTF-8");
                System.out.println(s);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        // 使用这种方式进行字符输入 较为灵活
        // 1. 还是需要先有字节流
        try(InputStream is = new FileInputStream("测试目录\\中文.txt")) {
            // 2. 利用字节流作为构造方法(传入字节流的对象名, 编码方式), 构造出字符流
            try(Reader reader = new InputStreamReader(is, "UTF-8")) {
                // 读取的单位变成了 字符char
                // 已经完成了字符集解析的工作了

                // 单字符读取
                //int c = reader.read(); // -1 代表 EOS

                // 批量读取
                char[] buf = new char[1024];
                int n;
                while ((n = reader.read(buf)) != -1) {
                    for (int i = 0; i < n; i++) {
                        System.out.println(buf[i]);
                    }
                }
            }
        }

        // 字符集默认按照项目的字符集进行编码(UTF-8)
        try(Reader reader = new FileReader("测试目录\\中文.txt")) {
            char[] buf = new char[1024];
            int n;
            while ((n = reader.read(buf)) != -1) {
                for (int i = 0; i < n; i++) {
                    System.out.println(buf[i]);
                }
            }
        }
    }

}
