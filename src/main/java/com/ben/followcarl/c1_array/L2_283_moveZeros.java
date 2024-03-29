package com.ben.followcarl.c1_array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-05  19:33
 * @Description:
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 都从0开始遍历，如果fast指向非0，则交换此时slow和fast所指向的元素值；
 * 1. slow = 0, fast = 0,此时nums[fast] = 0，所以不交换,fast++,[0,1,0,3,12]
 * 2. slow = 0, fast = 1, nums[1] = 1 != 0, 开始交换slow和fast位置元素，slow+1, fast+1， 数组[1,0,0,3,12]
 * 3. slow = 1, fast = 2, nums[2] = 0，保持 fast++, [1,0,0,3,12]
 * 4. slow = 1, fast = 3, nums[3] = 3 != 0， 交换slow和fast元素，slow + 1，fast + 1 数组是[1,3,0,0,12]
 * 5. slow = 2, fast = 4, nums[4] = 12 != 0, 交换，slow + 1, fast + 1 数组是[1,3,12,0,0]。完毕
 *
 *  数组去重是删除里面的重复项，fast遍历寻找不同值，不同则复制到slow的下一个位置元素；
 *  移动0是，把0位置的元素与下一个不为0的元素的位置交换。
 *  数组删除目标元素是遍历寻找新数组的下标，找到之后。覆盖到上一个slow位置；
 * @Version: 1.0
 */
public class L2_283_moveZeros {
    public void moveZeroes(int[] nums) {
        int slow = 0;
        //slow指向新数组的下标
        // fast 从0开始遍历数组nums，用来寻找不为0的元素，遇到0，就要进行交互slow和fast位置的元素
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                int tmp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = tmp;
                slow++;
            }
            System.out.println(Arrays.toString(nums));
        }
    }

    /**
     * @param nums:
     * @return void
     * @description 暴力解法: 遍历数组把非零元素移动到数组前面, 用index记录非0的终止位置;
     * 最后从index出发到n结束,给赋值成0;
     * @author benjieqiang
     * @date 2023/9/14 6:25 PM
     */
    public void moveZeroes2(int[] nums) {
        int n = nums.length;

        // 遍历数组，将非零元素移到数组的前面
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }

        // 将剩余位置补0
        while (index < n) {
            nums[index] = 0;
            index++;
        }
    }

    @Test
    public void testMove0() {
        int[] nums = {0,1,0,3,12};
        moveZeroes(nums);
        int[] nums2 = {0,1,0,3,12};
        moveZeroes2(nums2);
    }
}
