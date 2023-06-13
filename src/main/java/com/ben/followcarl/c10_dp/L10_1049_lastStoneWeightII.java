package com.ben.followcarl.c10_dp;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-13  08:49
 * @Description: 尽量让石头分成重量相同的两堆，相撞之后剩下的石头最小，这样就化解成01背包问题
 * 石头只能用一次；
 * 和分割相等和的子序列问题相似，只不过最后返回的是两堆石头的差值
 * target = sum / 2；
 * 1. dp[j] 表示背包容量为j能装的最大重量（价值）的石头 ,在定义dp数组时，dp[target + 1]的大小；
 * 2. dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i])
 * 3. dp[0] = 0
 * 4. for i in (0, stones.length)
 *       j= target; j >= stones[i]; j--
 * 5. return sum - dp[target] - dp[target];
 * @Version: 1.0
 */
public class L10_1049_lastStoneWeightII {
    public int lastStoneWeightII(int[] stones) {
        if (stones.length == 0) return 0;
        int sum = 0;
        for (int num : stones) sum += num;
        int target = sum / 2;

        int[] dp = new int[target + 1];
        dp[0] = 0;
        for (int i = 0; i < stones.length; i++) {
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - dp[target] - dp[target];
    }
}
