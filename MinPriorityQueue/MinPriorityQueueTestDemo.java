package MinPriorityQueue;

public class MinPriorityQueueTestDemo {
    public static void main(String[] args) {
        // 创建最小优先队列对象
        MinPriorityQueue<String> queue = new MinPriorityQueue<String>(10);

        // 最小优先队列插入元素
        queue.insert("a");
        queue.insert("l");
        queue.insert("h");
        queue.insert("f");
        queue.insert("r");
        queue.insert("o");
        queue.insert("e");
        queue.insert("b");

        // 输出最下元素排列(测试用例)
        String result;
        while((result = queue.deleteMin()) != null){
            System.out.print(result + " ");
        }

    }
}
