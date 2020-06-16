import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-06-15
 */
public class AboutThreadStop {
    static class B extends Thread {
        B() {
            super("B");
        }

        @Override
        public void run() {
            Thread t = Thread.currentThread();
            while (true) {
                System.out.println("我正在追星");
                try {
                    Thread.sleep(1000);
                    boolean interrupted = t.isInterrupted();
                    // 由于不知道 B 线程是否处于 sleep 状态, 所以两种情况都需要进行考虑
                    System.out.println("是否有人让我停止: " + interrupted);
                } catch (InterruptedException e) {
                    System.out.println("有人让我停止运行, 但因为我正在 sleep, 所以我收到了这个异常");
                }
            }
        }
    }

    public static void main(String[] args) {
        B b = new B();
        b.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("准备通知B停止");
        b.interrupt(); // 用于通知 B 停止
        System.out.println("已经通知B停止");


    }
}
