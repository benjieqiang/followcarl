package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-17  15:09
 * @Description:
 * 题目描述
 * 给定一个矩形，宽度为 W，高度为 H，现需要对其进行划分。
 * 现有一个数组 yCutting 用于表示在水平方向上用于切分矩形的切线位置，另有一个数组 xCutting 表示在垂直方向上用于切分矩形的切线位置。
 * 求被切割后的所有子矩形中最大的那块的面积。
 *
 * 输入:
 * 第一行 H：矩形的高度
 *
 * 第二行 W：矩形的宽度
 *
 * 第三行：yCutting 数组的长度
 *
 * 第四行：yCutting 数组
 *
 * 第五行：xCutting 数组的长度
 *
 * 第六行：xCutting 数组
 *
 * 5
 * 4
 * 3
 * 1 2 4
 * 2
 * 1 3
 * 输出
 * 一个整数，表示最大的那块子矩形的面积。
 *
 * 解题思路:
 * 1. 首先读取输入数据，
 * 2. 然后对水平yCutting和垂直xCutting切割数组进行排序
 * 3. 利用辅助函数 getMax 计算水平方向和垂直方向切割区间的最大值。
 * 4. 两个方向上的最大切割区间相乘即得到最大子矩形的面积。
 * @Version: 1.0
 */
import java.util.Scanner;
import java.util.Arrays;

public class L32_RectangleMaxArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取矩形的高度和宽度
        int H = scanner.nextInt();
        int W = scanner.nextInt();

        // 读取yCutting数组的长度和内容
        int yCuttingLength = scanner.nextInt();
        int[] yCutting = new int[yCuttingLength];
        for (int i = 0; i < yCuttingLength; i++) {
            yCutting[i] = scanner.nextInt();
        }

        // 读取xCutting数组的长度和内容
        int xCuttingLength = scanner.nextInt();
        int[] xCutting = new int[xCuttingLength];
        for (int i = 0; i < xCuttingLength; i++) {
            xCutting[i] = scanner.nextInt();
        }

        Arrays.sort(yCutting);
        Arrays.sort(xCutting);

        int maxY = getMax(yCutting, H);
        int maxX = getMax(xCutting, W);

        int maxArea = maxX * maxY;

        System.out.println(maxArea);
    }
    private static int getMax(int[] nums, int dimension) {
        int maxValue = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxValue = Math.max(maxValue, nums[i] - nums[i - 1]);
        }
        // 看最后一个切割点到边界的距离是不是最大的
        maxValue = Math.max(maxValue, dimension - nums[nums.length - 1]);
        return maxValue;
    }
}
