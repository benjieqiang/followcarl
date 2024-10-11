package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-11  07:43
 * @Description:
 * 57. 爬楼梯（第八期模拟笔试）
 * 题目描述
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬至多m (1 <= m < n)个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 输入描述
 * 输入共一行，包含两个正整数，分别表示n, m
 * 输出描述
 * 输出一个整数，表示爬到楼顶的方法数。
 * 输入示例
 * 3 2
 * 输出示例
 * 3
 * 提示信息
 * 数据范围：
 * 1 <= m < n <= 32;
 *
 * 当 m = 2，n = 3 时，n = 3 这表示一共有三个台阶，m = 2 代表你每次可以爬一个台阶或者两个台阶。
 *
 * 此时你有三种方法可以爬到楼顶。
 *
 *
 * 1 阶 + 1 阶 + 1 阶段
 * 1 阶 + 2 阶
 * 2 阶 + 1 阶
 * 和组合问题2不同的是，这里i和j都从1开始，
 * 1. dp[j]. 爬到j层的方法数；
 * 2. dp[j] += dp[j - i]. 由其他层累加上来的；
 * 3. dp[0] = 1
 * 4. 先背包后物品，排列问题，j从1到m，i从1到n；
 * 5. 举例：
 * @Version: 1.0
 */
import java.util.*;

public class L57_climbStairs {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextInt()) {
                int n = sc.nextInt();
                int m = sc.nextInt();

                int res = bagProblem(n, m);

                System.out.println(res);
            }
        }

        private static int bagProblem(int n, int m) {
            int[] dp = new int[n + 1];
            // dp[j] = dp[j] + dp[j - i] i[1,m];

            dp[0] = 1;
            for (int j = 1; j <= n; j++) {
                for (int i = 1; i <= m; i++) {
                    if (j >= i) dp[j] += dp[j - i];
                }
            }

            return dp[n];

        }
}
