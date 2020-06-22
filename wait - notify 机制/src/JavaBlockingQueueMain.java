import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * Description: Java 中的阻塞队列(BlockingQueue)
 * BlockingQueue<T> 是一个接口
 *
 * ArrayBlockingQueue: 数组实现, 循环实现, 有容量上限(带容量)
 * LinkedBlockingDeque: 链表实现, 队列没有容量上限*(不带容量)
 * PriorityBlockingQueue: 堆实现, 优先级阻塞队列
 * 都是 BlockingQueue<T> 的实现类
 *
 * User: HHH.Y
 * Date: 2020-06-22
 */
public class JavaBlockingQueueMain {
    static BlockingQueue<Integer> q1 = new ArrayBlockingQueue<>(10);
    static BlockingQueue<Integer> q2 = new LinkedBlockingDeque<>();
    static BlockingQueue<Integer> q3 = new PriorityBlockingQueue<>();

    static class Producer extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; true; i++) {
                    q1.put(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Producer producer = new Producer();
            producer.start();
        }

        while (true) {
            Integer take = q1.take();
            System.out.println(take);
        }
    }
}
