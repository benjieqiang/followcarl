package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-13  08:49
 * @Description:  这道题是01背包问题,往背包容量为sum/2的背包想装多少装多少;
 * 有一堆石头，每块石头的重量都是正整数。（划重点）
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。
 *
 * 也就是说：每次两个石头撞击，小的死了，大的石头重量变成大 - 小；求最后剩下的唯一一个石头重量
 * 再进一步：
 * 尽量让石头分成重量相同的两堆，相撞之后剩下的石头最小，这样就化解成01背包问题
 * 求出这些石头放入sum/2的背包得到的最大价值或重量dp，最终用sum - dp * 2;
 * 01背包问题： 从stones数组中拿石头，装入容量为sum/2的背包，求最大能装多少石头？石头重量和价值是一样的。
 * 比如： 2,7,4,1,8,1这堆石头，sum = 23，sum / 2 = 11
 * 凑一堆石头组成了和为11的石头：2，7，1，1
 * 和为12的石头：8，4
 * 两堆石头一撞就是最小的值；
 * 石头只能用一次；
 * 和分割相等和的子序列问题相似，只不过最后返回的是两堆石头的差值
 * bagSize = sum / 2；
 * 1. dp[j] 表示背包容量为j能装的最大重量（价值）的石头 ,在定义dp数组时，dp[bagSize + 1]的大小；
 * 2. dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i])
 * 3. dp[0] = 0
 * 4. for i in (0, stones.length)
 *       j= bagSize; j >= stones[i]; j--
 * 5. return sum - dp[bagSize] - dp[bagSize];
 * @Version: 1.0
 */
public class L10_1049_lastStoneWeightII {
    public int lastStoneWeightII(int[] stones) {
        if (stones.length == 0) return 0;
        int sum = 0;
        for (int num : stones) sum += num;
        int bagSize = sum / 2;

        int[] dp = new int[bagSize + 1];

        for (int i = 0; i < stones.length; i++) { // 从石头数组里面选石头,
            for (int j = bagSize; j >= stones[i]; j--) { // 放入背包容量为j的背包里, j要从bagSize起步到当前背包只能装stones[i]重量的石头为止;
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - dp[bagSize] - dp[bagSize];
    }

    public int lastStoneWeightII2(int[] stones) {
        int sum = 0;
        for (int num : stones) sum += num;
        int bagSize = sum / 2;

        int[] dp = new int[bagSize + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = bagSize; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
            System.out.println(Arrays.toString(dp));
        }
        return sum - dp[bagSize] * 2;
    }

    @Test
    public void testStones() {
        int[] stones = {2,7,4,1,8,1};
        System.out.println(lastStoneWeightII2(stones));
    }
}
