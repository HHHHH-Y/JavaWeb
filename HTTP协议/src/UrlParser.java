import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 拆分一段 URL, 分别表示出其: 协议, ip, port, 路径以及 完整的查询字符串
 * User: HHH.Y
 * Date: 2020-07-02
 */
public class UrlParser {
    public static void main(String[] args) {
        String[] urls = {
                "https://www.baidu.com/s?wd=java&rsv_spt=1&rsv_iqid=0xb86707600004351f&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_dl=tb&rsv_sug3=8&rsv_sug1=4&rsv_sug7=100&rsv_sug2=0&rsv_btype=i&inputT=2099&rsv_sug4=2305",
                "https://www.sogou.com/web?query=java&_asf=www.sogou.com&_ast=&w=01019900&p=40040100&ie=utf8&from=index-nologin&s_from=index&sut=871&sst0=1593516407073&lkt=4%2C1593516406203%2C1593516406827&sugsuv=00033F407B8B8B625E1ECF5D4D743627&sugtime=1593516407073",
                "http://www.qq.com/",
                "http://localhost:9939/hello"
        };
        for (String url:urls) {
            parseUrl(url); // 对 url 进行拆解
            System.out.println("==================");
        }
    }

    // 创建了一个存放 协议 的 Map
    private static Map<String, Integer> schemaDefaultPortMap = new HashMap<>();
    static {
        schemaDefaultPortMap.put("http", 80);
        schemaDefaultPortMap.put("https", 443);
    }
    private static void parseUrl(String url) {
        // 在 url 中定位 协议部分  --- 通过第一个 ://
        int post;
        post = url.indexOf("://"); // 获取到 :// 的下标
        String schema = url.substring(0, post); // 截取到了 协议
        System.out.println("协议部分: " + schema);

        String remain = url.substring(post + 3); // + 3 是跳过 :// 这三个符号

        // 在 remain 中定位 服务器部分  --- 通过第一个 /
        post = remain.indexOf("/");
        String server = remain.substring(0, post);
        System.out.println("服务端: " + server);

        // 如果 server 中有 : , 说明有明确的端口号, 如果没有, 就根据协议进行明确
        int i = server.indexOf(":");
        String host = null;
        int port = -1;
        if(i == -1) {
            // 说明没有明确的端口号信息, 是协议默认的
            host = server;
            port = schemaDefaultPortMap.get(schema);
        } else {
            // 说明有明确的端口号信息
            host = server.substring(0, i);
            port = Integer.parseInt(server.substring(i + 1));
        }
        System.out.println("服务端主机信息: " + host);
        System.out.println("服务端端口信息: " + port);

        remain = remain.substring(post); // 包含最开始的 /
        post = remain.indexOf("?");
        if(post == -1) {
            // 说明没有查询信息
            System.out.println("路径部分: " + remain);
        } else {
            String path = remain.substring(0, post);
            String queryString = remain.substring(post + 1);
            System.out.println("路径部分: " + path);
            System.out.println("查询字符串部分: " +    queryString);
        }
    }
}
