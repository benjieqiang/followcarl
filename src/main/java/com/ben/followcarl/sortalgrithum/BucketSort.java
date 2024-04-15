package com.ben.followcarl.sortalgrithum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-03-15  11:56
 * @Description: 桶排序
 * @Version: 1.0
 */


public class BucketSort {

    public static void bucketSort(int[] array) {
        int minValue = array[0];
        int maxValue = array[0];
        int n = array.length;
        if (n < 2) {
            return; // 如果数组长度小于2，则不存在两个元素，返回表示异常
        }

        // 1. 找到数组中的最大值和最小值
        for (int num : array) {
            if (num < minValue) {
                minValue = num;
            }
            if (num > maxValue) {
                maxValue = num;
            }
        }

        // 2. 计算桶的数量
        int bucketCount = (maxValue - minValue) / n + 1;

        // 3. 创建二维数组作为桶，并初始化
        List<LinkedList<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new LinkedList<>());
        }

        // 将元素放入桶中
        for (int i = 0; i < n; i++) {
            int bucketIndex = (array[i] - minValue) / n;
            buckets.get(bucketIndex).add(array[i]);
        }

        // 对每个桶中的元素进行排序
        for (LinkedList<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        // 合并桶中的元素到原数组中
        int index = 0;
        for (LinkedList<Integer> bucket : buckets) {
            for (int num : bucket) {
                array[index++] = num;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {29, 25, 3, 49, 9, 37, 21, 43};
        bucketSort(array);

        System.out.println("排序后的数组：");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}