/**
 * Created with IntelliJ IDEA.
 * Description: Synchronized 表示同步
 * 修饰方法定义 -- 同步方法
 * 修饰代码块 -- 同步代码块
 * User: HHH.Y
 * Date: 2020-06-19
 */
public class SynchronizedSyntaxDemo {
    /**
     * 修饰方法
     * 可以修饰普通方法
     * 可以修饰静态方法
     */
    public synchronized void method() {

    }
    public static synchronized void staticMethod() {

    }

    /**
     * 修饰代码块
     */
    public void otherMethod() {
        // 括号里跟着指向对象的引用，引用不能是 null
        // 只要是引用就行，没有任何要求
        Object o = new Object();
        synchronized (o) {
        }
        synchronized (this) {
        }
        // 反射中，指向当前类对象
        synchronized (SynchronizedSyntaxDemo.class) {
        }
    }
}
