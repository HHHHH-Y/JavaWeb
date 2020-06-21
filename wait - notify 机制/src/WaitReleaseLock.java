/**
 * Created with IntelliJ IDEA.
 * Description: wait 释放锁
 * User: HHH.Y
 * Date: 2020-06-21
 */
public class WaitReleaseLock {
    // 创建两个 object 的对象
    static Object o1 = new Object();
    static Object o2 = new Object();

    static class SubThread extends Thread {
        @Override
        public void run() {
            try {
                synchronized (o1) {
                    synchronized (o2) {
                        // 释放锁
                        o2.wait();
                        // 请求锁
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SubThread subThread = new SubThread();
        subThread.start();

        // 让主线程主动放弃 CPU, 保证让子线程运行起来
        Thread.sleep(1000);

        // 子线程已经运行起来了
       synchronized (o2) {
           System.out.println("o2 加锁成功"); // 因为 o2 进入 wait 状态后会释放锁, 所以会执行
       }

       synchronized (o1) {
           System.out.println("o1 加锁成功"); // o1 一直没有释放锁
       }
    }
}
