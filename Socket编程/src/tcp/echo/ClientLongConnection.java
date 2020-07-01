package tcp.echo;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: 客户端 "长连接" 请求
 * User: HHH.Y
 * Date: 2020-06-30
 */
public class ClientLongConnection {
    // 明确对方服务端的 ip + port
    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = Server.SERVER_PORT;

    public static void main(String[] args) throws IOException {
        // 使用标准 scanner 进行请求服务
        Scanner stdinScanner = new Scanner(System.in);
        // 建立和服务端相连的 socket
        Logger.debug("准备连接服务端 (" + SERVER_HOST + ":" + SERVER_PORT + ")");
        try(Socket socket = new Socket(SERVER_HOST, SERVER_PORT)) {
            Logger.debug("连接已建立");
            // 拿到 本地的连接 ip 和 port
            SocketAddress localSocketAddress = socket.getLocalSocketAddress();
            Logger.debug("连接中, 本地地址是: " + localSocketAddress);
            int localPort = socket.getLocalPort();
            Logger.debug("连接中, 本地端口是: " + localPort);
            // 拿到 对方的 ip 和 port
            SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
            Logger.debug("连接中, 对方地址是: " + remoteSocketAddress);
            int port = socket.getPort();
            Logger.debug("连接中, 对方端口是: " + port);
            // 填写请求
            System.out.print("请填写请求> ");

            // 通过一个 while 循环来进行多次请求
            // 只有通过 ctrl + D, 才可以结束服务端的请求
            while (stdinScanner.hasNextLine()) {
                String request = stdinScanner.nextLine();

                // 通过 socket 拿到 InputStream 输入流 和 OutputStream 输出流
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                // 通过字符数据形式, 进行 读写操作
                Scanner scanner = new Scanner(inputStream, "UTF-8");
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                // 发送请求
                Logger.debug("准备发送请求");
                writer.printf("%s\r\n", request);
                Logger.debug("发送调用成功, 记得 flush");
                writer.flush();
                Logger.debug("请求发送成功");

                // 接收响应
                Logger.debug("准备接收响应");
                String response = scanner.nextLine();
                Logger.debug("接收到的响应是: " + response);
                System.out.println(response);

                System.out.print("请填写请求> ");
            }
        }
    }
}
