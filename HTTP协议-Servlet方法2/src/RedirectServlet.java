import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description: 3XX 重定向
 * 关于 3XX 跳转部分:
 * 1. 301 跳转: 被请求的资源永久性跳转到新的位置
 * 2. 302 跳转: 请求后, 浏览器的提交不区分是 get 方法还是 post 方法
 * 3. 303 跳转: 请求后, 浏览器的提交统一为 get 方法
 * 4. 307 跳转: 请求后, 浏览器的提交方式和上一次的提交方式保持一致
 * User: HHH.Y
 * Date: 2020-07-09
 */
@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirect(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirect(resp);
    }

    private void redirect(HttpServletResponse resp) throws IOException {
        /*resp.setStatus(303);*/
        /*resp.setStatus(307);
        resp.setHeader("Location", "/dest");*/

        // 发送的是 302 请求
        resp.sendRedirect("/dest");
    }
}
