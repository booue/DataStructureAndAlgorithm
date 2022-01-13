package BinaryTree;


import java.util.LinkedList;
import java.util.Queue;

/*二叉查找树类*/
public class BinaryTree<Key extends Comparable<Key>, Value> {

    private Node root; // 根结点
    private int N; // 元素个数

    /*BinaryTree类的内部节点类Node*/
    private class Node { // 内部结点类
        public Key key;
        private Value value;
        public Node left;
        public Node right;

        // 带参构造方法
        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    // 获取树中元素的个数
    public int size() {
        return this.N;
    }

    // 向树中添加元素key-value
    public void put(Key key, Value value) {
        root = put(this.root, key, value); // 注意赋值，需要进行root结点的更新
    }

    // 向指定的树中添加元素key-value(可以简单的画图辅助理解)
    private Node put(Node x, Key key, Value value) {
        // 假设插入结点为空
        if (x == null) {
            this.N++;
            Node rootNode = new Node(key, value, null, null);
            x = rootNode; // 将此结点作为本树的root结点
        } else if (x.key.compareTo(key) == 0) { //  假设插入结点不为null
            x.value = value; // 二者key相同，进行value值的替换
        } else if (key.compareTo(x.key) > 0) { // 注意compareTo比较的结果为int类型
            x.right = put(x.right, key, value);
        } else if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        }
        return x;
    }

    // 根据键值获得树中的value值
    public Value get(Key key) {
        return get(this.root, key); // 直接在root中查找
    }

    private Value get(Node x, Key key) {
        Value temp = null;
        // 查找的树为空
        if (x == null) {
            temp = null;
        } else { // 查找的树不为空
            if (x.key.compareTo(key) == 0) {
                temp = x.value;
            } else if (x.key.compareTo(key) < 0) {
                temp = get(x.right, key);
            } else if (x.key.compareTo(key) > 0) {
                temp = get(x.left, key);
            }
        }
        return temp;
    }


    // 根据键删除树中的key-value键值对
    public void delete(Key key) {
        delete(this.root, key);
    }

    // 删除指定树中key为特定值的键值对
    private Node delete(Node x, Key key) {
        // x树为null
        if (x == null) {
            return null;
        }
        // x树不为null
        else {
            int cmp = key.compareTo(x.key);
            if (cmp > 0) {
                x.right = delete(x.right, key); // 寻找当前结点的右子树
            } else if (cmp < 0) {
                x.left = delete(x.left, key); // 寻找当前结点的左子树
            } else {
                // 删除结点后，个数减一
                this.N--;

                // 找到右子树中最小的结点或者左子树中最大的结点
                if (x.right == null) {
                    return x.left;
                }
                if (x.left == null) {
                    return x.right;
                }
                Node mainNode = x.right; // 存储右子树中的最小结点
                while (mainNode.left != null) {
                    mainNode = mainNode.left;
                }

                // 删除右子树中最小的节点
                Node n = x.right;
                while (n.left != null) {
                    if (n.left.left == null) {
                        n.left = null;
                    }
                    n = n.left;
                }

                // 使用右子树中的最小值替换结点
                /*此处使用：右子树中最小结点mainNode的key和value替换删除结点X的key和value值*/
                mainNode.left = x.left;
                mainNode.right = x.right;
                x = mainNode; // 完成递归调用时即可以完成右子树最小结点的替换 root.right = mainNode;

            }
        }
        return x;
    }

    // 查找二叉查找树中最小的键
    public Key min() {
        return min(root).key;
    }

    // 在指定树x中找出最小的键(借助于递归)
    private Node min(Node x) {
        if (x == null) {
            return null;
        } else {
            if (x.left != null) {
                return min(x.left);
            } else {
                return x;
            }
        }
    }

    // 查找二叉查找树中最大的键
    public Key max() {
        return max(root).key;
    }

    // 在指定树中找出最大的键(借助于while循环)
    private Node max(Node x) {
        if (x == null) {
            return null;
        } else {
            while (x.right != null) {
                x = x.right;
            }
            return x;
        }
    }

    /*与视频的差异，此处应用的Queue是Java语言自带的，Queue为abstract，不能直接实例化对象，采用多态方式实现linkedlist应用*/
    // 二叉查找树的前序遍历
    public Queue<Key> preErgodic() {
        Queue<Key> keys = new LinkedList<>();
        preErgodic(root, keys);
        return keys;
    }

    private void preErgodic(Node x, Queue<Key> keys) {
        if (x == null) {
            return;
        } else {

            // 将x结点的key放入队列中
            keys.add(x.key);
            // 递归遍历x节点的左子树
            if (x.left != null) {
                preErgodic(x.left, keys);
            }
            // 递归遍历x结点的右子树
            if (x.right != null) {
                preErgodic(x.right, keys);
            }
        }
    }

    // 二叉查找树的中序遍历
    public Queue<Key> midErgodic() {
        Queue<Key> keys = new LinkedList<>();
        midErgodic(root, keys);
        return keys;
    }


    private Node midErgodic(Node x, Queue<Key> keys) {
        if (x == null) {
            return null;
        } else {
            // 左子树
            if (x.left != null) {
                midErgodic(x.left, keys);
            }
            // 根节点
            keys.add(x.key);
            // 右子树
            if (x.right != null) {
                midErgodic(x.right, keys);
            }
        }
        return x;
    }

    // 二叉查找树的后续遍历
    public Queue<Key> afterErogodic() {
        Queue<Key> keys = new LinkedList<>();
        afterErogodic(root, keys);
        return keys;
    }

    private Node afterErogodic(Node x, Queue<Key> keys) {
        if (x == null) {
            return null;
        } else {
            // 左子树
            if (x.left != null) {
                afterErogodic(x.left, keys);
            }
            // 右子树
            if (x.right != null) {
                afterErogodic(x.right, keys);
            }
            // 根节点
            keys.add(x.key);
        }
        return x;
    }

    // 二叉查找树的层序遍历
    public Queue<Key> layerErogodic(){
        // 定义双队列：分别存储树中的键和树中的结点
        LinkedList<Key> keys = new LinkedList<>();
        LinkedList<Node> nodes = new LinkedList<>();

        // 默认向队列中放入根节点
        nodes.add(root);

        while(!nodes.isEmpty()){
            // 从队列中弹出结点，将key放入keys中
            Node node = nodes.pop();
            keys.add(node.key);
            // 判断当前结点是否存在左子节点，存在则放入nodes中
            if(node.left != null){
                nodes.add(node.left);
            }
            // 判断当前结点是否存在右子节点，存在则放入nodes中
            if(node.right != null){
                nodes.add(node.right);
            }
        }
        return keys;
    }

    // 获取二叉查找树的最大深度
    public int maxDepth(){
        return maxDepth(root);
    }

    // 获取指定二叉查找树的最大深度
    private int maxDepth(Node x){
        if(x == null){
            return 0;
        }else{
            int Rmax = 0;
            int Lmax = 0;
            int max = 0;
            // 计算左子树的最大深度
            if (x.left != null) {
                Lmax = maxDepth(x.left);
            }
            // 计算右子树的最大深度
            if(x.right != null){
               Rmax = maxDepth(x.right);
            }
            // 取二者最大值加一即可
            max = (Rmax > Lmax ? Rmax:Lmax) + 1; // 此处括号的有无可能会影响运算符的优先级
            return max;
        }
    }
}