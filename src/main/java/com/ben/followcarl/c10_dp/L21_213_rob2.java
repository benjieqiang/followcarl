package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-16  20:25
 * @Description: 打家劫舍2：房屋连成一个圈，小偷不能在相邻的位置偷，求最大能偷多钱
 * 有三种情况：对于数组{1,6,1,9,1}
 * 1。不偷首和尾；{6,1,9}中找最大能偷的钱；
 * 2。只偷首：{1,6,1,9}中找最大能偷的钱；
 * 3。只偷尾：{6,1,9,1}中找最大能偷的钱
 * <p>
 * 2和3包括1，因为只偷首的情况，也可能不偷第一个元素，到底偷不偷交给递推公式来决定，
 * dp[i]表示第i个屋子小偷能偷的最大钱；
 * @Version: 1.0
 */
public class L21_213_rob2 {
    public int rob(int[] nums) {
        if (nums == null) return 0;
        if (nums.length <= 1) return nums[0];
        // 只偷首，不偷最后一个尾；
        int max1 = robRange(nums, 0, nums.length - 2);
        // 只偷尾，不偷首；
        int max2 = robRange(nums, 1, nums.length - 1);

        return Math.max(max1, max2);
    }

    private int robRange(int[] nums, int start, int end) {
        if (start == end) return nums[start]; //不偷首之后，剩下的数组只有一个元素，如果下标相同直接返回该元素
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);

        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            System.out.println(Arrays.toString(dp));
        }
        return dp[end];
    }

    @Test
    public void testRob2() {
//        int[] nums = {1,2,3};
        int[] nums = {0, 0};
        System.out.println(rob(nums));
    }

}
