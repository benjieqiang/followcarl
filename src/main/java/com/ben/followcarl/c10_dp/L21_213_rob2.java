package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-16  20:25
 * @Description:
 * 打家劫舍2：房屋连成一个圈，小偷不能在相邻的位置偷，求最大能偷多钱
 * 有三种情况：对于数组{1,6,1,9,1}
 * 1。不偷首和尾；{6,1,9}中找最大能偷的钱；
 * 2。只偷首：{1,6,1,9}中找最大能偷的钱；
 * 3。只偷尾：{6,1,9,1}中找最大能偷的钱
 * <p>
 * 2和3包括1，因为只偷首的情况，也可能不偷第一个元素，到底偷不偷交给递推公式来决定，
 * 偷的范围都是左闭右闭的区间, 从[0, nums.length-2] 和[1,nums.length- 1]取最大的;
 * 普通的偷逻辑：
 * 1. dp[i]表示前i个屋子里小偷能偷的最大钱；
 * 2. dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i])
 * 遍历范围从start, start+1, ..., end;
 * 3. 初始化: dp[start] = nums[start]
 *     dp[start + 1] = Math.max(nums[start], nums[start + 1]);
 * 4. 遍历顺序, i从start + 2开始遍历到end结束; 包括end
 * 5. 举例;
 * 返回dp[end];
 * @Version: 1.0
 */
public class L21_213_rob2 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        // 只偷首，不偷尾；
        int max1 = robRange(nums, 0, nums.length - 2);
        // 只偷尾，不偷首；
        int max2 = robRange(nums, 1, nums.length - 1);

        return Math.max(max1, max2);
    }

    /**
     * @param nums:
     * @param start:
     * @param end:
     * @return int
     * @description 最简单的打家劫舍的逻辑;
     * @author benjieqiang
     * @date 2023/8/21 12:15 PM
     */
    private int robRange(int[] nums, int start, int end) {
        if (start == end) return nums[start]; //不偷首之后，剩下的数组只有一个元素，如果下标相同直接返回该元素
        int[] dp = new int[nums.length]; // 因为每次都少偷一个，所以长度只要比end-start + 1 = nums.length - 1大就行
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);

        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            // System.out.println(Arrays.toString(dp));
        }
        return dp[end];
    }

    @Test
    public void testRob2() {
        int[] nums = {1,2,3};
//        int[] nums = {0, 0};
        System.out.println(rob(nums));
    }

}
