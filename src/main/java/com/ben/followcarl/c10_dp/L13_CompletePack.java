package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-28  20:10
 * @Description: 完全背包
 * 有N件物品和一个最多能背重量为W的背包。
 * 第i件物品的重量是weight[i]，得到的价值是value[i] 。
 * 每件物品都有无限个（也就是可以放入背包多次），求解将哪些物品装入背包里物品价值总和最大。
 * 完全背包和01背包问题唯一不同的地方就是，每种物品有无限件。
 *
 *
 *
 * 先遍历背包再遍历物品, 先遍历物品再遍历背包
 * 两种都可以.
 * dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i])
 * dp[j]: 不放物品i,即0-i-1的物品中取物品放入容量为j的背包得到的最大价值
 * dp[j - weigh[i]] + value[i]: 放物品i. 剩余背包容量得到的最大价值加上物品i的价值;
 * 代码和01背包区别的地方是:背包的遍历顺序成为了正序,这样能用到上一次的的结果;
 * @Version: 1.0
 */
public class L13_CompletePack {

    // 先物品后背包;
    @Test
    public void testCompletePack(){
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;
        int[] dp = new int[bagWeight + 1];
        for (int i = 0; i < weight.length; i++){ // 遍历物品
            for (int j = weight[i]; j <= bagWeight; j++){ // 遍历背包容量
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        System.out.println(Arrays.toString(dp));
    }

    // 先背包后物品
    @Test
    public void testCompletePackAnotherWay(){
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;
        int[] dp = new int[bagWeight + 1];
        for (int j = 0; j <= bagWeight; j++){ // 遍历背包容量
            for (int i = 0; i < weight.length; i++){ // 遍历物品
                if (j - weight[i] >= 0){
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
    }
}
