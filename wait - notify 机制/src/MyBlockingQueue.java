/**
 * Created with IntelliJ IDEA.
 * Description: 实现一个基于循环数组的阻塞队列
 * wait 一般都建议放在 while 中, 防止生产者唤醒生产者/ 消费者唤醒消费者 而引起的错误
 *
 * 当容量较小的时候, 且生产者数量 >> 消费者数量时, 会出现所有的线程(生产者, 消费者)都进入了 wait 的状态
 * 所以, 需要使用 notifyAll, 保证生产者一定可以就将消费者唤醒, 消费者也一定可以将生产者唤醒
 * User: HHH.Y
 * Date: 2020-06-21
 */
public class MyBlockingQueue {
    private int[] array = new int[1]; // 存放数据的数组
    private volatile int size = 0;     // 当前已有元素个数
    private int fromIndex = 0;         // 指向队头元素的下标
    private int rearIndex = 0;         // 指向下一个可用位置的下标

    public synchronized void push(int element) throws InterruptedException {
        // 判断队列是否已满
        // 当一个生产者被唤醒后, 需要判断一下队列是否是满的, 如果是满的, 说明它是被生产者唤醒的, 应该继续 wait
        while (size >= array.length) {
            wait(); // 等着调用 pop 的线程唤醒, 所以在 pop 中实现 notify
        }
        // 否则放入队列中
        array[rearIndex] = element;
        size++; // 破坏了原子性
        rearIndex = (rearIndex + 1) % array.length;
        notifyAll(); // 唤醒调用 pop 时的阻塞线程
    }

    public synchronized int pop() throws InterruptedException {
        // 判断队列是否已空
        // 当一个消费者被唤醒时, 需要判断一下队列是否是空的, 如果是空的, 说明它是被消费者唤醒的, 应该继续 wait
        while (size == 0) {
            wait();
        }
        // 否则就取出队列中的数据
        int element = array[fromIndex];
        fromIndex = (fromIndex + 1) % array.length;
        size--; // 破坏了原子性

        // 队列中一定存在空间
        notifyAll(); // 唤醒调用 push 时阻塞的线程
        return element;
    }

    public int size() {
        return size; // 可能会有内存可见性问题
    }
}
