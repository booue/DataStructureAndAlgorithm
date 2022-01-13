package BinaryTree;

/*借助于二叉查找树数据结果模拟折纸的折痕问题
* 二叉树具有以下特点：
* 1. 根节点为下折痕
* 2. 左子节点为下折痕
* 3. 右子节点为上折痕
* 折纸问题最终遍历输出时，借助于“中序遍历”完成
* */

import java.util.LinkedList;

public class PagerFoldTestCode {
    public static void main(String[] args) {
        // 模拟折纸过程，产生二叉树
        Node<String> treeNode = createTree(8);
        // 遍历树，打印每个结点
        showTree(treeNode);
    }

    public static Node<String> createTree(int N){
        /*N代表折纸次数，以产生对应大小的二叉树*/
        Node<String> root = null;
        for (int i = 0; i < N; i++) {
            if(i == 0){
                /*第一次对折*/
                root = new Node<String>("down",null,null);
            }else{
                /*当前并非第一次对对折*/
                /*为当前树中所有的"叶子结点"添加左子节点和右子节点,借助于层序遍历思想找到所有的叶子节点*/
                LinkedList<Node> queue = new LinkedList<>();
                queue.add(root);

                // 循环遍历队列
                while(!queue.isEmpty()){
                    // 从队列中弹出一个结点
                    Node popNode = queue.pop();
                    // 当前节点存在左节点，说明并非叶子节点，继续向下找
                    if(popNode.left != null){
                        queue.add(popNode.left);
                    }
                    // 当前结点存在右节点，说明并非叶子结点，继续向下找
                    if(popNode.right != null){
                        queue.add(popNode.right);
                    }
                    // 当前节点为叶子节点，为当前节点添加左子节点(down)和右子节点(up)
                    if(popNode.left == null && popNode.right == null){
                        popNode.left = new Node<String>("down",null,null);
                        popNode.right = new Node<String>("up",null,null);
                    }
                }
            }
        }

        return root;
    }

    // 打印树中的每个结点到控制台(借助于中序遍历完成)
    public static void showTree(Node<String> root){
        if(root == null){
            return;
        }else {
            // 打印左子树结点
            if(root.left != null){
                showTree(root.left);
            }
            // 打印当前结点
            System.out.print(root.item + " ");
            // 打印右子树结点
            if(root.right != null){
                showTree(root.right);
            }
        }
    }

    /*内部结点类*/
    private static class Node<T>{
        T item; // 存储元素(up or down)
        Node<T> left;
        Node<T> right;

        // 带参构造方法
        public Node(T item, Node<T> left, Node<T> right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }
}


/*收获小Tips:
* 使用if-else语句时，注意if-else和if的区别
* int num = 10;
* if(num > 0){
*   System.out.println(num)
* }
* System.out.println("Test Code")
*
* int num = 0;
* if(num > 0){
*   System.out.println(num)
* }else{
*   System.out.println("Test Code")
* }
*
* 注意程序控制语句执行的顺序
* */