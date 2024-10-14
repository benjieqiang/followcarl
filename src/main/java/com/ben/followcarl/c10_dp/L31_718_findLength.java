package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-19  12:13
 * @Description: 一定要理解dp的定义;
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中最长的公共子数组长度
 * 这个题目求解的是: 最长连续子序列
 * 1. dp[i][j] 表示以i-1结尾的nums1和以j-1结尾的nums2的 最长重复子数组长度；i和j都从1开始。
 *    如果是表示以i结尾的nums1和以j结尾的nums2的 最长重复子数组长度，初始化麻烦，比如dp[0][*] 和 dp[*][0]
 *      初始化dp[i][0]遍历nums1 只要nums1中元素和nums2[0]一样,则置为1;
 *      初始化dp[0][j] 遍历nums2 只要nums2中元素和nums1[0]一样,则置为1;、、
 *    所以使用dp[i][j]表示以 nums1[i-1] 结尾和以 nums2[j-1] 结尾的最长重复子数组长度（即常见的定义方式）。
 * 2. if (nums[i - 1] == nums[j - 1]) dp[i][j] = dp[i-1][j-1] + 1; 不能是dp[i][j - 1] 或dp[i - 1][j]
 *      同进后退 =》元素相同时，一起往后退一位；
 *      表示nums1第i个元素的前一个元素和nums2第j个元素的前一个元素进行比较得到的最长子数组长度 + 第i个元素和第j个元素相同时的长度1；
 * 3. dp[i][0] dp[0][j] 第一列和第一行初始化为0 =》 理解: 一个以i-1结尾的数组nums1和一个空数组的最长重复子数组长度为0；
 *      因为如果首个元素相同的话，nums1[0]和nums2[0]相同的话，dp[1][1] = dp[0][0] + 1, 为0满足题意；
 * 4. 双层for循环从1开始<=nums.length分别遍历nums1和nums2。所以定义dp数组要大一位;
 * 5. 最终dp[nums1.length][nums2.length]并不是记录最大值的地方, 比如[1,2,3,2,1]和[3,2,1,4,7] 的公共子序列,
 *      nums1到达了末尾,但是nums2是在中间位置; 得遍历得到最大的dp[i][j];
 *    利用res记录最大的dp[i][j]
 * @Version: 1.0
 */
public class L31_718_findLength {
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1]; // dp数组为啥要长度加1，因为在遍历时if条件中的判断条件时i-1和j-1
        // 当i=nums1.length时，j=nums2.length时，dp[i][j]表示的是以nums1.length-1结尾的nums1和以nums2.length-1结尾的nums2
        // 的最长重复子数组长度，代表的是最后一个元素。
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
