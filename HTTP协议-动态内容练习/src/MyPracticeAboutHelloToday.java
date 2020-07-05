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
public class MyPracticeAboutHelloToday extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("target");

        resp.setContentType("text/html; charset = utf-8");
        PrintWriter writer = resp.getWriter();

        writer.print("<h1>我是 /a 资源</h1>");
        writer.println("<p>Hello Today</p>");
    }
}
