/**
 * Created with IntelliJ IDEA.
 * Description:
 *  启动两个线程, 同时操作一个变量 v = 0;
 *  一个线程对变量执行 N 次 v++;
 *  另一个线程对变量执行 N 次 v--;
 *  问: 当两个线程都执行结束时, v 的值是多少?
 *  期望值是多少?     0
 *  实际值是多少?    随机
 * User: HHH.Y
 * Date: 2020-06-17
 */
public class ThreadUnsafeDemo {
    private static int v = 0;
    private static final int N = 10_000_000;

    static class Add extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < N; i++) {
                v++;
            }
        }
    }

    static class Sub extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < N; i++) {
                v--;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Add a = new Add();
        Sub b = new Sub();
        a.start();
        b.start();

        a.join();
        b.join();
        // 到这时, a 和 b 都结束了
        System.out.println(v);
    }
}
