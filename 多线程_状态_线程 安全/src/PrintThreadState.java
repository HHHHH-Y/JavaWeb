/**
 * Created with IntelliJ IDEA.
 * Description: 打印一下线程状态
 * User: HHH.Y
 * Date: 2020-06-17
 */
public class PrintThreadState {
    public static void main(String[] args) {
        Thread.State[] values = Thread.State.values();
        for (Thread.State s:values) {
            System.out.println(s);
        }
    }
}
