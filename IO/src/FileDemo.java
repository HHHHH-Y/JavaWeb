import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description: 关于 IO, 就是输入输出
 * 文件就是硬盘上一组数据的抽象概念
 * 文件主要分为两大类:
 * 1. 文件夹/目录(directory) --- 文件夹其实也是文件
 * 2. 普通文件 --- 文本/图片/视频/代码
 *
 * 一个文件主要有两组数据组成:
 * 1. 数据内容
 * 2. 元数据(管理数据)
 *    1. 文件名
 *    2. 路径
 *    3. 其他
 * 主流的文件系统, 都是用 " 树形结构 "管理文件夹之间的关系
 *
 * 每个"文件夹"都可以有 0个或者 多个 孩子的文件(包括文件夹 or 普通文件)
 * 每个普通文件, 一定是叶子节点
 * 如果"文件夹" 下没有其他孩子, 也可以看成叶子节点
 * 最终, 整个硬盘上的所有数据(不包括没有使用的空间)都被一棵(或者多棵)树管理起来
 *
 * 关于路径, 可以分为 绝对路径 和 相对路径
 * 绝对路径(absolute path): 无论"我"身在何处, 都可以根据该路径确定唯一的节点
 *                         从根出发, 到达节点的一条路径
 * 相对路径(relative path): 相对"我"当前身在的位置, 确定节点的路径
 *                         如果"我"的位置改变了, 那相对路径就可能找不到正确的节点了
 *                         "." 代表的是本身, ".."代表的是父节点
 * 有路径不代表有文件(有路径, 但实际中不存在该文件)
 *
 * User: HHH.Y
 * Date: 2020-06-25
 */
public class FileDemo {
    public static void main(String[] args) {
        //先使用绝对路径
        //String path = "D:\\毕设\\实践练习\\src\\Practice.java";
        // 1. 对应一个实际存在的文件
        // 2. 对应一个实际不存在的文件
        // 3. 对应一个实际存在的目录
        // 4. 修改一下文件的属性, 观察下代码的打印有什么不同

        //String path = "D:\\毕设\\测试目录2";
        String path = "D:\\毕设\\a\\b\\c\\d";

        // 构建文件对象
        File file = new File(path);  // 路径对应的文件, 但文件可能实际上不存在

        // 常见属性
        // 文件是否是"普通文件"
        //System.out.println(file.isFile());
        // 文件是否是文件夹
        //System.out.println(file.isDirectory());
        //System.out.println(file.isHidden());
        System.out.println(file.exists());
        // 获取绝对路径
        //System.out.println(file.getAbsolutePath());
        // 获取相对路径
        //System.out.println(file.getPath());
        //System.out.println(file.getName());
        //System.out.println(file.getParent());
        //System.out.println(file.canRead());
        //System.out.println(file.canWrite());
        //System.out.println(file.canExecute());

        // 测试目录\hello.txt
        // 要创建 hello.txt文件, 要求测试目录首先存在
        // 但这里, 测试目录不存在, 所以会出错
        // 1. 演示创建成功
        // 2. 演示文件已经存在
        // 3. 上一级目录都不存在
        //try {
            //boolean newFile = file.createNewFile();   // 创建一个普通文件
            // 若文件不存在, 就可以创建一个新的"普通文件"
            // 若文件已经存在, 就不可以再创建了
            //System.out.println(newFile);
        //} catch (IOException e) {
            //e.printStackTrace();
        //}

        // 只能创建一个文件夹, 不能创建多个
//        boolean mkdir = file.mkdir();
//        System.out.println(mkdir);

        // 创建多个文件夹
        System.out.println("会把中间没有的目录, 循环创建出来");
        boolean mkdirs = file.mkdirs();
        System.out.println(mkdirs);
    }
}
