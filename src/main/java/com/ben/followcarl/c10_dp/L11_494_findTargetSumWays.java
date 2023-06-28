package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-13  11:40
 * @Description: todo：未搞懂2023.06.28
 *输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 一共有5种方法让最终目标和为3。
 * 把nums分为两部分，left左（正数）集合和right右（负数）集合，他两差是target，和是sum；
 * 从题目给的nums集合中取数，装满容量为left的背包有多少种方法
 * 分成左右两个集合left和right， left的和 = (总和 + target)/2
 * 由题意：left - right = target; // left左集合，right 右集合
 *       left + target = sum; //总和
 * 所以：  left = （sum + target)/2;
 * 比如[1,1,1,1,1]可以分成左集合left = [1,1,1,1], 右集合right = [1];
 * 为何必须整除？如果target为2，此时left = 7/2 不能被2整除；
 * 分成正数集合-负数集合=target
 * 4-1 = 3
 * 3-2 = 1
 * 2-3 = -1
 * 1-4 = -3
 * 所以target为2时，不能找到一个集合满足能够分割成功两份，他们的差是2；
 * 所以left 必须得整除；
 *
 * 给了一个容量为left的背包，问有多少种方式能把背包装满；
 * 装满背包有几种方法的递推公式: dp[j] = dp[j] + dp[j - nums[i]]
 * 定义dp[j]:表示有容量为j的背包有dp[j]种方法能填满背包；
 * 再形象一下：从数组nums中取数，问这些数满足和为j的方法有几个；
 * dp[j] = dp[j] + dp[j - nums[i]]
 * dp[j]: 没拿，不选i这个数的话，对原来的方法不影响；
 * dp[j - nums[i]]: 拿了，选i的话，那么剩下的背包j - nums[i]要满足有dp[j - nums[i]]种方法填满背包；
 *
 * @Version: 1.0
 */
public class L11_494_findTargetSumWays {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) sum += num;
        // 如果目标和大于sum，凑不出来两个集合满足差为target
        if (target < 0 && sum < -target) return 0;
        if ((target + sum) % 2 != 0) return 0; // 如果发现不能被2整除，则说明装不了；
        int size = (target + sum) / 2; // 正数集合的大小
        // dp[j] 表示：填满j这么大容积的包，有dp[j]种方法
        int[] dp = new int[size + 1];
        dp[0] = 1; // 表示用若干个元素组成和为零的方案数，只有一种方案就是什么都不选，所以设为1
        for (int i = 0; i < nums.length; i++) {
            for (int j = size; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[size];
    }

    @Test
    public void testFind() {
        int[] nums = {1,1,1,1,1};
        int target = 3;
        System.out.println(findTargetSumWays(nums, target));
    }
}
