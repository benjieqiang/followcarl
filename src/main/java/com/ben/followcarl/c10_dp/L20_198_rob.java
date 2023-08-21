package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-16  20:18
 * @Description: 有个小偷，偷一排屋子，不能相邻偷，求最大能偷多钱？
 * 1. dp[i] 包括i以内的房屋，小偷最大能偷dp[i]的钱；
 * 2. dp[i] = max(dp[i -1], dp[i - 2] + nums[i])
 * 偷i：i的前一户i-1就不偷，所以是dp[i-2]+nums[i].表示到i-2的屋子小偷偷的最大钱数加上在i号房子偷的钱nums[i]
 * 不偷i：dp[i-1] 表示到i-1的屋子小偷偷的钱；
 * 3. dp[0] = nums[0], dp[1]从0开始到1位置取最大的钱；
 * 4. 从i=2位置到nums.length-1位置结束。
 * 5. 返回的是最后一个位置的dp值就是最大能偷的钱;
 *
 * @Version: 1.0
 */
public class L20_198_rob {
    public int rob(int[] nums) {
        if (nums == null) return 0;
        if (nums.length <= 1) return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            System.out.println(Arrays.toString(dp));
        }
        return dp[nums.length - 1];
    }
    /**
     * @param nums:
     * @return int
     * @description dp[i]表示
     * @author benjieqiang
     * @date 2023/8/21 12:12 PM
     */
    public int rob2(int[] nums) {
        if (nums == null) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        //dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }

    @Test
    public void testRob() {
        int[] nums = {1,2,3,1};
        int res = rob(nums);
        System.out.println(res);
    }
}
