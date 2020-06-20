/**
 * Created with IntelliJ IDEA.
 * Description: 饿汉模式的单例
 * 1. 保存仅有的一个引用至一个静态属性中
 * 2. 一开始就对这个对象进行初始化(new)(静态代码块 / 直接初始化)
 * 3. 提供一个方法可以返回这个对象
 * 4. 避免无意中犯错, 将这个类的构造方法标记成 private 的
 *
 * 饿汉模式天生是线程安全的
 *
 * 一开始就初始化好, 占用了一部分空间
 * User: HHH.Y
 * Date: 2020-06-20
 */

// 通过 final 修饰类, 避免有人定义该类的子类来构造对象(有时候)
public final class SingletonStarve {
    // 1. 我需要保存我仅有的一个引用(保存到一个静态属性中)
    // final 修饰了, 保证了原子性
    private static final SingletonStarve instance;

    // 2. 饿汉模式, 也就是一开始就初始化
    // 静态代码块 or 直接初始化都可以
    static {
        // 只发生在 SingletonStarve 被加载时执行
        // 而 SingletonStarve 的加载, 只发生一次
        instance = new SingletonStarve();
    }

    // 3. 如果需要该类的对象, 只能使用这个 instance 指向的对象
    // 所以需要提供方法, 把对象返回
    public static SingletonStarve getInstance() {
        // 该方法会被调用若干次, 但返回的都是指向同一个对象的引用
        return instance;
    }

    // 4. 尽可能的避免有人无意中构造出该类的对象
    // 所以将类的构造方法标记为 private, 减少误用可能
    private SingletonStarve() {
    }

}
