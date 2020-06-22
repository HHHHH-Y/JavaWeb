import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: 单生产者, 单消费者
 * 当生产者生产的速度大于消费者消费的速度时, 会发生这样的现象: 消费者消费一次, 生产者就生产一次
 * 当消费者消费的速度大于生产者生产的素的, 会发生这样的情况: 生产者生产一次, 消费者就消费一次
 * User: HHH.Y
 * Date: 2020-06-21
 */
public class MyBlockingQueueMain {
    private static final MyBlockingQueue queue = new MyBlockingQueue();

    /*static class Producer extends Thread {
        @Override
        public void run() {
            // 生产者一直生产
            try {
                for (int i = 0; true; i++) {
                    queue.push(i);
                    System.out.println("生产者放入: " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/

    static class Customer extends Thread {
        @Override
        public void run() {
            // 消费者一直消费
            try {
                while (true) {
                    int i = queue.pop();
                    System.out.println("消费者消费: " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*Producer producer = new Producer();
        producer.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            // 消费者一直消费
            System.out.println("输入任何值, 进行一次消费");
            scanner.nextLine();
            int i = queue.pop();
            System.out.println("消费者消费: " + i);
        }*/

        Customer customer = new Customer();
        customer.start();

        // 生产者一直生产
        for (int i = 0; true; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("输入任何值, 进行一次生产");
            scanner.nextLine();

            queue.push(i);
            System.out.println("生产者生产: " + i);
        }
    }
}
