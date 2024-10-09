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
 * dp[i-1]表示到第i-1层有dp[i-1]种方法，再走1步就能到dp[i]层；
 * dp[i-2]表示到第i-2层有dp[i-2]种方法，再走2步就能到dp[i]层；
 * 他两和就是到达dp[i]层所有的方法；
 * 3. 初始化dp[0]和dp[1],dp[2], dp[0]不应该初始化为0或1，因为i是从1开始，表示此时在第一层台阶位置；
 * 4. 遍历顺序，如果从后往前不能确定dp[i -1]和dp[i-2],所以顺序遍历；
 * 5. 举例
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

    /**
     * @param n:
     * @return int
     * @description 利用背包的解法, 比如
     * 升级版：要求每次可以爬[1 - m]个台阶应该怎么写。问有多少种不同的方法可以爬到楼顶呢？
     * 1阶，2阶，.... m阶就是物品，楼顶就是背包。
     * 每一阶可以重复使用，例如跳了1阶，还可以继续跳1阶
     * 问跳到楼顶有几种方法其实就是从1-m个物品中拿物品，装满容量为n的背包有多少种方法
     * 题目：
     * 从物品1，2中选物品，装满容量为n的背包,有多少种方法;
     * dp[j]: 容量j的背包有dp[j]种方法；j [1,n]. new int[n + 1], return dp[n];
     * 装满背包有几种方法，递推公式一般都是dp[i] += dp[i - nums[j]];
     * dp[j]有几种来源，dp[j - 1]，dp[j - 2]，dp[j - 3] 等等，即：dp[j - i]
     * 那么递推公式为：dp[j] += dp[j - i]
     * dp[0] = 1?
     * 递归公式是 dp[i] += dp[i - j]，那么dp[0] 一定为1，
     * dp[0]是递归中一切数值的基础所在，如果dp[0]是0的话，其他数值都是0了。
     * 下标非0的dp[i]初始化为0，因为dp[i]是靠dp[i-j]累计上来的，dp[i]本身为0这样才不会影响结果
     *
     * 完全背包问题, 排列, 和顺序有关系, 先背包在物品;
     * 1. dp[j]：爬到有i个台阶的楼顶，有dp[j]种方法。
     * 2. 初始化dp[0] = 1;
     * 3. dp[j] += dp[j - i]

     * 4. 遍历顺序: 背包从1到n, 物品从1到2;
     * @author benjieqiang
     * @date 2023/8/20 11:17 PM
     */
    public int climbStairs4(int n) {
        if (n <= 1) return n;
        int m = 2; //本次是每次只能爬1/2个台阶，所以是两个物品中取数, 可以多次取, 求最后和为n的方法;
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
        System.out.println(climbStairs(n));
        System.out.println(climbStairs4(n));
    }
}
