
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: Http 客户端发送请求
 * User: HHH.Y
 * Date: 2020-07-02
 */
public class HttpClientDemo {
    public static void main(String[] args) {
        // URL:  http://www.baidu.com/s?wd=java
        // 定义了一个 URL
        String SERVER_HOST = "www.baidu.com";
        int SERVER_PORT = 80;

        // 给客户端发送 HTTP 协议
        try(Socket socket = new Socket(SERVER_HOST, SERVER_PORT)) {
            // GET 方法的请求, 不包含请求体
            String request = "GET /s?wd=java HTTP/1.0\r\nHost: www.baidu.com\r\n\r\n";

            // 成功的发送请求
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
            writer.print(request);
            writer.flush();

            // 等待客户端响应
            Scanner scanner = new Scanner(socket.getInputStream(), "utf-8");
            while (scanner.hasNextLine()) {
                String response = scanner.nextLine();
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
