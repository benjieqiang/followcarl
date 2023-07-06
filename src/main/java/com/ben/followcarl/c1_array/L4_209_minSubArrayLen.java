package com.ben.followcarl.c1_array;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-06  16:57
 * @Description:
 * 给定一个含有n个正整数的数组和一个正整数target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的连续子数组[numsl, numsl+1, ..., numsr-1, numsr]，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0 。
 * 输入：s = 7, nums = [2,3,1,2,4,3] 输出：2 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * @Version: 1.0
 */
public class L4_209_minSubArrayLen {

    /**
     * @param target:
     * @param nums:
     * @return int
     * @description 暴力解法：双层for循环，一层来找起始位置，一层来找终止位置，求sum，两者求区间之后，每次取最小值；
     * @author benjieqiang
     * @date 2023/7/6 5:01 PM
     */
    public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int sum = 0;
        int subLength = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    subLength = j - i + 1;
                    res = Math.min(res, subLength);
                }
            }
        }
        // 对于target = 11, nums = {1,1,1,1}， 遍历完之后res还是原来的最大值，没有找到符合条件的子序列，所以需要判断一下
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * @param target:
     * @param nums:
     * @return int
     * @description 滑动窗口
     * 1、窗口里放的是：sum值大于或等于target的最小连续子序列
     * 2、窗口的起始位置如何移动：当窗口里面的和大于或等于target就需要移动起始位置
     * 3、窗口的终止位置如何移动：for循环中遍历；
     * @author benjieqiang
     * @date 2023/7/6 5:10 PM
     */
    public int minSubArrayLen2(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int sum = 0;
        int subLength = 0;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            // 动态调节起始位置
            while (sum >= target) {
                subLength = j - i + 1;
                res = Math.min(res, subLength);
                sum -= nums[i++];
            }
        }
        // 对于target = 11, nums = {1,1,1,1}， 遍历完之后res还是原来的最大值，没有找到符合条件的子序列，所以需要判断一下
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    @Test
    public void testMinSub() {
        int target = 7;
        int[]nums = {2,3,1,2,4,3};
//        int target = 11;
//        int[]nums = {1,1,1,1};

        System.out.println(minSubArrayLen2(target, nums));
    }
}
