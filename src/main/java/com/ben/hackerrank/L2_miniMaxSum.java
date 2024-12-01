package com.ben.hackerrank;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-11-30  10:29
 * @Description:
 * Sample Input
 * 1 2 3 4 5
 * output:
 * 10 14
 * @Version: 1.0
 */
public class L2_miniMaxSum {
    public static void miniMaxSum(List<Integer> arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        long totalSum = 0;
        for (int num : arr) {
            totalSum += num;
            if (num < min) min = num;
            if (num > max) max = num;
        }

        System.out.println((totalSum - max) + " " + (totalSum - min));
    }
    public static void miniMaxSum2(List<Integer> arr) {
        // Write your code here
        int[] nums = new int[arr.size()];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = arr.get(i);
        }
        Arrays.sort(nums);
        long minRes = 0;
        long maxRes = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length - 1) minRes += nums[i];
            if (i > 0) maxRes += nums[i];
        }

        System.out.println(minRes + " " + maxRes);
    }
}
