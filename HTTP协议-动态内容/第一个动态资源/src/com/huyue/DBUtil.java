package com.huyue;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description: 直接将所有的单词已经其中文含义放进数据库中, 从数据库中进行查询
 * 使用单利模式中的懒汉模式 -- 需要进行二次判断
 * User: HHH.Y
 * Date: 2020-07-06
 */
public class DBUtil {
    private static volatile DataSource dataSource;

    public static Connection getConnection() throws SQLException {
        if(dataSource == null) {
            synchronized (DBUtil.class) {
                // 懒汉模式下要进行二次判断
                if(dataSource == null) {
                    dataSource = initDataSource();
                }
            }
        }
        return dataSource.getConnection();
    }

    private static DataSource initDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/july_6?useSSL=false&characterEncoding=utf-8");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("huyuelover1017");

        return mysqlDataSource;
    }
}
