package com.ben.followcarl.c10_dp;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-13  11:40
 * @Description:
 * 分成左右两个集合left和right， left的和 = (总和 + target)/2
 * 由题意：left - right = target // left左集合，right 右集合
 *       left + target = sum
 * 所以：  left = （sum + target)/2;
 * 比如[1,1,1,1,1]可以分成左集合left = [1,1,1,1], 右集合right = [1];
 *
 * 给了一个容量为left的背包，问有多少种方式能把背包装满；
 *
 * @Version: 1.0
 */
public class L11_494_findTargetSumWays {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) sum += num;
        //如果target过大 sum将无法满足
        if (target < 0 && sum < -target) return 0;
        if ((target + sum) % 2 != 0) return 0; //如果发现不能被2整除，则说明装不了；
        int size = (target + sum) / 2; // 正数集合的大小
        if (size < 0) size = -size; //确保是正数集合；
        //dp[j] 表示：填满j这么大容积的包，有dp[j]种方法
        int[] dp = new int[size + 1];
        dp[0] = 1; // ?
        for (int i = 0; i < nums.length; i++) {
            for (int j = size; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[size];
    }
}
