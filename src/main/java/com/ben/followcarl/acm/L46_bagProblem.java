package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-10  07:52
 * @Description: 46. 携带研究材料（第六期模拟笔试）
 * 题目描述
 * 小明是一位科学家，他需要参加一场重要的国际科学大会，以展示自己的最新研究成果。他需要带一些研究材料，但是他的行李箱空间有限。这些研究材料包括实验设备、文献资料和实验样本等等，它们各自占据不同的空间，并且具有不同的价值。
 * <p>
 * 小明的行李空间为 N，问小明应该如何抉择，才能携带最大价值的研究材料，每种研究材料只能选择一次，并且只有选与不选两种选择，不能进行切割。
 * <p>
 * 输入描述
 * 第一行包含两个正整数，第一个整数 M 代表研究材料的种类，第二个正整数 N，代表小明的行李空间。
 * <p>
 * 第二行包含 M 个正整数，代表每种研究材料的所占空间。
 * <p>
 * 第三行包含 M 个正整数，代表每种研究材料的价值。
 * <p>
 * 输出描述
 * 输出一个整数，代表小明能够携带的研究材料的最大价值。
 * 输入示例
 * 6 1
 * 2 2 3 1 5 2
 * 2 3 1 5 4 3
 * 输出示例
 * 5
 * 提示信息
 * 小明能够携带 6 种研究材料，但是行李空间只有 1，而占用空间为 1 的研究材料价值为 5，所以最终答案输出 5。
 * <p>
 * 数据范围：
 * 1 <= N <= 5000
 * 1 <= M <= 5000
 * 研究材料占用空间和价值都小于等于 1000
 * @Version: 1.0
 */

import org.junit.Test;

import java.util.*;

public class L46_bagProblem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int[] weights = new int[m];
            int[] values = new int[m];

            for (int i = 0; i < m; i++) {
                weights[i] = sc.nextInt();
            }

            for (int j = 0; j < m; j++) {
                values[j] = sc.nextInt();
            }

            int res = bagProblem(n, weights, values);

            System.out.println(res);
        }
    }

    private static int bagProblem(int n, int[] weights, int[] values) {
        int[] dp = new int[n + 1];

        dp[0] = 0;
        // 正序物品从0开始，倒序背包
        for (int i = 0; i < weights.length; i++) {
            for (int j = n; j >= weights[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
        return dp[n];
    }

    @Test
    public void test_bagProblem() {
        int m = 1;
        int n = 1;
        int[] weight = {1};
        int[] value = {10};

        System.out.println(bagProblem(n, weight, value));
    }
}
