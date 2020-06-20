/**
 * Created with IntelliJ IDEA.
 * Description: 饿汉模式的单利
 * 1. 保存仅有的一个对象至一个静态属性中
 * 2. 一开始就对这个对象进行初始化(new)(静态代码块 / 直接初始化)
 * 3. 提供一个方法可以返回这个对象
 * 4. 避免无意中犯错, 将这个类的构造方法标记成 private 的
 * User: HHH.Y
 * Date: 2020-06-20
 */
public class SingletonStarve {
    // 1. 我需要保存我仅有的一个对象(保存到一个静态属性中)
    private static final SingletonStarve instance;

    // 2. 饿汉模式, 也就是一开始就初始化
    // 静态代码块 or 直接初始化都可以
    static {
        instance = new SingletonStarve();
    }

    // 3. 如果需要该类的对象, 只能使用这个 instance 指向的对象
    // 所以需要提供方法, 把对象返回
    public static SingletonStarve getInstance() {
        return instance;
    }

    // 4. 尽可能的避免有人无意中构造出该类的对象
    // 所以将类的构造方法标记为 private, 减少误用可能
    private SingletonStarve() {
    }

}
