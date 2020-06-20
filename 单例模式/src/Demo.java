/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-06-20
 */
public class Demo {
    static class SubThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                SingletonStarve instance = SingletonStarve.getInstance();
                System.out.println(instance);
                // 饿汉模式下, 是否会存在线程安全问题?
                // 也就是说, 是否可以保证构造出唯一的对象吗?
                // 没有线程安全问题
            }
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new SubThread().start();
        }
        // 由于 SingletonStarve 的构造方法是 private 的, 所以会有语法错误
        // SingletonStarve mistake = new SingletonStarve();

        // 如果要用到该类的对象
        SingletonStarve right = SingletonStarve.getInstance();
        SingletonLazy rightLazy = SingletonLazy.getInstance();
    }
}
