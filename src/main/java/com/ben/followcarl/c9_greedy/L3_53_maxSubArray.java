package com.ben.followcarl.c9_greedy;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-01  09:18
 * @Description:
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释:  连续子数组  [4,-1,2,1] 的和最大，为  6。
 *
 * @Version: 1.0
 */
public class L3_53_maxSubArray {


    /**
     * @param nums:
     * @return int
     * @description  暴力解法:
     *  固定一个位置, 从下一个位置一直累加求各元素和, 如果大于结果集,重新赋值给res;
     *  O n2的算法；
     * @author benjieqiang
     * @date 2023/6/1 9:30 AM
     */
    public int maxSubArray1(int[] nums) {
        if (nums.length == 1) return nums[0];
        int res = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                res = Math.max(sum, res);
            }
        }
        return res;
    }


    /**
     * @param nums:
     * @return int
     * @description 如果连续和是负数，我们就从下一个位置开始，因为负数加上一个数只能让和更小。
     * 一定要先记录再清0；
     * @author benjieqiang
     * @date 2023/8/22 7:06 PM
     */
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE; //如果子序列都为负数，那么也得从最大的负数开始计算求和
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // 求连续和
            res = Math.max(res, sum); //记录最大的和；
            if (sum < 0) sum = 0; // 当连续和是负数，我们选下一个元素作为起点，也就是说把当前的sun清0
        }
        return res;
    }
    @Test
    public void testMaxSubArray() {
        int[] nums = {5,4,-1,7,8};
//        int[] nums = {-2, -1};
        System.out.println(maxSubArray1(nums));
        System.out.println(maxSubArray(nums));
    }
}