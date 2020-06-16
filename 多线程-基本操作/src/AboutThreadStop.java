import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:中断一个线程
 * 主线程中创建一个新的线程 (B)
 * 主线程只负责通知让 (B) 停下来
 *
 * 如果子线程中有 sleep 方法, 那么为了可以让子线程接收到中断信息, interrupted 和 InterruptedException 都得写上
 *
 * 1. 如果对方正在 sleep / wait 等, 直接通知异常, 此时会将 isInterrupted 修改回 false
 * 2. b.isInterrupted(), 这样的话, isInterrupted 会一直为 true
 * 3. Thread.interrupted(), 这种情况下, isInterrupted 会先变成 true, 最后又修改成为 false
 * User: HHH.Y
 * Date: 2020-06-15
 */
public class AboutThreadStop {
    static class B extends Thread {
        // 通过调用一个构造方法, 给这个新线程取名为 B
        B () {
            super("B");
        }

        @Override
        public void run() {
            Thread t = Thread.currentThread();
            while (true) {
                System.out.println("我正在种太阳");
                try {
                    Thread.sleep(1000);
                    boolean interrupted = t.isInterrupted();
                    System.out.println("是否有人让我停止运行: " + interrupted);
                    if(interrupted == true) {
                        // 干很多事
                        break; // break, return 都可以
                    }
                } catch (InterruptedException e) {
                    System.out.println("有人让我停止运行, 但因为我正在 sleep, 所以我收到了这个异常!");
                }
            }
        }
    }

    public static void main(String[] args) {
        B b = new B();
        b.start();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        System.out.println("准备通知 B 线程停止运行");
        // 通知 B 线程停止
        b.interrupt();
        System.out.println("已经通知 B 线程停止运行");
    }
}
