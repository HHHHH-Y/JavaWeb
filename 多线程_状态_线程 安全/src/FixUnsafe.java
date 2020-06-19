/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-06-19
 */
public class FixUnsafe {
    private static long v = 0;
    private static final long N = 1_0000_0000L;

    static class Add extends Thread {
        @Override
        public void run() {
            synchronized (FixUnsafe.class) {
                for (int i = 0; i < N; i++) {
                    v++;
                }
            }
        }
    }

    static class Sub extends Thread {
        @Override
        public void run() {
            synchronized (FixUnsafe.class) {
                for (int i = 0; i < N; i++) {
                    v--;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Add a = new Add();
        Sub s = new Sub();
        a.start();
        s.start();

        a.join();
        s.join();
        System.out.println(v);
    }
}
