package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-10  20:41
 * @Description:
 * 第一层有一种方法：走一步
 * 第二层有两种方法：一步一步走，一次走两步；
 * 第三层有三种方法：一步一步走，先走两步再走一步，先走一步再走两步；
 * 1. dp[i]：到达第i个楼梯有dp[i]的方法
 * 2. dp[i] = dp[i - 1] + dp[i - 2]
 * 3. 初始化dp[0]和dp[1],dp[2], dp[0]不应该初始化为0或1，因为i是从1开始，表示此时在第一层台阶位置；
 * 4. 遍历顺序，如果从后往前不能确定dp[i -1]和dp[i-2],所以顺序遍历；
 * 5. 打印
 * @Version: 1.0
 */
public class L2_70_climbStairs {
    public int climbStairs(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * @param n:
     * @return int
     * @description 优化空间复杂度为O(1)
     *
     * @author benjieqiang
     * @date 2023/8/18 10:30 PM
     */
    public int climbStairs2(int n) {
        if (n <= 1) return n;
        int[] dp = new int[3];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            int sum = dp[1] + dp[2];
            dp[1] = dp[2];
            dp[2] = sum;
        }
        return dp[2];
    }

    public int climbStairs3(int n) {
        if (n <= 1) return n;
        int dp1 = 1;
        int dp2 = 2;
        for (int i = 3; i <= n; i++) {
            int sum = dp1 + dp2;
            dp1 = dp2;
            dp2 = sum;
        }
        return dp2;
    }
    @Test
    public void testClimbStairs() {
        int n = 45;
        System.out.println(climbStairs2(n));
    }
}
