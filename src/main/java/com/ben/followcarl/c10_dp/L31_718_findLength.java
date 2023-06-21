package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-19  12:13
 * @Description: 给两个整数数组 nums1 和 nums2 ，返回 两个数组中最长的公共子数组长度
 * 1. dp[i][j] 表示以i-1结尾的nums1和以j-1结尾的nums2的最长重复子数组长度；
 * 2. if (nums[i - 1] == nums[j - 1]) dp[i][j] = dp[i-1][j-1] + 1;
 * 元素相同时，一起往后退一位；
 * 表示nums1第i个元素的前一个元素和nums2第j个元素的前一个元素进行比较得到的最长子数组长度 + 第i个元素和第j个元素相同时的长度1；
 * 3. dp[i][0] dp[0][j] 第一列和第一行初始化为0；
 * 一个以i-1结尾的数组nums1和一个空数组的最长重复子数组长度位0；
 * 因为如果首个元素相同的话，nums1[0]和nums2[0]相同的话，dp[1][1] = dp[0][0] + 1, 为0满足题意；
 * 4. 双层for循环从1开始分别遍历nums1和nums2。
 * 5. 利用res记录最大的dp[i][j]
 * @Version: 1.0
 */
public class L31_718_findLength {
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1]; // dp数组为啥要长度加1，因为在遍历时if条件中的判断条件时i-1和j-1
        // 当i=nums1.length时，j=nums2.length时，dp[i][j]表示的是以nums1.length-1结尾的nums1和以nums2.length-1结尾的nums2的最长重复
        // 子数组长度，代表的是最后一个元素。
        int res = 0;
        // 首行首列没有意义，所以从i=1和j=1开始遍历
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if (dp[i][j] > res) res = dp[i][j];
            }
        }
        return res;
    }

    @Test
    public void testFindLength() {
        int[] nums1 = {1, 2, 3, 2, 1}, nums2 = {3, 2, 1, 4, 7};
        System.out.println(findLength(nums1, nums2));
    }
}
