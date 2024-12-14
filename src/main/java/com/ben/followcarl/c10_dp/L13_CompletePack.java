package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-28  20:10
 * @Description: 完全背包: n个物品放入w容量的背包，物品可以取多次，求解将哪些物品装入背包里物品价值总和最大。
 * 第i件物品的重量是weight[i]，得到的价值是value[i] 。
 * 完全背包和01背包问题唯一不同的地方就是，每种物品有无限件。
 *
 * 先遍历背包再遍历物品, 先遍历物品再遍历背包
 * 两种都可以.
 * 定义dp数组：dp[j]表示背包容量为j时，能获得的最大价值，j从0到bagSize；dp[bagSize]存放最大的价值；
 * int[] dp = new int[bagSize + 1];
 * dp[0] = 0; 背包容量为0，肯定能装0个物品，对应价值也为0；
 * dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i])
 * dp[j]: 不放物品i,即0-i-1的物品中取物品放入容量为j的背包得到的最大价值
 * dp[j - weigh[i]] + value[i]: 放物品i. 剩余背包容量得到的最大价值加上物品i的价值;
 * 代码和01背包区别的地方是:背包的遍历顺序成为了正序,这样能用到上一次的的结果;
 *
 * 关于遍历顺序确定是排列还是组合问题:
 * 1. 组合: 先遍历物品后遍历背包; 比如物品0, 物品1加入背包的顺序只能是{物品0, 物品1}, 不可能出现先物品1后物品0的情况;
 * 2. 排列: 先背包后物品; 背包固定了, 物品成了变量,  那么会出现{物品0,物品1}或{物品1, 物品0}的情况.
 *
 * 背包问题：
 * 1. 看求最大价值还是多少种方法？
 * 问多少种方法：装满背包有几种方法的递推公式: dp[j] = dp[j] + dp[j - nums[i]]
 * 问装满时的最大价值：dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
 * 2. 重复取？重复就是完全背包；不重复就是01背包
 *   2.1 完全背包：都是正序，组合先物品后背包，背包遍历从weight[1]开始，排列先背包后物品，只有背包大于等于物品重量才能加入；
 *      1. 组合: 先物品后遍历背包; 比如物品0, 物品1加入背包的顺序只能是{物品0, 物品1}, 不可能出现先物品1后物品0的情况;
 *      2. 排列: 先背包后物品; 背包固定了, 物品成了变量,  那么会出现{物品0,物品1}或{物品1, 物品0}的情况.
 *   2.2 01背包：一维，正序物品倒序背包；
 *
 * @Version: 1.0
 */
public class L13_CompletePack {

    // 先物品后背包; 组合
    @Test
    public void testCompletePack(){
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        int[] dp = new int[bagSize + 1];
        for (int i = 0; i < weight.length; i++){ // 遍历物品
            for (int j = weight[i]; j <= bagSize; j++){ // 遍历背包容量
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        System.out.println(Arrays.toString(dp));
    }

    // 先背包后物品，排列
    @Test
    public void testCompletePackAnotherWay(){
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        int[] dp = new int[bagSize + 1];
        for (int j = 0; j <= bagSize; j++){ // 遍历背包容量
            for (int i = 0; i < weight.length; i++){ // 遍历物品
                if (j >= weight[i]){ // 背包容量为j时，能装下物品i的重量weight[i];
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
    }
}
