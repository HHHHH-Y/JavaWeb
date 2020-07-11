import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description: 关于种 cookie 问题
 * User: HHH.Y
 * Date: 2020-07-09
 */
@WebServlet("/set-cookie")
public class SetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setHeader("Set-Cookie", "today=2020/7/10"); // 这句非常的关键

        // 以下是 Servlet 提供的更方便的类和方法种 cookie
        // 本质上都是在设置响应头: Set-Cookie
        Cookie cookie = new Cookie("today", "2020/7/10");
        //cookie.setDomain("baidu.com");

        resp.addCookie(cookie);
        resp.setContentType("text/plain; charset = utf-8");
        resp.getWriter().println("种下 cookie: today=2020/7/10");
    }
}
