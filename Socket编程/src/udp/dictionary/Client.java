package udp.dictionary;

import udp.echo.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.Scanner;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-06-29
 */
public class Client {
    // 需要明确服务端的 ip 地址
    public static final String SERVER_HOST = "127.0.0.1";

    public static void main(String[] args) {
        // 从标准输入中读取内容的 scanner
        Scanner stdinScanner = new Scanner(System.in);

        // 创建一个 DatagramSocket, 创建方式和 Server 的略有不同
        // 不需要明确我们自己的 port, 交给 OS 随意分配一个未使用的 port 即可
        // 我们是 Client, 所以我们的地址不需要公开
        try(DatagramSocket clientSocket = new DatagramSocket()) {
            System.out.printf("%s: DEBUG: 已经创建好 socket 对象%n", new Date());
            System.out.print("echo> ");
            while (stdinScanner.hasNextLine()) {
                String request = stdinScanner.nextLine();
                System.out.printf("%s, DEBUG: 读取用户的输入是: %s%n", new Date(), request);
                if(request.equalsIgnoreCase("quit")) {
                    break;
                }

                // 准备发送
                byte[] sendBuffer = request.getBytes("UTF-8");
                // 绑定对方的 ip
                // 绑定对方的 port
                // 127.0.0.1 : 9260
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, 0 , sendBuffer.length, InetAddress.getByName(SERVER_HOST), Server.PORT);

                System.out.printf("%s: DEBUG: 准备发送响应 ... %n", new Date());
                clientSocket.send(sendPacket);
                System.out.printf("%s: DEBUG: 请求发送成功%n", new Date());

                // 等待响应
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, 0, receiveBuffer.length);

                System.out.printf("%s: DEBUG: 准备接收响应 ... %n", new Date());
                clientSocket.receive(receivePacket);
                System.out.printf("%s: DEBUG: 响应接收成功 %n", new Date());

                // 转换成字符形式
                String response = new String(receiveBuffer, 0, receivePacket.getLength(), "UTF-8");
                System.out.println("echo server 应答: " + response);
                System.out.print("echo> ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

