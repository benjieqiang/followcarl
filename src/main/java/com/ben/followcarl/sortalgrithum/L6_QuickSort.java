package com.ben.followcarl.sortalgrithum;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-23  14:11
 * @Description: TODO
 * @Version: 1.0
 */
public class L6_QuickSort {
    // 快速排序方法
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 找到分区点
            int pivotIndex = partition(arr, low, high);

            // 递归排序左右子数组
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    // 分区方法
    private static int partition(int[] arr, int low, int high) {
        // 选取最后一个元素作为枢纽
        int pivot = arr[high];

        int i = low - 1;  // i 用来跟踪小于 pivot 的元素的位置

        // 遍历数组并将小于 pivot 的元素放到左边，大于 pivot 的元素放到右边
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // 交换 arr[i] 和 arr[j]
                swap(arr, i, j);
            }
        }

        // 将枢纽元素放到正确的位置，即所有小于 pivot 的元素在它左边，大于它的元素在右边
        swap(arr, i + 1, high);

        return i + 1;  // 返回枢纽元素的索引
    }

    // 交换数组中的两个元素
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 测试代码
    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        System.out.println("原始数组:");
        printArray(arr);

        quickSort(arr, 0, arr.length - 1);

        System.out.println("\n排序后的数组:");
        printArray(arr);
    }

    // 打印数组的方法
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


}
