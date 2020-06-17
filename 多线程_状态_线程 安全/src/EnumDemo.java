/**
 * Created with IntelliJ IDEA.
 * Description: 线程的状态是一个枚举类型
 * User: HHH.Y
 * Date: 2020-06-17
 */
public class EnumDemo {
    enum 性别 {
        男, 女, 神秘人;
    }

    // 创建了一个 Enum 的子类, 子类的名称是"性别"
    // 同时定义了对象: 男 和 女

    public static void main(String[] args) {
        性别 sex = 性别.男;
        性别 sex2 = 性别.女;
        System.out.println(sex);
        System.out.println(sex2);

        性别[] values = 性别.values();
        for (性别 s:values) {
            System.out.println(s);
        }
    }
}
