package com.huyue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-07-06
 */
public class TranslateServlet extends HttpServlet {

    // select chinese from dictionary where english = ?
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String english = req.getParameter("english");
        // TODO: 没有任何的错误处理
        // 查找相应的单词意思, 如果有就返回中文含义, 如果没有就默认返回: "不认识的单词"
        String chinese;
        // select chinese from dictionary where english = ?
        try(Connection connection = DBUtil.getConnection()) {
            String sql = "select chinese from dictionary where english = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, english);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // 返回了一行
                        chinese = resultSet.getString("chinese");
                    } else {
                        chinese = "不认识的单词";
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        resp.setContentType("text/html; charset = utf-8");
        PrintWriter writer = resp.getWriter();

        writer.println("<h1>翻译结果</h1>");
        writer.print("<p>" + english + " 的意思是: " + chinese + "</p>");
    }
}
