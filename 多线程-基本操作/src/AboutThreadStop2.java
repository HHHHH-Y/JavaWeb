import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: 如何不使用 Thread 中设计的方式通知线程停止
 *
 * 对于这个程序而言:
 * 主线程只负责通知 B 线程停止
 * 对于 B 如何停止, 如何退出, 由 B 自行决定,
 *
 *
 * User: HHH.Y
 * Date: 2020-06-15
 */
public class AboutThreadStop2 {
    // 定义了一个类变量, 线程之间可以共享
    // B 线程可以通过观察该变量, 知道是否有人让它停止
    private static volatile boolean isInterrupted = false; // 初始化被中断变量为 false
    static class B extends Thread {
        B () {
            super("B");
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("我正在种太阳");
                    Thread.sleep(1000);
                    System.out.println("是否有人让我停止: " + isInterrupted);
                    if(isInterrupted) {
                        break;
                    }
                } catch (InterruptedException e) {
                    // 通过 InterruptedException 异常的方式通知有人让你停止, 这样的通知是非常及时的
                    System.out.println("有人让我停止, 但由于我正在 sleep, 所以我收到了这个异常!");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        B b  = new B();
        b.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("准备通知 B 线程停止运行");
        isInterrupted = true;
        System.out.println("已经通知 B 线程停止运行");
    }

}
