import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: 一个生产者, 一个消费者
 * User: HHH.Y
 * Date: 2020-06-21
 */
public class MyBlockingQueueMain {
    private static final MyBlockingQueue queue = new MyBlockingQueue();

    static class Producer extends Thread { // 生产者
        @Override
        public void run() {
            try {
                for (int i = 0; true; i++) {
                    queue.push(i);
                    System.out.println("生产者放入: " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer();
        producer.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("输入任何值，进行一次消费");
            scanner.nextLine();
            int i = queue.pop();
            System.out.println("消费者消费: " + i);
        }
    }
}
