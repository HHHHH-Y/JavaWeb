import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-07-05
 */
public class MyPracticeAboutPxc extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String age = req.getParameter("age");

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        writer.print("<h1>我是 /bye 资源</h1>");
        writer.println("<p> 再见" + name + ", 年龄 " + age + "</p>");
    }
}
