package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-08-02  16:36
 * @Description: 区间和
 * 题目描述
 * 给定一个整数数组 Array，请计算该数组在每个指定区间内元素的总和。
 * 输入描述
 * 第一行输入为整数数组 Array 的长度 n，接下来 n 行，每行一个整数，表示数组的元素。随后的输入为需要计算总和的区间下标：a，b （b > = a），直至文件结束。
 * 输出描述
 * 输出每个指定区间内元素的总和。
 * 输入示例
 * 5
 * 1
 * 2
 * 3
 * 4
 * 5
 * 0 1
 * 1 3
 * 输出示例
 * 3
 * 9
 * 提示信息
 * 数据范围：
 * 0 < n <= 100000
 *
 * 前缀和的概念：前缀和 在涉及计算区间和的问题时非常有用！
 *示例
 * 假设输入为：
 * 5
 * 1 2 3 4 5
 * 0 2
 * 1 3
 * 输入解析：
 *
 * 数组长度为5，数组nums为[1, 2, 3, 4, 5]。
 * 前缀和数组pNums计算为[1, 3, 6, 10, 15]。
 * 查询结果：
 *
 * 对于查询0 2：子数组和为pNums[2] = 6，即1 + 2 + 3 = 6。
 * 对于查询1 3：子数组和为pNums[3] - pNums[0] = 10 - 1 = 9，即2 + 3 + 4 = 9。
 * @Version: 1.0
 */

import java.util.Scanner;

public class L58_IntevalSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int[] nums = new int[length];
        int[] pNums = new int[length];

        int presum = 0; // 用于保存当前的累积和
        for (int i = 0; i < length; i++) {
            nums[i] = sc.nextInt();
            presum += nums[i];
            pNums[i] = presum; // 存储从数组开始到第i个元素的累积和（前缀和）
        }

        while (sc.hasNextInt()) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            int sum;
            // a == 0表示子数组从起始位置开始，此时子数组和直接为pNums[b]
            if (a == 0) {
                sum = pNums[b];
            } else {
                // 如果a != 0，子数组和为pNums[b] - pNums[a - 1]。这是因为pNums[b]是从起始位置到b的和，而pNums[a - 1]是从起始位置到a-1的和，
                // 因此其差值就是从a到b的和。
                sum = pNums[b] - pNums[a - 1];
            }
            System.out.println(sum);
        }
    }

}