package com.ben.followcarl.c1_array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-05  19:22
 * @Description: 删除升序数组中的重复项
 * slow, fast一前一后，
 * 如果发现两指针指向的元素相等，则fast后移一位；
 * 如果fast指向的元素比slow指向的元素大，则slow需要先前进一位，则需要把fast的元素覆盖slow指向的当前位置的元素。
 * @Version: 1.0
 */
public class L2_26_removeDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] > nums[slow]) {
                nums[++slow] = nums[fast];
            }
        }
//        System.out.println(Arrays.toString(nums));
        return slow + 1;
    }

    @Test
    public void testRemoveDuplicates() {
        int[] nums = {1,2,3};
        System.out.println(removeDuplicates(nums));
    }
}
