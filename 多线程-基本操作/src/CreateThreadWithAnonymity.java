/**
 * Created with IntelliJ IDEA.
 * Description: 使用匿名类或者 Lambda 表达式创建线程
 * 创建线程的步骤:
 * 1. 通过重写 run 方法, 指定线程要执行的动作
 * 2. 创建 Thread 对象
 * 3. 调用 start() 方法, 将线程加入到 "就绪队列" 中
 * 4. 随后, 随着线程的调度, 获取到 CPU 进行运行
 * User: HHH.Y
 * Date: 2020-06-16
 */
public class CreateThreadWithAnonymity {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
           while (true) {
               System.out.println("我是子线程");
           }
        });
        t.start();

        while (true) {
            System.out.println("我是 main 线程");
        }
    }
}
