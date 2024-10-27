package com.ben.followcarl.c1_array;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-26  08:10
 * @Description:
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 * @Version: 1.0
 */
public class L1_35_searchInsert {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // int mid = left + ((right - left) >> 1);
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        // 对于{-1,0,3,5,9,12}来说， 最后一步根据数组最大值与目标值的比较，left与right位置是左右颠倒的关系，
        // left=right=mid=5.nums[mid]=nums[right]=12
        // 1. if nums[mid] = 12 > target = 10，right跑到left左侧，right = right - 1
        //  mid位置就是要插入的位置，因为他第一个比target大，又因为此时mid = left。返回left ok；
        // 2. if nums[mid] = 12 < target=18, left跑到right右侧，left = left + 1,
        // 看表达式mid位置元素是最后一个比target小的元素，所以插入到mid+1位置。又因为上一步中left已经move到了left+1位置，所以返回left ok。

        return left;
    }

    @Test
    public void testSearch() {
        int[] nums = {-1,0,3,5,9,12};
        int target = 10;
        System.out.println(search(nums, target));
    }
}
