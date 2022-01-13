package MinPriorityQueue;

/*最小优先队列与最大优先队列的实现思想类似：
* 最大优先队列->大顶堆(最大元素放置在第一个位置，父节点大于任意一个子节点)
* 最小优先队列->小顶堆(最小元素放置在第一个位置，父节点小于任意一个子节点)
* */

public class MinPriorityQueue<T extends Comparable<T>> {
    //成员变量
    private T[] items;
    private int N;

    // 构造方法
    public MinPriorityQueue(int capacity){
        this.items = (T[])new Comparable[capacity + 1]; // 废除索引1
        this.N = 0;
    }

    // 成员方法

    // 判断堆中索引i处的元素是否小于索引j处的元素
    private Boolean less(int i,int j){
        return (items[i].compareTo(items[j])) < 0;
    }

    // 交换堆中索引i处的元素和索引j处的元素
    private void exch(int i,int j){
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    // 获取堆中元素个数
    public int size(){
        return this.N;
    }

    // 判断堆是否为空
    public Boolean isEmpty(){
        return this.N == 0;
    }

    // 删除堆中最小的元素(索引为1的元素)并且返回这个元素
    public T deleteMin(){
        // 定义临时变量记录堆中最小的结点
        T temp = items[1];
        // 交换最小结点和最后一个结点
        exch(1,N);
        // 删除最后一个结点
        items[N] = null;
        this.N--;
        // 对首元素执行下沉算法，调整堆的结构
        sink(1);
        // 返回删除后的元素
        return temp;
    }

    // 往堆中插入一个元素
    public void insert(T t){
        // 向队列末尾插入一个元素
        items[++N] = t;
        // 对新插入的元素执行上浮算法调整堆的结构
        swim(N);
    }

    // 使用上浮算法，使得索引k处的元素能够在堆中处于正确的位置
    private void swim(int k){
        while(k > 1){ // 注意判断条件(判断上浮更新结点位置是否到堆首的位置，同时注意此处不能相等，否则出现下标为0的情况，程序异常)
            if(less(k,k/2)){
                // 交换元素位置，执行上浮操作
                exch(k,k/2);
            }else{
                // 满足最小堆条件，不必继续上浮
                break;
            }
            // 节点更新
            k = k/2;
        }
    }

    // 使用下沉算法，使得索引k处的元素能够在堆中处于正确的位置
    private void sink(int k){
        while(2*k <= N){
            // 获取当前结点较小子节点的索引
            int index;
            if(2*k+1 <= N){
                // 此时当前结点存在右子节点
                index = less(2*k+1,2*k)?(2*k+1):(2*k);
            }else{
                // 不存在右子节点
                index = 2*k;
            }
            // 判断当前结点和较大子节点的大小关系
            if(less(index,k)){
                exch(index,k);
            }else{
                break;
            }
            // 结点更新
            k = index;
        }
    }



















}
