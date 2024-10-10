package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-28  20:48
 * @Description: 零钱兑换2
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 * <p>
 * 示例 1:
 * <p>
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * <p>
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * <p>
 *
 * 问多少种方法：装满背包有几种方法的递推公式: dp[j] = dp[j] + dp[j - nums[i]]
 * 问装满时的最大价值：dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
 * 首先明确是完全背包问题: 因为物品能够取多次, 遍历背包的时候是正序;
 * 1. dp[j] 表示容量为j的背包有dp[j]种方法能填满背包/凑成和为amount；; 比如2个2,1个1;1个2,3个1等;
 * 2. dp[j] = dp[j] + dp[j - coins[j]]
 * 参考494题目标和, 凑left时的dp含义:
 * dp[j] 不取物品i, 此时只考虑前i-1个物品,得到的最大容量就是dp[j]
 * dp[j - coins[j]]: 取物品i, 前i-1个物品装满剩下的容量是j - coins[i]他所能装的最大容量;
 * 3. 初始化:dp[0] = 1;
 * 初始化dp数组，表示金额为0时只有一种情况，也就是什么都不装
 * 4. 遍历顺序: 先物品后背包: 物品从0-coins.length-1; 背包从coins[i] 到 amount
 * 解释: 物品数量就是数组的数量所以是物品从0-coins.length-1
 * 背包容量只有大于coins[i]才能装下物品i, 所以从coins[i]开始, amount是背包的最大容量;
 * <p>
 * 关于遍历顺序确定是排列还是组合问题:
 * 1. 组合: 先遍历物品后遍历背包; 比如物品0, 物品1加入背包的顺序只能是{物品0, 物品1}, 不可能出现先物品1后物品0的情况;
 * 2. 排列: 先背包后物品; 背包固定了, 物品成了变量,  那么会出现{物品0,物品1}或{物品1, 物品0}的情况.
 * 可以画一个表取推算一下;
 * @Version: 1.0
 */
public class L14_528_change {

    /**
     * @param amount:
     * @param coins:
     * @return int
     * @description
     * 1. 首先明确是可重复拿,完全背包问题,
     * 2. 再看对顺序是否有要求,无要求,先物品后背包;
     *    先物品后背包, dp += dp[j - weight[i]]
     * 3. 五个步骤
     * @author benjieqiang
     * @date 2023/8/20 9:13 PM
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        // 初始化dp数组，表示金额为0时只有一种情况，也就是什么都不装
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[amount];
    }


    /**
     * @param amount:
     * @param coins:
     * @return int
     * @description 先背包后物品;
     * @author benjieqiang
     * @date 2023/8/21 10:14 AM
     */
    public int change2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        // 初始化dp数组，表示金额为0时只有一种情况，也就是什么都不装
        dp[0] = 1;
        for (int j = 0; j <= amount; j++) {
            for (int i = 0; i < coins.length; i++) {
                if (j >= coins[i]) {
                    dp[j] += dp[j - coins[i]];
                    System.out.println(Arrays.toString(dp));
                }
            }
        }

        return dp[amount];
    }
    @Test
    public void voidTestAmount() {
        int amount = 5;
        int[] coins = {1, 2, 5};
        System.out.println(change(amount, coins)); // 题目对顺序无要求, 所以是组合;
        System.out.println(change2(amount, coins)); // 测试先背包后物品, 得到的是排列
    }
}
