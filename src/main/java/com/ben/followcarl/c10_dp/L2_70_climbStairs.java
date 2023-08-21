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



    /**
     * @param n:
     * @return int
     * @description 利用背包的解法, 比如
     * 一步一个台阶，两个台阶，三个台阶，.......，直到 m个台阶。问有多少种不同的方法可以爬到楼顶呢？
     * 1阶，2阶，.... m阶就是物品，楼顶就是背包。
     * 每一阶可以重复使用，例如跳了1阶，还可以继续跳1阶
     * 问跳到楼顶有几种方法其实就是问装满背包有几种方法。
     * 从1,2,...n中物品中选数;装满容量为n的背包,有多少种方法; 其中物品1的重量就是1,物品0的重量是0.
     * 完全背包问题, 排列,和顺序有关系,先背包在物品;
     * 1. dp[j]：爬到有i个台阶的楼顶，有dp[j]种方法。
     * 2. 初始化dp[0] = 1;
     * 3. dp[j] = dp[j - i]
     * 4. 遍历顺序: 背包从1到n, 物品从1到2;
     * @author benjieqiang
     * @date 2023/8/20 11:17 PM
     */
    public int climbStairs4(int n) {
        if (n <= 1) return n;
        int m = 2; //两个物品中取数, 可以多次取, 求最后和为n的方法;
        int[] dp = new int[n + 1];
        dp[0] = 1; // 爬上台阶0有一种方法;
        for (int j = 1; j <= n; j++) { // 先背包
            for (int i = 1; i <= m; i++) { // 后物品
                if (j >= i) dp[j] += dp[j - i];
            }
        }
        return dp[n];
    }
    @Test
    public void testClimbStairs() {
        int n = 45;
        System.out.println(climbStairs3(n));
        System.out.println(climbStairs4(n));
    }
}
