package Heap;

/*堆排序*/
public class HeapSort<T extends Comparable<T>> {

    // 借助于堆排序完成数组从小到大的排序
    public static void sort(Comparable[] source){

        // 构建堆
        Comparable[] heap = new Comparable[source.length + 1]; // 废止索引1
        creareHeap(source,heap);
        // 索引1处的元素和未排序中最大索引处的元素进行交换
        int index = heap.length-1; // 索引最大处的元素
        while(index!=1){
            exch(heap,1,index);
            index--; // 排除已经排序元素，方便确定元素下沉的范围

            // 对索引1处的元素进行下沉操作(下沉范围需要排除已经排序的元素)
            sink(heap,1,index);
        }

        // 将heap中数据复制到原数组source中
        System.arraycopy(heap,1,source,0,source.length);
    }

    // 根据原数组source构建出堆对象heap
    private static void creareHeap(Comparable[] source,Comparable[] heap){
        /*根据原数组构造堆：
        * 可以遍历原数组，将原数组中的每一个元素插入到heap堆中，同时采用上浮的方式使得堆的结构变的规整，此时完成根据原数组进行堆构建的任务
        * */
        /*巧妙的方式：
        * 1. 将原数组原封不动的拷贝出来形成格式不规范的堆(注意下标的调整，废弃索引0)
        * 2. 在数组索引的一一半处向前扫描，对每一个元素进行下沉操作获得格式化后正确的堆
        * 3. 理由：一一半之前均为分支节点，存在子节点，因此存在下沉必要，但是一半向后的结点为叶子结点，不存在子节点，因此没有下沉的必要，提高性能
        * */

        // 将source中元素拷贝至heap中，heap形成结构不规范的堆
        System.arraycopy(source,0,heap,1,source.length); // 将source中的元素从索引为0处开始的所有元素拷贝至heap数组中(heap数组从下标为1开始)
        // 对堆中的元素进行下沉调整，调整范围从长度一半处向索引1处扫描
        for(int i = (heap.length)/2;i>0;i--){
            sink(heap,i,heap.length-1); // 根据索引确定元素，数组中并不存在source[length]元素，会报错out of range
        }

    }

    // 判断堆中索引i出的元素是否小于索引j出的元素
    private static Boolean less(Comparable[] heap,int i,int j){
        return (heap[i].compareTo(heap[j]) < 0);
    }

    // 交换堆heap中索引i处和j处的元素
    private static void exch(Comparable[] heap,int i,int j){
        Comparable temp = heap[i]; // 此处创建元素T实现Comparable接口，因此为Comparable类型，而非Object类型
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // 在heap堆中，对target处的元素进行下沉，范围为0-range
    private static void sink(Comparable[] heap,int target,int range){
        while(2*target <= range){
            // 找出当前结点较大子节点
            int index;
            if(target*2+1 <= range) {
                index = less(heap, target * 2, target * 2 + 1) ? (target * 2 + 1) : (target * 2);
            }else{
                index = target*2;
            }
            // 比较当前结点和较大子节点
            if (less(heap,target,index)){
                exch(heap,index,target);
            }else{
                break;
            }
            // 当前结点更新
            target = index;
        }

    }

}
