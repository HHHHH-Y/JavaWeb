/**
 * Created with IntelliJ IDEA.
 * Description: yield() 主动放弃CPU, 但是仍然保有抢占CPU的资格
 *              RUNNING ->  READY
 *
 * User: HHH.Y
 * Date: 2020-06-17
 */

// 不使用 yield, A 和 B 顺序基本上一样多. 使用了 yield, 基本上看不到 B 了
public class YieldDemo {
    static class A extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("A");
            }
        }
    }

    static class B extends Thread {
        @Override
        public void run() {
            while (true){
                System.out.println("B");
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.start();
        b.start();
    }
}
