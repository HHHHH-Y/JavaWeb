/**
 * Created with IntelliJ IDEA.
 * Description:  懒汉模式的单例
 * 1. 保存仅有的一个对象引用至静态属性中
 * 2. 提供一个方法, 当需要使用时再进行初始化并返回
 * 3. 为了避免无意中构造对象, 将这个类的构造方法标记成 private 的
 *
 * 懒汉模式天生不是线程安全的
 * User: HHH.Y
 * Date: 2020-06-20
 */
public class SingletonLazy {
    // 需要保存该类唯一对象的引用
    private static  SingletonLazy instance = null;

    // 加上一个 synchronized 关键字, 就可以保证线程安全, 但不是最优化的版本
    public static synchronized SingletonLazy getInstance() {
        if(instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }

    public static SingletonLazy getInstanceUnsafe() {
        // getInstance 被调用的时候, 代表有人需要该类的对象了
        // 所以应该实例化出对象来了  --- 按需加载
        /*if(instance == null) {
            // 代表对象还没有被初始化
            // 需要先初始化
            instance = new SingletonLazy();
            return instance;
        } else {
            // 代表 instance 已经指向了一个对象
            // 所以我们不需要实例化对象了
            // 直接返回该对象的引用
            return instance;
        }*/

        if(instance == null) { // 判断的时候 instance 还是 null
            // 到这里实例化的时候, instance 有可能就不是 null
            // 换句话说, 没有保证 if(instance == null) { instance = new SingletonLazy;} 的原子性
            instance = new SingletonLazy();
        }
        return instance;
    }

    private SingletonLazy() {
    }
}
