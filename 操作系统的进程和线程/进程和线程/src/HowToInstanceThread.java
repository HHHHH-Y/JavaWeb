/**
 * Created with IntelliJ IDEA.
 * Description: Java语言中, 创建一个线程出来, 本质核心都需要有一个 Thread对象
 * 创建线程的基本方法(创建一个 Thread 对象 + 关联任务):
 * 1. 继承一个 Thread 类, 并重写 run 方法, 直接 new 该类的对象, 得到了一个线程
 * 2. 实现一个 Runnable 接口, 并重写 run 方法, 需要 new Thread(创建任务对象), 得到一个线程对象
 * 3. 继承一个 Thread 类, 重写 run 方法, 但是把该类的对象当做 Runable 对象处理, 同 2
 *
 * 以上均为创建一个线程, 需要通过 线程对象.start() 方法将这个线程加入到就绪队列中, 当分配到 CPU 时, 执行自己的 run 方法
 * User: HHH.Y
 * Date: 2020-06-15
 */
public class HowToInstanceThread {
    // 线程
    static class A extends Thread {
        @Override
        public void run() {
            System.out.println("我是 A 类");
        }
    }

    // 任务
    static class B implements Runnable {
        @Override
        public void run() {
            System.out.println("我是 B 类");
        }
    }

    public static void main(String[] args) {
        Thread thread;
        {
            // 1. 直接继承一个 Thread 类, 并重写 run 方法, 得到的就是一个线程
            A a = new A(); // A 继承了 Thread 类
            thread = a;
            thread.start();
        }

        {
            // 2. 实现了 Runnable 接口, 并重写了 run 方法
            B b = new B();
            // thread = b; 这个是错误的
            thread = new Thread(b);
            thread.start();
        }

        {
            // 3. 继承一个 Thread类, 并重写 run 方法, 将这个类作为 Runnable 类使用
            A a = new A();
            thread = new Thread(a);
            thread.start();
        }
    }
}
