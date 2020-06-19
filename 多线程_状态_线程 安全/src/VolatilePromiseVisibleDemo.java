/**
 * Created with IntelliJ IDEA.
 * Description: Volatile 可以保证内存的可见性
 * User: HHH.Y
 * Date: 2020-06-19
 */
public class VolatilePromiseVisibleDemo {
    // run 这个变量(静态属性)是共享的
    // 不加上 volatile，子线程很可能看不到 run 值的修改
    // 加上 volatile, 子线程一定可以看到 run 的值发生了修改
    private static volatile boolean run = true;

    static class Runner extends Thread{
        @Override
        public void run() {
            // run 变成 false 才可以退出
            // 由于内存可见性问题，子线程看不到 run 的值被修改了
            while (run) {
            }
            System.out.println("Runner 退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        runner.start();

        Thread.sleep(2000); // 主线程放弃 2s CPU，保证后边的代码执行时，子线程已经运行起来了

        System.out.println("2s 已到");
        run = false;
        // 正常情况下，主线程到这里就退出了
        // 随后，因为 run == false，所以，子线程也会退出
        // 进而 JVM 结束
    }
}
