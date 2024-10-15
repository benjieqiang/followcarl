package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-19  10:36
 * @Description: 最长递增子序列，不需要连续，是双层for循环
 * 1. dp[i] 表示以nums[i]结尾的最长递增子序列的长度
 * 2. 比较nums[i]和nums[j]大小，dp[i] = Math.max(dp[i], dp[j] + 1)
 *     解释: j [0,i], dp[j] + 1 表示从0到i-1的最长子序列加1就是当前dp[i]的长度, 当然要和原来的进行比,取最大;
 * 3. dp数组最少也得是包括nums[i], dp[i]初始化为1。对于每个元素 nums[i]，即使它没有比前面的任何元素大，至少它自己可以构成一个子序列，因此初始化 dp[i] = 1，表示单个元素可以构成一个长度为 1 的递增子序列。
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
            if (dp[i] > res) res = dp[i];
        }
        return res;
    }

    public int[] lengthOfLIS2(int[] nums) {
        if (nums.length <= 1) return nums;

        int[] dp = new int[nums.length];
        int[] pre = new int[nums.length]; // 记录前驱索引
        Arrays.fill(dp, 1);
        Arrays.fill(pre, -1); // 初始时所有元素都没有前驱

        int maxLength = 0;
        int maxIndex = 0; // 记录最长子序列的最后一个元素的索引

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    pre[i] = j; // 记录前驱索引
                }
            }

            // 更新最长递增子序列的长度和最后一个元素的索引
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                maxIndex = i;
            }
        }

        // 回溯最长递增子序列
        List<Integer> lis = new ArrayList<>();
        for (int i = maxIndex; i >= 0; i = pre[i]) {
            lis.add(nums[i]);
            if (pre[i] == -1) break; // 当没有前驱时，回溯结束
        }
        System.out.println(lis);

        // 将结果转换为数组并返回（这里需要逆序）
        int[] result = new int[lis.size()];
        for (int i = 0; i < lis.size(); i++) {
            result[i] = lis.get(lis.size() - 1 - i);
        }

        return result;
    }
    @Test
    public void testLengthOfLIS() {
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums));
        int[] result = lengthOfLIS2(nums);
        System.out.println(Arrays.toString(result));
    }
}
