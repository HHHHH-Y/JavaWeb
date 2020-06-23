/**
 * Created with IntelliJ IDEA.
 * Description: 如果 notify 方法先于 wait 方法
 * notify 没有状态保存的
 * notify 早于 wait 之前运行, 什么都唤不醒
 * notify 只能唤醒当前时刻上已经 wait 的线程, 无法唤醒未来 wait 的线程
 * 换句话说, notify 一定要在 wait 之后运行才有意义
 * User: HHH.Y
 * Date: 2020-06-21
 */
public class NotifyFirst {
    static class SubThread extends Thread {
        @Override
        public void run() {
            try {
                // 这里的 this, 代表的就是 SubThread
                synchronized (this) {
                    this.wait();
                    System.out.println("子线程被唤醒");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 先执行 notify, 再让子线程运行
    public static void main(String[] args) {
        // subThread 明明是一个局部变量, 但还是共享的
        SubThread subThread = new SubThread();

        // 先调用 notify, 然后再让子线程运行, 再调用 wait
        // subThread 指向的对象, 就是子线程代码中的 this 指向的 对象
        synchronized (subThread) {
            subThread.notify();
        }

        subThread.start();
    }
}
