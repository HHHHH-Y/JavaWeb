import java.io.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: 读取字符数据
 * User: HHH.Y
 * Date: 2020-06-26
 */
public class ReadTemplateDemo {

    // 一行一行的进行读取, 每次处理一行数据
    public static void 字符数据1() throws IOException {
        try(InputStream is = new FileInputStream("测试目录\\中文.txt")) {
            try(Reader reader = new InputStreamReader(is, "UTF-8")) {
                try(Scanner scanner = new Scanner(reader)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        System.out.println(line);
                    }
                }
            }
        }
    }

    // 一行一行的进行读取, 每次处理一行数据 (变形)
    public static void 字符数据2() throws IOException {
        try(InputStream is = new FileInputStream("测试目录\\中文.txt")) {
            try(Scanner scanner = new Scanner(is, "UTF-8")) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                }
            }
        }
    }

    // 进行批量的读取
    public static void 字节数据() throws IOException {
        try(InputStream is = new FileInputStream("测试目录\\log.png")) {
            byte[] buf = new byte[1024];
            int n;
            while ((n = is.read(buf)) != -1) {
                // 使用 buf[0, n)
            }
        }
    }
}
