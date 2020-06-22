import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: 多个生产者, 一个消费者
 * 5 个生产者, 一个消费者
 * User: HHH.Y
 * Date: 2020-06-21
 */
public class MyBlockingQueueMain2 {
    private static final MyBlockingQueue queue = new MyBlockingQueue();

    static class Producer extends Thread {
        Producer(int i) {
            super("生产者 - " + i);
        }
        @Override
        public void run() {
            try {
                for (int i = 0; true; i++) {
                    queue.push(i);
                    System.out.println(getName() + " 放入: " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 预期是: 5个线程加起来一共放16个数据之后就会全部阻塞, 直到被消费
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // 代表有 5个生产者
        for (int i = 0; i < 5; i++) {
            Producer producer = new Producer(i);
            producer.start();
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
//            System.out.println("输入回车, 进行消费");
//            scanner.nextLine();
            int i = queue.pop(); // 消费者只调用了一个 notify
            System.out.println("消费者消费了: " + i);
        }
    }

}
