import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 计算斐波那契数列的值, 一旦计算时间过长, 就只能等待计算, 无法继续干别的事
 *
 * 使用多线程编程:
 * 1. 主线程负责通过 Scanner 读取用户的输入
 * 2. 专门启动其它线程, 用来负责计算
 * User: HHH.Y
 * Date: 2020-06-15
 */
public class BlockScene {
    private static long fib(int n) {
        // 时间复杂度为 O(2 ^ n), 时间复杂度非常高
        if(n == 1 || n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
    private static class FibThread extends Thread {
        private final int n;
        FibThread(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            System.out.printf("fib(%d) = %d%n", n, fib(n));
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // 主线程只负责用来读取用户的输入
            int n = scanner.nextInt();
            // 每次的计算交给一个新的线程处理
            // 需要使用start()才可以运行run方法
            new FibThread(n).start();
        }
    }
}
