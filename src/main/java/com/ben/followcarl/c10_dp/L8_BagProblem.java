package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

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
     * 1. dp[i][j]的含义：表示最大价值，是指从0～i的物品中任取物品放入容量为j的背包，得到的最大价值
     * i是从0～weight.length - 1的物品中取，所以dp有weight.length（物品长度）行；
     * j是放入0～bagSize的背包，所以dp有bagSize+1列；
     * 2. dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i]);
     *  2.1 不放物品i：背包容量为j它所能装物品的最大价值。也就是在0-i-1的物品中取物品放入容量为j的背包得到的最大价值；
     *      物品i重量已经超过背包重量j，放不进去，所以此时最大价值应该是和前面相同，直接覆盖过来；
     *  2.2 放物品i：在0-i-1的物品中选出物品放入背包容量减去物品i的重量所得到的最大价值 + 物品i的价值
     * 3. 初始化首行和首列
     * 首行的意思：拿物品0依次放入0，1，2...bagSize这么大的背包中得到的最大价值；只要背包能装下物品0,则最大价值就是物品0的价值;
     * 物品0的重量weight[0]需要和背包容量(从0到bagSize)比较大小，不超过就放如物品0的价值，如果超过了放不下就是0；
        那么很明显当 j < weight[0]的时候，dp[0][j] 应该是 0，因为背包放不下物品0;
        当j >= weight[0]时，dp[0][j] 应该是value[0]，因为背包容量能放下物品0的重量
     * 首列：背包容量为0的背包能放的物品当然是0,因为数组已经全部初始化为0，所以首列不需要初始化了；
     * 4. 遍历，for一层物品，for一层背包，确定起始都是1
     *  先遍历物品还是先遍历背包呢?
     *  都可以, 默认都是先物品后背包;
     * 画图来看:
     *
     * 5. 打印dp数组
     * @author benjieqiang
     * @date 2023/6/12 8:42 PM
     */
    public int test2WeiBagProblem(int[] weight, int[] value, int bagSize) {

        int goods = weight.length;
        int[][] dp = new int[goods][bagSize + 1]; //不知道dp数组应该定义多大，可以举例，
        // 比如，最后的结果要想在最右下角进行收获，那意味着得有goods-1行，列需要bagSize+1
        // 不然会越界

        // 只有j大于等于物品0的背包重量,才能放下物品0, 所以从weight[0]开始, 得到的最大价值就是物品0的价值value[0]
        for (int j = weight[0]; j <= bagSize; j++) {
            dp[0][j] = value[0];
        }

        for (int i = 1; i < goods; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (j < weight[i]) {
                    // 如果当前背包的容量装不下i, 那么此时第i个物品能放入背包j的最大价值是上一个最大价值；
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        // 5. 打印dp数组
        for (int[] res : dp) {
            System.out.println(Arrays.toString(res));
        }
        return dp[goods - 1][bagSize];
    }

    public int test2WeiBagProblem2(int[] weight, int[] value, int bagSize) {
        int[][] dp = new int[weight.length][bagSize + 1];

        for (int j = weight[0]; j <= bagSize; j++) {
            dp[0][j] = value[0];
        }

        // i [1,weight.length-1]
        // j [1,bagSize]
        for (int i = 1; i < weight.length; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (j < weight[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }

        return dp[weight.length - 1][bagSize];
    }

    @Test
    public void test2WeiBagProblem() {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        int maxValue = test2WeiBagProblem2(weight, value, bagSize); //35
        System.out.println(maxValue);
    }

    /**
     * @param weight:
     * @param value:
     * @param bagSize:
     * @return void
     * @description 一维数组，滚动数组，用二维数组上面一层来覆盖下一层
     * 当前层是从上一层直接拷贝过来的,每一次更新这一行的数据,降维成一维数组;
     *         重量   价值
     *   物品0  1    15
     *   物品1  3    20
     *   物品2  4    30
     * @author benjieqiang
     * @date 2023/6/12 9:48 PM
     */
    private void test1weiBagProblem(int[] weight, int[] value, int bagSize) {
        int goods = weight.length;
        //定义dp数组：dp[j]表示背包容量为j时，能获得的最大价值，j从0到bagSize；dp[bagSize]存放最大的价值；
        int[] dp = new int[bagSize + 1];
        // dp[0] = 0; 背包容量为0，肯定能装0个物品，对应价值也为0；
        // dp其他非0下标，只要初始化为非0的最小值就可以；这里给0；
        // 遍历顺序：先遍历物品，物品从0到goods-1，再倒序遍历背包容量
        // 倒序遍历，因为如果是正序遍历：
        // 当i = 0时，即拿出物品0要依次往背包1，2放,因为0号背包放不下，所以是dp[0] = 0
        // dp[1] = dp[1 - weight[0]] + value[0] = dp[0] + value[0] = 15;
        // dp[2] = dp[2- weight[0]] + value[0] = 15+ 15 = 30
        // 此时看一下dp[2]的含义，2号背包所能装的最大价值是30，但是如果按照我们的想法，把物品0拿出来放入到背包2中，
        // 最大价值只能是15，这样就矛盾了，所以是倒序遍历。
        // 倒序遍历：dp[2] = dp[2 - weight[0]] + value[0] = dp[1] + 15 = 15;
        for (int i = 0; i < goods; i++){
            for (int j = bagSize; j >= weight[i]; j--){ // 只有j的背包容量大于物品i的重量,才能装下物品i
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        // 测试遍历顺序，先遍历背包，再遍历物品
        // 当j = 4时，dp[4]表示放入4号背包所能装的最大价值；
        // 从0开始遍历物品，dp[4] = max(dp[4],dp[4-weight[0]] + value[0]) = max(dp[4],dp[3]+15) = 15
        // 物品1，dp[4] = max(dp[4], dp[1]+20) = 20
        // 物品2，dp[4] = max(dp[4], dp[0]+30) = 30
        // 也就是按照先背包后物品的顺序, 4号背包放入物品2得到的最大价值是30，但是实际上背包4的最大价值是35，也就是放入物品0和物品1
//        for (int j = bagSize; j >= weight[0]; j--){
//            for (int i = 0; i < weight.length; i++){
//                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
//            }
//        }
        //打印dp数组
        System.out.println(Arrays.toString(dp));
    }

    public int test1WeiBagProblem2(int[] weight, int[] value, int bagSize){
        int[] dp = new int[bagSize + 1];
        dp[0] = 0;

        for (int i = 0; i < weight.length; i++) {
            for (int j = bagSize; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }

        return dp[bagSize];
    }


    @Test
    public void test1WeiBagProblem() {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        System.out.println(test1WeiBagProblem2(weight, value, bagSize));
    }
}
