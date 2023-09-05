package com.ben.followcarl.sortalgrithum;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-04  11:47
 * @Description: 选择排序
 * 思路:
 * 双层for循环,第一层for循环表示第几趟遍历
 * 第二层for循环用来找最小索引,找到之后,记录最小元素的索引,把最小元素与当前位置的元素交换;
 * @Version: 1.0
 */
public class L2_SelectionSort {

    public int[] selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) minIndex = j;
            }

            if (minIndex == i) continue; //起始位置就是最小元素,不需要动; 否则交换当前元素和最小元素的位置;
            int tmp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = tmp;
        }
        return nums;
    }

    @Test
    public void testSelectionSort() {
        int[] arr = {64, 25, 12, 22, 11};
        selectionSort(arr);

        System.out.println("排序后的数组:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
