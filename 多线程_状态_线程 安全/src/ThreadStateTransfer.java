import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description: 线程状态转换
 * User: HHH.Y
 * Date: 2020-06-17
 */
public class ThreadStateTransfer {
    static class SubThread extends Thread {
        @Override
        public void run() {
            /*Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            System.out.println("子线程即将退出");*/

            for (int i = 0; i < 50; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {

                }
            }
            // 子线程走完这里, 就终止了
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SubThread p = new SubThread();
        System.out.println(p.getState()); // NEW
        p.start();
        System.out.println(p.getState()); // RUNNABLE

        while (p.isAlive()) {
            System.out.println(p.getState());    // 看到的是 RUNNABLE 还是 TIMES_WAITING
                                                 // 理论上是可以看到 RUNNABLE de
            TimeUnit.SECONDS.sleep(1);   // 但实际运行中, 基本看不到 RUNNABLE 的
        }
        System.out.println(p.getState()); // TERMINATED
    }
}
