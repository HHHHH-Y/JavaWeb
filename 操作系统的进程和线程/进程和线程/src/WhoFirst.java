/**
 * Created with IntelliJ IDEA.
 * Description: WhoFirst 的结果是不固定的
 * 将线程加入到就绪队列的时机是固定的, 但是什么时候被调度到 CPU 上是不确定(随机), 什么时候从 CPU 上调度下来也是不确定(随机)的
 * User: HHH.Y
 * Date: 2020-06-15
 */
public class WhoFirst {
    static class PrintA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("A");
            }
        }
    }

    static class PrintB extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("B");
            }
        }
    }

    public static void main(String[] args) {
        PrintA a = new PrintA();
        PrintB b = new PrintB();
        a.start();
        b.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("main");
        }
    }
}
