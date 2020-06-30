package tcp.echo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * Description: 使用 线程池 的方式进行 服务端的响应
 * 主线程只负责进行连接
 * 响应资源的任务都交给子线程进行操作
 * User: HHH.Y
 * Date: 2020-06-30
 */

// 主线程
public class ServerThreadPoolLongConnection {
    // 明确 服务端的 端口号信息, 便于客户端找到服务端
    public static final int SERVER_PORT = 8888;

    public static void main(String[] args) throws IOException {
        // 创建一个固定大小为 8 的线程池
        ExecutorService threadPoll = Executors.newFixedThreadPool(8);

        // 开张(向客户端提供端口号)
        Logger.debug("准备创建 serverSocket");
        try(ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            Logger.debug("创建 serverSocket 成功, 端口号是: " + SERVER_PORT);
            // 等待着客户端发送请求 (等待着接电话)
            while (true) {
                // 使用 accept() 方法建立连接 (相当于接电话)
                Logger.debug("等待获取建立好的连接(等待接电话)");
                Socket socket = serverSocket.accept();
                Logger.debug("获取已经建立成功的连接(有人打电话来了并成功接听)");

                // 创建给线程池提供的任务
                Runnable task = new RequestResponseTaskLongConnection(socket);
                // 将任务传给线程池
                threadPoll.execute(task);
            }
        }

    }
}
