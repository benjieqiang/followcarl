package com.ben.followcarl.c1_array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-05  19:22
 * @Description: 删除升序数组中的重复项。为啥slow+1,假设数组只有一个元素，数组的长度应该是slow+1=1,而不是0；
 * slow, fast一前一后，
 * fast：去寻找数组中下一个不同的元素。
 * - 如果是等于，fast继续加；
 * - 如果fast指向的元素比slow指向的元素大，找到了这个不同元素，则需要把fast的元素放到上一个不同元素的右边，也就是slow加1的位置；
 * @Version: 1.0
 */
public class L2_26_removeDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] > nums[slow]) {
                nums[++slow] = nums[fast];
            }
        }
        System.out.println(Arrays.toString(nums));
        return slow + 1; // slow始终指向的最后一个元素的下标位置,所以：slow + 1才是数组长度；
    }

    @Test
    public void testRemoveDuplicates() {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(Arrays.toString(nums));
        System.out.println(removeDuplicates(nums));
    }
}
