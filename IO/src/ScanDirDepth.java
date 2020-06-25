import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * Description: 深度优先遍历方法遍历整个目录
 * User: HHH.Y
 * Date: 2020-06-25
 */
public class ScanDirDepth {
    public static void main(String[] args) {
        File root = new File("测试目录\\非空目录");

        scanDir(0, root);
    }

    /**
     * 保证 node 代表的一定是目录
     * @param node
     */
    private static void scanDir(int level, File node) {
        // 1. 先打印节点的绝对路径
        // 假设我的缩进单位是 4 个空格
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(node.getAbsolutePath() + "\\");
        // 2. 获取节点的孩子节点
        File[] children = node.listFiles();
        if(children == null) {
            // 防御式编程: 理论上不应该出现该情况的
            return;
        }
        // 3. 孩子中的普通文件, 直接打印
        //    孩子中的目录, 递归, 进行深度遍历
        // 隐含的递归停止条件: children 的长度 == 0 的时候, 下面的循环是没有任何效果的
        for (File f:children) {
            // 如果孩子节点是目录, 就进行递归
            if(f.isDirectory()) {
                scanDir(level + 1, f);
            } else if(f.isFile()) {
                // 如果孩子节点是文件, 就直接打印
                for (int i = 0; i < level; i++) {
                    System.out.print("    ");
                }
                System.out.println(f.getAbsolutePath());
            }
        }

    }
}
