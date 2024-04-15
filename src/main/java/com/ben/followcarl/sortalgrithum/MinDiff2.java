package com.ben.followcarl.sortalgrithum;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-03-15  12:23
 * @Description: TODO
 * @Version: 1.0
 */

import java.util.Arrays;

public class MinDiff2 {

    public static void main(String[] args) {
        int[] arr = {20, 1, 4, 5, 9, 10, 6, 8, 7};
        int minDiff = findMinDiff(arr);
        System.out.println("最小差为：" + minDiff);
    }

    public static int findMinDiff(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return -1;
        }

        // 找到最大值和最小值
        int minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (arr[i] < minVal) {
                minVal = arr[i];
            }
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }

        // 计算桶的个数和宽度
        int bucketWidth = 1;
        int bucketCount = maxVal - minVal + 1;

        // 初始化桶
        int[][] buckets = new int[bucketCount][n];
        int[] bucketSizes = new int[bucketCount];

        // 将元素放到对应的桶中
        for (int i = 0; i < n; i++) {
            int index = (arr[i] - minVal) / bucketWidth;
            buckets[index][bucketSizes[index]++] = arr[i];
        }

        // 对每个桶进行排序
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSizes[i] > 0) {
                Arrays.sort(buckets[i], 0, bucketSizes[i]);
            }
        }

        // 计算相邻桶的最小差值
        int minDiff = Integer.MAX_VALUE;
        int prevMax = buckets[0][0];
        for (int i = 1; i < bucketCount; i++) {
            if (bucketSizes[i] == 0) {
                continue;
            }
            int currMin = buckets[i][0];
            int diff = currMin - prevMax;
            if (diff < minDiff) {
                minDiff = diff;
            }
            prevMax = buckets[i][bucketSizes[i] - 1];
        }

        return minDiff;
    }
}