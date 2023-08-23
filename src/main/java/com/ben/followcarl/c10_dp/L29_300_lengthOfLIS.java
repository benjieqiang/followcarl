package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-19  10:36
 * @Description: 最长递增子序列，不需要连续，所有是双层for循环
 * 1. dp[i] 表示以nums[i]结尾的最长递增子序列的长度
 * 2. 比较nums[i]和nums[j]大小，dp[i] = Math.max(dp[i], dp[j] + 1)
 *     解释: dp[j] + 1 表示从0到i-1的最长子序列加1就是当前dp[i]的长度, 当然要和原来的进行比,取最大;
 * 3. dp数组最少也得是包括nums[i], dp[i]初始化为1。
 * 4. 遍历顺序, i从1到nums.length, j从大到小或从小到大去遍历[0,i), 只要把0到i-1的数遍历完了,符合条件就往dp数组加上, 来看是否符合nums[i] > nums[j],是则加入dp数组中;
 *    最后的结果是? 最大的那个吗? 之前的结果是dp[nums.length - 1]里面 再看dp数组的含义: 以nums[nums.length- 1]结尾的最长子序列长度.
 *    不一定包括最后一个元素,所以得有一个结果集来收集最大的子序列数组长度;
 * @Version: 1.0
 */
public class L29_300_lengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) return 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
//            System.out.println(Arrays.toString(dp));
            if (dp[i] > res) res = dp[i];
        }
        return res;
    }

    @Test
    public void testLengthOfLIS() {
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums));
    }
}
