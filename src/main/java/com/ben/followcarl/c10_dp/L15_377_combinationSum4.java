package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-20  21:16
 * @Description: 377. 组合总和 Ⅳ
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * <p>
 * 题目数据保证答案符合 32 位整数范围。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * 有顺序要求, 可以重复取; 完全背包
 * 1. dp[j]从数组中取数得和为target共有dp[j]种方法;
 * 2. dp[j] += dp[j - nums[i]]
 * 3. dp[0] = 1;
 * 4. 遍历顺序: 先背包后物品 j [0, target] i[0, nums.length)
 *  要注意的是: 只有j的背包大于当前物品i的重量才能装进去;
 * 5. 举例
 * @Version: 1.0
 */
public class L15_377_combinationSum4 {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int j = 0; j <= target; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j >= nums[i]) dp[j] += dp[j - nums[i]];
            }
        }
        return dp[target];
    }

    @Test
    public void testCombine() {
        int[] nums = {1, 2, 3};
        int target = 4;

        System.out.println(combinationSum4(nums, target));
    }
}
