/**
 * Created with IntelliJ IDEA.
 * Description: 如何不使用 Thread 中设计的方式通知线程停止
 * User: HHH.Y
 * Date: 2020-06-15
 */
public class AboutThreadStop2 {
    // 定义了一个类变量, 线程之间可以共享
    // B 线程可以通知观察该变量, 知道是否有人让它停止
    private static volatile boolean isInterrupted = false;

    static class B extends Thread {
        B () {
            super("B");
        }

        @Override
        public void run() {
            System.out.println("我正在追星");

        }
    }
}
