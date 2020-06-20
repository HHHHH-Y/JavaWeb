/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-06-20
 */
public class Demo {
    public static void main(String[] args) {
        // 由于 SingletonStarve 的构造方法是 private 的, 所以会有语法错误
        // SingletonStarve mistake = new SingletonStarve();

        // 如果要用到该类的对象
        SingletonStarve right = SingletonStarve.getInstance();
    }
}
