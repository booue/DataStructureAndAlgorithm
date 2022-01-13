package Heap;

/*堆
* 1. 完全二叉树
* 2. 通常利用"数组"实现(为更加方便的操作多因索引，废弃掉索引0，堆中最大的元素存储在索引为1的位置上)
* {a[k]结点向上移动一层为a[k/2],向下移动一层为a[2k]或者a[2k+1]},借助于数组索引实现在完全二叉树中层间的移动
* 3. 父节点大于子节点，但是两个子节点的位置不做规定，左节点和右节点不区分大小(堆树与二叉查找树的定义存在区别)
* */



public class Heap<T extends Comparable<T>> {
    private T[] items; // 底层数组
    private int N; // 堆中数据个数

    // 带参构造方法
    public Heap(int capacity) {
        /*此处泛型T实现Comparable接口，因此new时必须为Comparable类型的数组*/
        /*同时，因为堆数据结构废除索引0，因此常规数组创建缺少一个元素，为保证堆的容量，数组创建时需要加一*/
        this.items = (T[]) new Comparable[capacity + 1]; // 强制类型转化
        this.N = 0;
    }

    // 判断索引i处的元素是否小于元素j处的元素
    private Boolean less(int i,int j){
        return items[i].compareTo(items[j]) < 0;
    }

    // 交换堆中i索引处和j索引处的值
    private void exch(int i,int j){
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    // 删除堆中最大的元素，并且返回这个元素
    public T deleteMax(){
        T max = items[1];

        // 交换1索引处的值和最大索引处的值
        exch(1,N);
        // 临时根结点删除(最大索引处的值)
        items[N] = null;
        this.N--;
        // 使用下沉算法使堆的数据排列有序
        sink(1);
        // 返回删除掉的最大元素
        return max;
    }

    // 向堆中插入一个元素
    public void insert(T t){
        items[++N] = t; // N先做加法，而后进行赋值操作(目的在于废弃掉数组中的索引0，从1开始继续计数)
        /*每次向堆中插入新的数据，默认插入到数组最后一个，而后借助于上浮算法实现堆的结构调整*/
        swim(N);
    }

    // 使用上浮算法，使得索引k处的元素能够在堆中处于一个正确的位置
    private void swim(int k){
        /*不断将新插入的元素和父节点进行比较，不符合堆的规则(父节点大于子节点)，即交换两个节点的位置，实现堆结构的调整*/
        while(k > 1){ // 交换至最大结点处停止
            if(less(k/2,k)){
                exch(k/2,k);
            }else{
                break;
            }
            k = k/2;
        }
    }

    // 使用下沉算法，使得索引k处的元素能够在堆中处于以一个正确的位置
    private void sink(int k){
        while(2*k <= N){ // 含有子节点才进行后续比较
            // 获取子节点中较大值的索引
            int index;
            if(2*k+1 <= N){
                index = (less(2*k,2*k+1))?(2*k+1):(2*k);
            }else{
                index = 2*k;
            }

            // 下沉算法
            if(less(k,index)){
                exch(k,index);
            }else{
                break;
            }

            // k值更新
            k = index;

        }
    }

}
