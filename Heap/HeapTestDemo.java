package Heap;

public class HeapTestDemo {
    public static void main(String[] args) {
        //  创建堆对象
        Heap<String> heap = new Heap<String>(10);
        // 向堆中插入字符串数据
        heap.insert("A");
        heap.insert("B");
        heap.insert("C");
        heap.insert("D");
        heap.insert("E");
        heap.insert("F");
        heap.insert("G");
        // 借助于循环从堆中删除数据
        String result = null;
        System.out.println("堆删除测试用例：");
        while((result = heap.deleteMax()) != null){
            System.out.print(result + " ");
        }
    }
}
