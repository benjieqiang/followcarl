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
 * @Version: 1.0
 */
public class L2_283_moveZeros {
    public void moveZeroes(int[] nums) {
        System.out.println(Arrays.toString(nums));
        int slow = 0; //slow指向新数组的下标
        // fast用来寻找新数组的元素，遇到0，就要进行交互slow和fast位置的元素
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                int tmp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = tmp;
                slow++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void testMove0() {
        int[] nums = {0,1,0,3,12};
        moveZeroes(nums);
    }
}
