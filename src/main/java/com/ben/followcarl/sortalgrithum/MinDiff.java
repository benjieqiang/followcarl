package com.ben.followcarl.sortalgrithum;

import java.util.ArrayList;
import java.util.Collections;

public class MinDiff {

    public static void main(String[] args) {
        int[] arr = {20, 1, 4, 5, 9, 10, 6, 8, 7};
        int minDiff = findMinDiff(arr);
        System.out.println("最小差为：" + minDiff);
    }

    public static int findMinDiff(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return -1; // 如果数组长度小于2，则不存在两个元素，返回表示异常
        }

        // 找到最大值和最小值
        int minVal = arr[0];
        int maxVal = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] < minVal) {
                minVal = arr[i];
            }
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }

        // 计算桶的个数和宽度
        int bucketCount = maxVal - minVal + 1;

        // 初始化桶
        ArrayList<Integer>[] buckets = new ArrayList[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }

        // 将元素放到对应的桶中
        for (int num : arr) {
            int index = num - minVal;
            buckets[index].add(num);
        }

        // 查找相邻桶的最小差值
        int minDiff = Integer.MAX_VALUE;
        int prevMax = minVal;
        for (ArrayList<Integer> bucket : buckets) {
            if (bucket.isEmpty()) {
                continue;
            }
            Collections.sort(bucket);
            int currMin = bucket.get(0);
            minDiff = Math.min(minDiff, currMin - prevMax);
            prevMax = bucket.get(bucket.size() - 1);
        }

        return minDiff;
    }
}
