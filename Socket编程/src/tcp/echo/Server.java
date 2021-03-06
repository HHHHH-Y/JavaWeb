package tcp.echo;

import sun.rmi.runtime.Log;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: TCP 的服务端
 * User: HHH.Y
 * Date: 2020-06-30
 */
public class Server {
    // 明确 服务端的 端口号信息, 便于客户端找到服务端
    public static final int SERVER_PORT = 8888;

    public static void main(String[] args) throws IOException {
        // 开张(向客户端提供端口号)
        Logger.debug("准备创建 serverSocket");
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        Logger.debug("创建 serverSocket 成功, 端口号是: " + SERVER_PORT);
        // 等待着客户端发送请求 (等待着接电话)
//        while (true) {
            // 使用 accept() 方法建立连接 (相当于接电话)
            Logger.debug("等待获取建立好的连接(等待接电话)");
            Socket socket = serverSocket.accept();
            Logger.debug("获取已经建立成功的连接(有人打电话来了并成功接听)");

            // 通过建立的连接 socket, 可以获取到发送请求的客户端信息(IP + port)
            SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
            Logger.debug("对方的地址是: " + remoteSocketAddress);
            int port = socket.getPort();
            Logger.debug("对方的端口号是: " + port);

            // 通过 socket 可以获取到 InputStream 作为输入流, OutputStream 作为输出流
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            Logger.debug("获取连接中的 InputStream 和 OutputStream");

            // 全部通过 字符数据的形式 进行读写操作
            // 通过 Scanner 进行读入数据
            // 通过 PrintWriter 进行写入数据 (PrintWriter -> Writer -> OutputStream)
            Scanner scanner = new Scanner(inputStream, "UTF-8");
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            // 接收数据(使用 scanner.nextLine())
            Logger.debug("等待对方发送请求");
            String request = scanner.nextLine();
            Logger.debug("接收到对方发送的请求: " + request);
            // 进行数据的响应
            String response = echoService(request);
            Logger.debug("会给对方的响应是: " + response);

            // 发送响应
            Logger.debug("准备发送响应");
            writer.printf("%s\r\n", response);
            Logger.debug("记得调用 flush(), 把数据真正写入到 socket 中");
            writer.flush(); // 记得 flush
            Logger.debug("响应发送成功");

            // 由于是 "短连接", 所以处理完一次响应之后需要关闭连接
            Logger.debug("准备关闭连接(挂断电话)");
            socket.close();
            Logger.debug("成功关闭连接");
//        }
    }

    /**
     * 回显服务
     * @param request
     * @return
     */
    private static String echoService(String request) {
        return request;
    }
}
