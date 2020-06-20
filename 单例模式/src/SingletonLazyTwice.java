/**
 * Created with IntelliJ IDEA.
 * Description: 懒汉模式单例 使用 synchronized + 二次判断 + volatile修饰---> 保证了是 线程安全 + 性能较高
 *
 * 按需加载的好处, 减少了空间的浪费
 * User: HHH.Y
 * Date: 2020-06-20
 */
public class SingletonLazyTwice {
    // 避免了代码重排序
    private static volatile SingletonLazyTwice instance = null;

    // 以下写法是线程安全的么?
    // 不是线程安全的, 因为这把锁加的毫无意义
    public static SingletonLazyTwice getInstanceUnsafe() {
        if(instance == null) {
            // 提高了性能
            synchronized (SingletonLazyTwice.class) {
                    instance = new SingletonLazyTwice();
            }
        }

        return instance;
    }

    // 通过二次判断, 既保证了线程安全, 又减少了抢锁的次数
    // 这个版本中仍存在着一个小错误
    public static SingletonLazyTwice getInstanceWrong() {
        if(instance == null) {
            synchronized (SingletonLazyTwice.class) {
                // instance 可能是 null, 也可能不是 null
                if(instance == null) {
                    // 这里因为锁的存在, 只会执行一次
                    // 保证了单例
                    instance = new SingletonLazyTwice();
                } else {
                    // 抢锁之前, instance 是 null
                    // 但是从抢锁到抢锁成功这段期间, instance 已经不是 null
                    // 代表已经被之前抢到的锁的线程实例化好了
                    // 也就什么都不需要做了
                }
            }
        }
        return instance;
    }

    public static SingletonLazyTwice getInstance() {
        if(instance == null) {
            synchronized (SingletonLazyTwice.class) {
                if(instance == null) {
                    // 下面的代码, 可能会被重排序
                    instance = new SingletonLazyTwice();
                }
            }
        }
        return instance;
    }

    private SingletonLazyTwice() {
    }
}
