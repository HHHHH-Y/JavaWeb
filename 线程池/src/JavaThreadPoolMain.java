import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * Description: Java 中的线程池 (重要)
 * User: HHH.Y
 * Date: 2020-06-22
 */
public class JavaThreadPoolMain {
    // 工厂
    static class MyThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "我随便创建的");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);

        // 创建一个线程池
        // ExecutorService 是接口, ThreadPoolExecutor 是这个接口的实现类
        ExecutorService threadPool = new ThreadPoolExecutor(
                10, // 正式员工有 10 个
                10, // 临时员工有 0 个
                0,
                TimeUnit.SECONDS,
                queue,
                new MyThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy();
//                new ThreadPoolExecutor.DiscardPolicy() // 直接丢掉最新的任务
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; true; i++) {
            // 创建让线程池执行的任务
            Runnable target = new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            // 等同于把任务提交给了线程池
            // 线程池内部会选择一个空闲的线程去执行该任务
            System.out.println("提交第 " + i + " 个任务");
            System.out.println(queue.size());
            threadPool.execute(target); // 总共可以提交 13 次任务
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
