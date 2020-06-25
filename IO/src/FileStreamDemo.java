import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-06-25
 */
public class FileStreamDemo {
    public static void main(String[] args) {
        inputDemo();
    }

    private static void inputDemo() {
        // 构造的方式:
        // 1. 参数是 File 对象
        // 2. 参数是 String 类型的路径

        // 可以转化为 try -with -resource 的形式
       /* InputStream is1 = null;
        try {
             is1 = new FileInputStream("测试目录\\a.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is1 != null) {
                    is1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        // FileInputStream 就是 InputStream 的一个子类
        // InputStream 是一种 输入 字节 流
        // FileInputStream 是一种 输入 字节 来自文件 流
        /*try(InputStream is = new FileInputStream("测试目录\\hello.txt")) {
            // 返回数据流中的下一个字节
            // 为什么要返回 int 类型, 因为返回值中需要返回-1, 这个和真正的数据做区分
            // 通过返回-1, 通知你, 已经读到文件结尾了
            // -1 被称为 EOS (End of Stream)

            // 统计读到的有效字节数
            int count = 0; // count 代表有效字节数
            while (true) {
                int b = is.read();
                if (b == -1) {
                    // 代表文件的内容全部读完了
                    // 所以退出循环
                    break;
                }
                count++;
                System.out.printf("%d%n", b); // 代表的是 十进制
                System.out.printf("%02x%n", b); // 代表的是 十六进制
                System.out.printf("%c%n", b); // 代表的是 字符形式
                System.out.println("======================");
            }
            System.out.println(count);

        } catch (IOException e) {
            e.printStackTrace();
        }*/


        // 一次读取一批数据的情况(建议)
        try(InputStream is = new FileInputStream("测试目录\\hello.txt")) {
            byte[] buffer = new byte[8]; // 创建一个缓冲区
            int count = 0; // read 的调用次数
            while (true) {
                // n 代表最终读取到的个数
                // 区别: 最多读 8 个  VS  实际读到 n 个
                // 可能出现比要求读的个数少的情况: 这种情况不代表一定就是读到 EOS
                // EOS: -1
                int n = is.read(buffer); // 最多一次读取 8 个字节, 不够 8 个也可以, 读到 buffer 的 [0, 8)
                System.out.println("n = " + n);
                count++;
                //is.read(buffer, 0, buffer.length); // 与上面等价
                if(n == -1) {
                    break;
                }
                for (int i = 0; i < n; i++) {
                    int b = buffer[i];
                    System.out.printf("%d%n", b); // 代表的是 十进制
                    System.out.printf("%02x%n", b); // 代表的是 十六进制
                    System.out.printf("%c%n", b); // 代表的是 字符形式
                    System.out.println("======================");
                }

                // 下面的处理是错误的
                /*if(n < 8) {
                    break;
                }*/
            }
            System.out.println("read() 一共被调用了: " + count + "次");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
