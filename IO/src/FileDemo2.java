import java.io.File;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: 文件重命名: 不区分文件 or 目录
 * 模拟重命名的操作
 * 模拟剪切粘贴操作
 * 如果目标文件已经存在: 无法移动成功
 * 1. 普通文件
 * 2. 目录
 * User: HHH.Y
 * Date: 2020-06-25
 */
public class FileDemo2 {
    public static void main(String[] args) {
//        File file = new File("测试目录1");
        // 进行文件重命名操作(直接重命名)
//        File dest = new File("测试目录");
//        boolean b = file.renameTo(dest);
//        System.out.println(b);

        // 进行剪切, 粘贴的操作
//        File dest = new File("a.txt");
//        boolean b = file.renameTo(dest);
//        System.out.println(b);


        /**
         * 对文件的删除操作: delete() 表示该文件立即被删除
         * 1. 普通文件的删除: 文件中是否包含内容完全没有影响
         * 2. 目录的删除: 必须是一个空目录(目录下没有其他文件的目录)
         *    非空目录的删除是怎么处理的? 需要把目录的文件全部删除掉, 才能删除该目录
         *
         * 从树的角度来看: 删除只允许删除树上的叶子结点
         * 注意: 这里的删除, 就是彻底的删除, 不是进入到"回收站"的删除
         *
         * deleteOnExit(): 该文件不是立即删除, 等 JVM 退出的时候再删除
         * 什么时候才会使用 deleteOnExit()? 有些文件, 应用运行期间需要该文件, 退出的时候不需要了, 就可以使用 deleteOnExit()
         */
//        File file = new File("测试目录\\非空目录");
//        boolean delete = file.delete();
//        System.out.println(delete);

//        Scanner scanner = new Scanner(System.in);
//        {
//            File file = new File("测试目录\\a.txt");
//            file.delete();
//            System.out.println("等待一会, 先观察 a.txt 是否已经被删除");
//            scanner.nextLine();
//        }
//        {
//            File file = new File("测试目录\\b.txt");
//            file.deleteOnExit();
//            System.out.println("等待一会, 先观察 b.txt 是否已经被删除");
//            scanner.nextLine();
//        }


        /**
         * 遍历目录下的孩子文件(不包括所有的子孙, 只是该目录下的孩子)
         *
         * 使用 listFiles() 得到的是 File 的数组
         * 使用 list(), 得到的是 String 的数组
         *
         * 如果是空目录: 返回长度不是 0 的数组
         * 如果是空目录: 返回长度是 0 的数组
         * 如果不是目录: 返回 null
         */
//        File file = new File("测试目录\\非空目录");
//        File[] files = file.listFiles();
//        for (File f : files) {
//            System.out.println(f.getAbsolutePath());
//        }

//        String[] list = file.list();
//        for (String s : list) {
//            System.out.println(s);
//        }

        File file = new File("测试目录\\a.txt");
        String[] list = file.list();
        for (String s:list) {
            System.out.println(s);
        }
    }
}
