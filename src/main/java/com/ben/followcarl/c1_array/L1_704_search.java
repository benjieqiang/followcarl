package com.ben.followcarl.c1_array;

import org.junit.Test;

/**
 * @param null:
 * @return null
 * @description 有序，找下标；
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * @author benjieqiang
 * @date 2023/7/2 8:19 PM
 */
public class L1_704_search {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    @Test
    public void testSearch() {

    }

}
