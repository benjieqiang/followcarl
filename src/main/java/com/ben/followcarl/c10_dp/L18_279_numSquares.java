package com.ben.followcarl.c10_dp;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-11  09:22
 * @Description:
 * 1. 确定dp数组（dp table）以及下标的含义
 * dp[j]：和为j的完全平方数的最少数量为dp[j]
 *
 * 2. 确定递推公式
 * dp[j] 可以由dp[j - i * i]推出， dp[j - i * i] + 1 便可以凑成dp[j]。
 *
 * 此时我们要选择最小的dp[j]，所以递推公式：dp[j] = min(dp[j - i * i] + 1, dp[j]);
 *
 * 3. dp数组如何初始化
 * dp[0]表示 和为0的完全平方数的最小数量，那么dp[0]一定是0。
 *
 * 有同学问题，那0 * 0 也算是一种啊，为啥dp[0] 就是 0呢？ 看题目描述，找到若干个完全平方数（比如 1, 4, 9, 16, ...），题目描述中可没说要从0开始，dp[0]=0完全是为了递推公式。
 *
 * 非0下标的dp[j]应该是多少呢？
 *
 * 从递归公式dp[j] = min(dp[j - i * i] + 1, dp[j]);
 * 中可以看出每次dp[j]都要选最小的，所以非0下标的dp[j]一定要初始为最大值，这样dp[j]在递推的时候才不会被初始值覆盖。
 *
 * 4. 确定遍历顺序
 * 我们知道这是完全背包，组合数：先物品后背包，排列数：先背包，后物品。本次使用先物品后背包；
 *
 * 5. 举例推导dp数组 已输入n为5例，dp状态图如下
 * dp[0] = 0
 * dp[1] = min(dp[0] + 1) = 1
 * dp[2] = min(dp[1] + 1) = 2
 * dp[3] = min(dp[2] + 1) = 3
 * dp[4] = min(dp[3] + 1, dp[0] + 1) = 1
 * dp[5] = min(dp[4] + 1, dp[1] + 1) = 2

 * @Version: 1.0
 */
public class L18_279_numSquares {
    public int numSquares(int n) {
        // 1，4，9，... , sum = n
        // dp[j] = Math.min(dp[j], dp[j - sqrnum] + 1);
        // i 0,n.
        // dp[0] = 1
        // i 1, n; j: i*i -> n;
        int[] dp = new int[n + 1];
        int max = Integer.MAX_VALUE;
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i * i; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        return dp[n];
    }
}
