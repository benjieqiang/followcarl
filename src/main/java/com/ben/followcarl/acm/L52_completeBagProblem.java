package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-10  11:28
 * @Description: 52. 携带研究材料（第七期模拟笔试）
 * 题目描述
 * 小明是一位科学家，他需要参加一场重要的国际科学大会，以展示自己的最新研究成果。他需要带一些研究材料，但是他的行李箱空间有限。这些研究材料包括实验设备、文献资料和实验样本等等，它们各自占据不同的重量，并且具有不同的价值。
 * <p>
 * 小明的行李箱所能承担的总重量为 N，问小明应该如何抉择，才能携带最大价值的研究材料，每种研究材料可以选择无数次，并且可以重复选择。
 * <p>
 * 输入描述
 * 第一行包含两个整数，N，V，分别表示研究材料的种类和行李空间
 * <p>
 * 接下来包含 N 行，每行两个整数 wi 和 vi，代表第 i 种研究材料的重量和价值
 * <p>
 * 输出描述
 * 输出一个整数，表示最大价值。
 * 输入示例
 * 4 5
 * 1 2
 * 2 4
 * 3 4
 * 4 5
 * 输出示例
 * 10
 * 提示信息
 * 第一种材料选择五次，可以达到最大值。
 * <p>
 * 数据范围：
 * <p>
 * 1 <= N <= 10000;
 * 1 <= V <= 10000;
 * 1 <= wi, vi <= 10^9.
 * @Version: 1.0
 */

import java.util.*;

public class L52_completeBagProblem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int v = sc.nextInt();

        int[] weight = new int[n];
        int[] value = new int[n];

        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }

        int[] dp = new int[v + 1];

        for (int i = 0; i < n; i++) {
            for (int j = weight[i]; j <= v; j++) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }

        System.out.println(dp[v]);

    }
}
