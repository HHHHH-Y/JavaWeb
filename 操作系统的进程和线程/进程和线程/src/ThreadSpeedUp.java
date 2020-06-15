/**
 * Created with IntelliJ IDEA.
 * Description: 并发可以提升速度
 * 多线程编程可能可以提升速度
 * User: HHH.Y
 * Date: 2020-06-15
 */
public class ThreadSpeedUp {
    private static final long C = 10;
    private static final long N = 10_0000_0000L;

    private static class Calc extends Thread {
        @Override
        public void run() {
            long r = 0;
            for (int i = 0; i < N; i++) {
                r += i;
            }
            System.out.println(r);
        }
    }
    private static void 并行方式计算() throws InterruptedException {
        Long b = System.currentTimeMillis();
        // 一共启动 C 个线程执行
        // 需要再启动 C - 1 个线程
        // 每个线程, 只需要计算 1 + ... + N 即可
        Calc[] thread = new Calc[(int)C - 1];

        for (int i = 0; i < C - 1; i++) {
            thread[i] = new Calc(); // 创建线程
            thread[i].start(); // 将线程加入到就绪序列中
        }

        // 主线程自己也要执行一次
        long r = 0;
        for (int i = 0; i < N; i++) {
            r += i;
        }
        System.out.println(r);

        // 必须等待其他 N - 1 个线程执行结束, 才算真正的执行结束了
        for (int i = 0; i < C - 1; i++) {
            // 等待 thread[i] 线程结束
            thread[i].join();
        }
        Long e = System.currentTimeMillis();

        System.out.printf("并行方式下, 耗时: %.2f秒%n", (e - b) / 1000.0);
    }

    private static void 串行方式计算() {
        Long b = System.currentTimeMillis();
        for (int i = 0; i < C; i++) {
            long r = 0; // r 用来计算串行计算得到的结果
            for (int j = 0; j < N; j++) {
                r += j; // r 总共遍历 C 次 N
            }
            System.out.println(r);
        }
        Long e = System.currentTimeMillis();
        System.out.printf("串行模式下, 耗时: %.2f秒%n", (e - b) / 1000.0);
    }
    public static void main(String[] args) throws InterruptedException {
        // 预热, 防止影响速度
        for (int i = 0; i < 20; i++) {
            long r = 0;
            for (long j = 0; j < N; j++) {
                r += j;
            }
        }
        串行方式计算();
        并行方式计算();
    }
}
