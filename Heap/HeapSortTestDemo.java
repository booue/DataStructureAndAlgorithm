package Heap;

import java.util.Arrays;

public class HeapSortTestDemo {
    public static void main(String[] args) {

        // 待排序数组
        String[] arr = {"S","O","R","T","E","X","A","M","P","L","E"}; // 注意小写字母和大写字母的ASCII码值
        // 通过heapsort对原数组进行排序
        HeapSort.sort(arr);
        // 字符串输出
        System.out.println(Arrays.toString(arr));
    }
}
