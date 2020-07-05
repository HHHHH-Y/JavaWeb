import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * Description: 动态资源方法的实现
 * HTTP 的 doGet 方法
 * 1. 取得 Query String 得到的 wd 这个名字对应的 value
 * 2. 设置它的 Content-Type 的类型为 text/http, 字符集编码为: utf-8
 * 3. 输出其响应的内容
 * User: HHH.Y
 * Date: 2020-07-05
 */
public class MyFirstDynamicResource extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 通过 query string 得到 wd 这个名字对应的 value 值
        String wd = req.getParameter("wd");

        // 2. 返回的 content-Type 类型是 text/http, 编码方式是: utf-8
        resp.setContentType("text/http; charset = utf-8");
        PrintWriter writer = resp.getWriter();

        // 3. 输出的响应内容
        writer.print("<h1>我是 /s 资源</h1>");
        writer.println("<p>用户要查找的关键字是: " + wd + "</p>");
    }
}
