package tcp.echo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 把打印日志的代码进行封装
 * User: HHH.Y
 * Date: 2020-06-30
 */
public class Logger {
    public static void debug(String message) {
        System.out.printf("%s: %s: Debug: %s%n", Thread.currentThread().getName(), new Date(), message);
    }

    public static void error(String message) {
        System.out.printf("%s: %s: ERROR: %s%n",  Thread.currentThread().getName(), new Date(), message);
    }
}


