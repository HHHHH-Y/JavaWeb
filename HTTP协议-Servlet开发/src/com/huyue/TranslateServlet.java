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

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-07-08
 */
public class TranslateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 读取用户输入的英文单词
        String english = req.getParameter("english");
        // 2. 对用户输入的英文单词进行翻译
        String chinese = translate(english);
        // 3. 输出 html 的内容
        resp.setContentType("text/html; charset = utf-8");
        PrintWriter writer = resp.getWriter();
        writer.printf("<p>%s 的中文翻译是: %s</p>%n", english, chinese);
    }

    // 从数据库中提取数据
    private String translate(String english) throws ServletException {
        try(Connection connection = DBUtil.getConnection()) {
            String sql = "select chinese from dictionary where english = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, english);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    if(resultSet.next()) {
                        return resultSet.getString("chinese");
                    } else {
                        return "不认识的单词";
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
