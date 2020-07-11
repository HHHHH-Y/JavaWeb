package lab;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description: 设置运行状态
 * 如何将 http 和最终的 url 关联在一起?
 * 老方法: 在 web.xml 中进行配置
 * 新方法: 可以通过 WebServlet 进行注解, 直接在代码中进行关联
 *
 * 关于状态码:
 * 1. 404: 站在用户的角度, 看到 404, 就说明 URL 没有对应到一个资源上
 * 2. 405: TomCat 找到对应的资源后, 没有覆写响应的方法.
 * 3. HttpServletResponse 的 sendError(int status, String s) 方法, (1). setStatus(status) 可以设置方法; (2), 可以发送合适的页面出去
 * 4. 500: 主要是由异常引起的, 所以看到 500 错误, 就当成出现异常进行处理
 *
 * 如何解决 404 问题:
 * 1. TomCat 根据 URL 中的 Context Path 确定, 分配给哪个 Web 应用 (Application Path)
 * 2. TomCat 根据 URL 中的 ServletPath 确定, 分配给哪个 Servlet 对象处理 ( @WebServlet(...) )
 *
 * User: HHH.Y
 * Date: 2020-07-09
 */
@WebServlet("/status")
public class StatusServlet extends HttpServlet {
    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int a = 0;
        int c = 20;
        int tmp = c / a;
    }*/

    // 关于 3XX 的跳转

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 301 跳转: 永久性跳转
        /*resp.setStatus(301);
        resp.setHeader("Location", "http://www.baidu.com/");*/

        // 302 跳转, 不会区分使用的是 get 还是 post
       /* resp.setStatus(302);
        resp.setHeader("Location", "/dest");*/

       // 303 跳转, 跳转后, 浏览器的提交都是 Get 请求
        resp.setStatus(303);
        resp.setHeader("Location", "/dest");
    }
}
