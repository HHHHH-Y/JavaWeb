/**
 * Created with IntelliJ IDEA.
 * Description: 关于线程的常见方法 -- Thread 的常见方法
 * 属性相关:
 * 1. id: JVM内部, 每个线程的唯一标识
 * 2. name: 线程的名字, 为了打印日志, 调试使用
 * 3. 优先级: 变相的影响到线程被调度到的机会(建议型的属性)
 * 4. 状态: 类似讲讲进程调度时的进程状态
 * 5. 是否存活: 结合状态理解
 * 6. 是否是后台线程: 什么情况下 JVM 会退出
 *
 * User: HHH.Y
 * Date: 2020-06-15
 */

// 在打印属性的时候是完全随机的, 随机的交叉都是正确的, 但是, 一个线程内部的打印顺序是固定的
public class PrintThreadFields {
    // 只有继承了 Thread 类, 才能称为一个线程类
    static class SubThread extends Thread {
        // 创建线程对象时, 可以通过构造方法指定名字(名字不做任何要求, 可以重复)
        SubThread() {
            super("guyueyue");
        }
        @Override
        public void run() {
            // 子线程打印其对应的属性
            printFields();
        }
    }

    // 一开始就在一个线程之中
    public static void main(String[] args) {
        SubThread t = new SubThread();
        t.start();

        // 主线程打印其对应的属性
        printFields();
    }

    private static void printFields() {
        Thread t = Thread.currentThread(); // 谁调用了这条语句, 谁就是当前的线程
        long id = t.getId();
        System.out.println("线程的id: " + id + ": " + t.getId());
        System.out.println("线程的名字: " + id + ": " + t.getName());
        System.out.println("线程的优先级: " + id + ": " + t.getPriority());
        System.out.println("线程的状态: " + id + ": " + t.getState());
        System.out.println("线程是否存活: " + id + ": " + t.isAlive());
        System.out.println("线程是否是后台线程: " + id + ": " + t.isDaemon());
    }
}
