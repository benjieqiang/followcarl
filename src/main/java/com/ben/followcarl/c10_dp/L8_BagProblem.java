package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-12  20:39
 * @Description: 01背包问题
 * @Version: 1.0
 */
public class L8_BagProblem {

    /**
     * @param weight:  物品重量
     * @param value:   物品价值
     * @param bagSize: 背包的大小
     * @return void
     * @description 01背包，利用二维数组实现
     * @author benjieqiang
     * @date 2023/6/12 8:42 PM
     */
    public int test2WeiBagProblem(int[] weight, int[] value, int bagSize) {
        // 1. dp[i][j]的含义：从0～i的物品中任取放入背包容量为j的背包，得到的最大价值
        // i是从0～i的物品中取，所以dp有weight.length（物品长度）行；
        // j是放如0～j的背包，所以dp有bagSize+1列；
        int goods = weight.length;
        int[][] dp = new int[goods][bagSize + 1];
        // 2. dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i]);
        // 3. 初始化首行和首列
        // 首行的意思：拿物品0依次放入bagSize这么大的背包中得到的最大价值；
        // 物品0的重量需要和背包容量比较大小，不超过就放如物品0的价值，如果超过了放不下就是0；
        //for (int i = 1; i <= bagSize; i++) {
        //    dp[0][i] = weight[0] <= i ? value[0] : 0;
        //}
        // 初始化的意思是，从j为当前物品0的位置开始初始化首行的元素为物品0的价值，直到不超过背包大小；
        for (int j = weight[0]; j <= bagSize; j++) {
            dp[0][j] = value[0];
        }
        // 首列：背包容量为0的背包能放的物品当然是0,因为数组已经全部初始化为0，所以首列不需要初始化了；
        // for (int i = 0; i < goods; i++) dp[i][0] = 0;
        // 4. 遍历，for一层物品，for一层背包，确定起始都是1
        for (int i = 1; i < goods; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (j < weight[i]) {
                    // 如果当前背包的容量装不下i, 那么此时第i个物品能放入背包j的最大价值是
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        // 5. 打印dp数组
        for (int i = 0; i < goods; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println("\n");
        }
        return dp[goods - 1][bagSize];
    }

    @Test
    public void test2WeiBagProblem() {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        int maxValue = test2WeiBagProblem(weight, value, bagSize);
        System.out.println(maxValue);
    }

    /**
     * @param weight:
     * @param value:
     * @param bagSize:
     * @return void
     * @description 一维数组，滚动数组，二维数组用最上面一层来依次覆盖下一层
     * @author benjieqiang
     * @date 2023/6/12 9:48 PM
     */
    private void test1weiBagProblem(int[] weight, int[] value, int bagSize) {
        int goods = weight.length;
        //定义dp数组：dp[j]表示背包容量为j时，能获得的最大价值，j从0到bagSize；dp[bagSize]存放最大的价值；
        int[] dp = new int[bagSize + 1];
        // dp[0] = 0; 背包容量为0，肯定能装0个物品，对应价值也为0；
        // dp其他非0下标，只要初始化为非0的最小值就可以；这里给0；
        //遍历顺序：先遍历物品，物品从0到goods-1，再倒序遍历背包容量
//        for (int i = 0; i < goods; i++){
//            for (int j = bagSize; j >= weight[i]; j--){
//                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
//            }
//        }
        // 测试遍历顺序，先遍历背包，再遍历物品
//        for (int j = bagSize; j > weight[1]; j--){
//            for (int i = 0; i < goods; i++){
//                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
//            }
//        }
        //打印dp数组
        for (int j = 0; j <= bagSize; j++){
            System.out.print(dp[j] + " ");
        }
    }

    @Test
    public void test1WeiBagProblem() {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        test1weiBagProblem(weight, value, bagSize);
    }
}
