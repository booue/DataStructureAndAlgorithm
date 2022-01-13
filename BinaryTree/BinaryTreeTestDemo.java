package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeTestDemo {
    public static void main(String[] args) {

        // 创建二叉查找树对象
        BinaryTree<Integer, String> Tree = new BinaryTree<Integer, String>();

        Tree.put(5,"张三");
        Tree.put(2,"李四");
        Tree.put(7,"王五");
        Tree.put(1,"如花");
        Tree.put(4,"赵六");
        Tree.put(6,"田七");
        Tree.put(8,"邓八");
        Tree.put(3,"黄三");

        System.out.println("BinaryTree的大小为：" + Tree.size());

        String result = Tree.get(2);
        System.out.println("key=2对应元素为：" + result);

//        /*此处delete存在问题，抛出null的异常，需要后续调整*/
//        Tree.delete(2);
//        System.out.println("删除后的元素个数：" + Tree.size());
//        System.out.println("key=2处对应元素为：" + Tree.get(2));

        System.out.println("三种遍历方法验证-前序，中序，后序，层序：");
        // 前序遍历实现
        Queue<Integer> Prequeue = new LinkedList<>();
        Prequeue = Tree.preErgodic();
        for (Integer element : Prequeue) {
            System.out.print(element + " ");
        }

        System.out.println();
        System.out.println("---------------");

        // 中序遍历实现
        Queue<Integer> Midqueue = new LinkedList<>();
        Midqueue = Tree.midErgodic();
        for (Integer element : Midqueue) {
            System.out.print(element + " ");
        }

        System.out.println();
        System.out.println("---------------");

        // 后续遍历实现
        Queue<Integer> Afterqueue = new LinkedList<>();
        Afterqueue = Tree.afterErogodic();
        for (Integer element : Afterqueue) {
            System.out.print(element + " ");
        }

        System.out.println();
        System.out.println("---------------");

        // 层序遍历实现
        Queue<Integer> layerqueue = new LinkedList<>();
        layerqueue = Tree.layerErogodic();
        for (Integer element : layerqueue) {
            System.out.print(element + " ");
        }

        /*层序遍历采用广度有限搜索，三种基础遍历方式均采用深度有限搜索方式*/

        System.out.println();
        // 测试二叉查找树最大深度代码
        int depth = Tree.maxDepth();
        System.out.println("二叉查找树的最大深度为" + depth);


    }
}
