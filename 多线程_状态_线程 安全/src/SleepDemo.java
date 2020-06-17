import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description: 休眠当前线程
 * 线程休眠有两种方式:
 * 1. Thread.sleep(毫秒);
 * 2. TimeUnit.SECONDS/MINUTES/HOURS.sleep(), 根据不同的时间单位, sleep中放置的时间也是不同的
 * User: HHH.Y
 * Date: 2020-06-17
 */
public class SleepDemo {
    public static void main(String[] args) {
        System.out.println(1);
        try {
            Thread.sleep(1);   // 休眠 1 毫秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(2);
        try {
            TimeUnit.SECONDS.sleep(1);   // 休眠 1 秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(3);
        try {
            TimeUnit.MINUTES.sleep(1);    // 休眠 1 分钟
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(4);
    }
}
