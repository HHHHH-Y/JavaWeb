package database;
/**
 * 将这个饿汉模式的单例修改成一个懒汉模式的单例
 * 线程安全, 高性能
 */

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-06-20
 */
public class DBUtil {
    // 整个应用期间, 只需要一个 DataSource
    // DataSource 就是一个单例的应用
    private static volatile DataSource dataSource;

    private static DataSource initDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("huyuelover1017");
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setCharacterEncoding("utf8");

        return mysqlDataSource;
    }
    public static Connection getConnection() throws SQLException {
        if(dataSource == null) {
            synchronized (DBUtil.class) {
                if(dataSource == null) {
                    // 在进行赋值之后, 还是会出现代码重排序的效果, 相当于是修改了 dataSource, 破坏了代码可见性的效果
                    // dataSource = mysqlDataSource;
                    /*MysqlDataSource mysqlDataSource = new MysqlDataSource();
                    mysqlDataSource.setServerName("127.0.0.1");
                    mysqlDataSource.setPort(3306);
                    mysqlDataSource.setUser("root");
                    mysqlDataSource.setPassword("huyuelover1017");
                    mysqlDataSource.setUseSSL(false);
                    mysqlDataSource.setCharacterEncoding("utf8");*/

                    // volatile 可见性进一步限制了重排序的效果
                    // 由于 dataSource 被 volatile 限制了, 所以任何对于 dataSource 的修改都会被其他线程所看到
                    // 所以不能轻易的修改 dataSource
                    /*dataSource = mysqlDataSource;*/
                    dataSource = initDataSource();
                }
            }
        }
        return dataSource.getConnection();
    }

}
