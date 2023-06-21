package com.ben.followcarl.c9_greedy;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-01  09:18
 * @Description: TODO
 * @Version: 1.0
 */
public class L3_53_maxSubArray {


    /**
     * @param nums:
     * @return int
     * @description O n2的算法；
     * @author benjieqiang
     * @date 2023/6/1 9:30 AM
     */
    public int maxSubArray1(int[] nums) {
        int res = Integer.MIN_VALUE;
        for (int j = 0; j < nums.length; j++) {
            int sum = 0;
            for (int i = j; i < nums.length; i++) {
                sum += nums[i];
                if (sum < 0) continue;
                if (sum > res) {
                    res = sum;
                }
            }
        }
        return res;
    }
    // 如果连续和是负数，我们就从下一个位置开始，因为负数加上一个数只能让和更小。
    // 一定要先记录再清0；
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE; //如果子序列都为负数，那么也得从最大的负数开始计算求和
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // 求连续和
            if (sum > res) res = sum; //记录最大的和；
            if (sum < 0) sum = 0; // 当连续和是负数，我们选下一个元素作为起点，也就是说把当前的sun清0
        }
        return res;
    }
    @Test
    public void testMaxSubArray() {
//        int[] nums = {5,4,-1,7,8};
        int[] nums = {-2, -1};
        System.out.println(maxSubArray(nums));
    }
}