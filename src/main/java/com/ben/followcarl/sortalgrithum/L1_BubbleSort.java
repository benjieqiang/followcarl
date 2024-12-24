package com.ben.followcarl.sortalgrithum;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-04  11:04
 * @Description: 冒泡排序
 * 双层for循环
 * 第一个for循环表示跑几趟, 从1跑nums.length - 1躺;
 * 第二个for循环用来比较相邻元素,如果当前比后面大,则交换; 从0开始到nums.length-i(不包括nums.length-i)结束, 因为每一趟都确定了一个最大的元素在末尾
 * 第1躺则需要遍历到0到nums.length-2的位置, 最后比较的是当前元素: nums[nums.length - 2], 下一个元素是nums[nums.length-1]
 * 第2躺只需遍历到0到nums.length-3的位置, 即比较nums[nums.length - 3]和nums[nums.length - 2]
 * flag标识为为false说明这一躺至少交换了一次, 为true说明都原数组是递增的, 不用排序;
 * @Version: 1.0
 */
public class L1_BubbleSort {
    public int[] bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean flag = true;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                    flag = false;
                }
            }
            if (flag) break;
        }
        return nums;
    }

    public int[] bubbleSort2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean flag = true;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                    flag = false;
                }
            }
            if (flag) break;
        }
        return nums;
    }
    @Test
    public void testBubbleSort(){
        int[] nums = {64, 34, 25, 12, 22, 11, 90};
        int[] res = bubbleSort(nums);
        System.out.println(Arrays.toString(res));
        int[] nums2 = {64, 34, 25, 12, 22, 11, 90};

        System.out.println(Arrays.toString(bubbleSort2(nums2)));
    }
}
