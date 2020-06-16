/**
 * Created with IntelliJ IDEA.
 * Description: 等待一个线程 join()
 * 有时, 我们需要等待一个线程完成它的工作后, 才能进行自己的下一步工作
 * User: HHH.Y
 * Date: 2020-06-16
 */
public class JoinDemo {
    static class B extends Thread {
        B () {
            super("B");
        }

        @Override
        public void run() {
            long r = 0;
            for (int i = 0; i < 100_0000_000L; i++) {
                r += i;
            }
            System.out.println("B 停止运行");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        B b = new B();
        b.start();

        System.out.println("我不走了, 什么时候 B 停止, 我才继续");
        b.join(); // main 线程会主动放弃 CPU, 等待B运行, 直到 B 停止, 才重新就如就绪队列
        System.out.println("B 结束了, 我就继续");
    }
}
