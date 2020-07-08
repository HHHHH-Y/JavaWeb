package com.huyue;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-07-08
 */
public class DBUtil {
    private static volatile DataSource dataSource = null;
    public static Connection getConnection() throws SQLException {
        if(dataSource == null) {
            synchronized (DBUtil.class) {
                if(dataSource == null) {
                    dataSource = initDataSource();
                }
            }
        }
        return dataSource.getConnection();
    }

    private static DataSource initDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("huyuelover1017");
        mysqlDataSource.setDatabaseName("july_6");
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setCharacterEncoding("utf8");

        return mysqlDataSource;
    }
}
