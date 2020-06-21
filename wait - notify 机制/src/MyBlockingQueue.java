/**
 * Created with IntelliJ IDEA.
 * Description: 实现一个基于循环数组的阻塞队列
 * User: HHH.Y
 * Date: 2020-06-21
 */
public class MyBlockingQueue {
    private int[] array = new int[16]; // 存放数据的数组
    private volatile int size = 0;     // 当前已有元素个数
    private int fromIndex = 0;         // 指向队头元素的下标
    private int rearIndex = 0;         // 指向下一个可用位置的下标

    public synchronized void push(int element) throws InterruptedException {
        // 如果队列已满, 就需要进入到阻塞队列
        if(size >= array.length) {
            wait();
        }
        // 否则放入队列中
        array[rearIndex] = element;
        size++; // 破坏了原子性
        rearIndex = (rearIndex + 1) % array.length;
        notify(); // 唤醒调用 pop 时的阻塞线程
    }

    public synchronized int pop() throws InterruptedException {
        // 如果队列已空, 就需要进入到阻塞状态
        if(size == 0) {
            wait();
        }
        // 否则就取出队列中的数据
        int element = array[fromIndex];
        fromIndex = (fromIndex + 1) % array.length;
        size--; // 破坏了原子性
        notify(); // 唤醒调用 push 时阻塞的线程
        return element;
    }

    public int size() {
        return size; // 可能会有内存可见性问题
    }
}
