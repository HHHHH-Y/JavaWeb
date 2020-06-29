package udp.dictionary;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 服务端: 进行字典式服务
 * User: HHH.Y
 * Date: 2020-06-29
 */
public class Server {
    // 作为服务器, 需要公开自己的地址
    // IP 地址, 交给 OS 选, 在所有的 IP 地址上都公开
    // port 端口, 需要我们自己明确, 随便选
    // 1. [0, 65535], 一般建议选 1024 以后的, 因为 1024 以前的端口号可能已经被占用
    // 2. port 只能属于唯一的一个进程, 所以不能选择已经被使用的端口
    public static final int PORT = 9260; // 由于端口号是要被公开的, 所以必须使用 public

    public static void main(String[] args) {
        // 1. 作为服务端, 首先要"开张", 也就是要像客户端公开自己的 ip:port
        // 也就是说需要创建一个 socket
        try(DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.printf("%s, DEBUG: 服务端 Socket 已创建, UDP 端口是: %d%n", new Date(), PORT);

            // 在一个循环中, 不停的接收请求
            // 每次收到的请求, 可以来自完全不同的用户
            // 接受请求 -> 处理请求得到响应 -> 发送请求
            while (true) {
                byte[] receiveBuffer = new byte[1024];
                // 需要应用的开发人员和用户保证请求不会超过 1024 字节

                // 创建一个 DatagramPacket 对象, 并且把缓冲区(receiveBuffer) 关联上去
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, 0, receiveBuffer.length);

                // 通过 socket 等待客户端发送数据, 会把接受到的数据放入 receivePacket 对象(关联的 receiveBuffer 中)
                // 会调用一个阻塞方法, 不知道什么时候才能返回
                System.out.printf("%s: DEBUG: 等待客户端发送请求 ... %n", new Date());
                serverSocket.receive(receivePacket);
                System.out.printf("%s: DEBUG: 收到了客户端发送的请求%n", new Date());

                //2. 数据接收成功, 需要明确
                // 对方是谁(对方的 ip + port)
                String host = receivePacket.getAddress().getHostName();
                System.out.printf("%s: DEBUG: 对方的 host 是: %s%n", new Date(), host);
                int port = receivePacket.getPort();
                System.out.printf("%s: DEBUG: 对方的 port 是: %d%n", new Date(), port);
                // 我接收了多少数据
                int length = receivePacket.getLength();
                System.out.printf("%s: DEBUG: 接收到了 %d 字节的数据%n", new Date(), length);

                // 目前收到的数据都是字节形式 的, 我们转换成字符形势, 便于理解
                // 字节 -> 字符, 设计到字符集编解码的问题
                // 假设双方约定好都使用 UTF-8 的编码 (需要我们应用保证)
                String request = new String(receiveBuffer, 0, length, "UTF-8");
                System.out.printf("%s: DEBUG: 收到的请求是: %s%n", new Date(), request);

                // 3. 进行业务处理, 并得到准备发送回客户端的响应
                String response = service(host, port, request);
                System.out.printf("%s: DEBUG: 准备发送给对方的响应是: %s%n", new Date(), response);

                // 通过 socket 发送响应
                // 需要把 目前 字符数集 转换成 字节数集
                byte[] sendBuffer = response.getBytes("UTF-8");

                // 创建发送用的 DatagramPacket, 同时关联上 byte[] 发送的数据
                // 和接收不同的是, 需要关联上对方是谁, 便于经过 socket, 让 OS 发送给网上的正确进程
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, receivePacket.getAddress(), port);
                // 经过 socket 准备进行正式发送了
                System.out.printf("%s: DEBUG: 准备发送数据 ... %n", new Date());
                serverSocket.send(sendPacket);
                System.out.printf("%s: DEBUG: 发送数据成功%n", new Date());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static class Translation {
        private String 中文含义;
        private List<String> 例句 = new ArrayList<>();
    }

    private static final Map<String, Translation> 字典 = new HashMap<>();

    static {
        // 在这个静态代码块中, 需要初始化两个单词的中文翻译以及例句
        {
            Translation translation = new Translation();
            translation.中文含义 = "n. 苹果，苹果树，苹果似的东西；[美俚]炸弹，手榴弹，（棒球的）球；[美俚]人，家伙。";
            translation.例句.add("He took another bite of apple. \r\n\r\n他又咬了一口苹果。");
            translation.例句.add("She crunched her apple noisily. \r\n\r\n她吃苹果发出嘎嚓嘎嚓的声音。");
            字典.put("apple", translation);
        }

        {
            Translation translation = new Translation();
            translation.中文含义 = "n. [园艺] 梨树；梨子";
            translation.例句.add("She bit into a ripe juicy pear. \r\n\r\n她咬了一口熟透了的多汁的梨。");
            translation.例句.add("Pear trees are grafted on quince rootstocks. \r\n\r\n梨树被嫁接到榅桲的根茎上。");
            字典.put("pear", translation);
        }
    }
    /**
     * 进行字典翻译式服务 (translate server)
     * 使用一个 map, 用来存放一个单词以及其相应的翻译和例句
     * @param host
     * @param port
     * @param request
     * @return
     */

    private static String service(String host, int port, String request) {
        Translation translation = 字典.get(request);
        if(translation == null) {
            return "不认识的单词";
        }
        StringBuffer responseBuffer = new StringBuffer();
        responseBuffer.append("含义: \r\n");
        responseBuffer.append(translation.中文含义);
        responseBuffer.append("\r\n\r\n例句: \r\n");
        responseBuffer.append(translation.例句);
        responseBuffer.append("\r\n");

        return responseBuffer.toString();
    }

    /**
     * 显示回显服务
     * @param host
     * @param port
     * @param request
     * @return
     */
//    private static String service(String host, int port, String request) {
//        return request;
//    }


}
