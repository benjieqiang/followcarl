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
     * 2.1 不放物品i：背包容量为j它所能装物品的最大价值。也就是在0-i-1的物品中取物品放入容量为j的背包得到的最大价值；
     * 物品i重量已经超过背包重量j，放不进去，所以此时最大价值应该是和前面相同，直接覆盖过来；
     * 2.2 放物品i：在0-i-1的物品中选出物品放入背包容量减去物品i的重量所得到的最大价值 + 物品i的价值
     * 3. 初始化首行和首列
     * 首行的意思：拿物品0依次放入0，1，2...bagSize这么大的背包中得到的最大价值；只要背包能装下物品0,则最大价值就是物品0的价值;
     * 物品0的重量weight[0]需要和背包容量(从0到bagSize)比较大小，不超过就放如物品0的价值，如果超过了放不下就是0；
     * 那么很明显当 j < weight[0]的时候，dp[0][j] 应该是 0，因为背包放不下物品0;
     * 当j（背包容量） >= weight[0]时，dp[0][j] 应该是value[0]，因为背包容量能放下物品0的重量
     * 首列：背包容量为0的背包能放的物品当然是0,因为数组已经全部初始化为0，所以首列不需要初始化了；
     * 4. 遍历，for一层物品，for一层背包，确定起始都是1
     * 先遍历物品还是先遍历背包呢?
     * 都可以, 默认都是先物品后背包;
     * 画图来看:
     * <p>
     * 5. 打印dp数组
     * @author benjieqiang
     * @date 2023/6/12 8:42 PM
     */
    public int test2WeiBagProblem(int[] weight, int[] value, int bagSize) {

        int goods = weight.length;
        int[][] dp = new int[goods][bagSize + 1]; //不知道dp数组应该定义多大，可以举例，
        // 比如，最后的结果要想在最右下角进行收获，那意味着得有goods-1行，列需要bagSize+1
        // 从索引从0-goods-1的物品数组中取物品，依次装入0-bagSize的背包中；最右下角是最大能装下的物品价值；

        // 首行初始化：只有背包容量j大于等于物品0的重量,才能放下物品0, 所以从weight[0]开始, 得到的最大价值就是物品0的价值value[0]
        // 首列不需要，首列表示背包容量为0，装不下东西，定义时已经初始化成0了
        for (int j = weight[0]; j <= bagSize; j++) {
            dp[0][j] = value[0];
        }

        for (int i = 1; i < goods; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (j < weight[i]) {
                    // 删除 if (j < weight[i]) 这一判断将导致两大后果：
                    //数组越界：当j<weight[i] 时，访问 dp[i][j - weight[i]] 会导致下标变为负数，导致数组越界错误。
                    //逻辑错误：背包容量不足时仍然放入物品，导致最大价值的计算不准确，进而影响最终的最优解。
                    // 如果当前背包的容量j装不下物品i, 此时第i个物品能放入背包j的最大价值是上一个最大价值；
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

    @Test
    public void test2WeiBagProblem() {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        int maxValue = test2WeiBagProblem(weight, value, bagSize); //35
        System.out.println(maxValue);
    }

    /**
     * @param weight:
     * @param value:
     * @param bagSize:
     * @return void
     * @description 一维数组，滚动数组，用二维数组上面一层来覆盖下一层
     * 当前层是从上一层直接拷贝过来的,每一次更新这一行的数据,降维成一维数组;
     * 重量   价值
     * 物品0  1    15
     * 物品1  3    20
     * 物品2  4    30
     * @author benjieqiang
     * @date 2023/6/12 9:48 PM
     */
    private void test1weiBagProblem(int[] weight, int[] value, int bagSize) {
        int goods = weight.length;
        //定义dp数组：dp[j]表示背包容量为j时，能获得的最大价值，j从0到bagSize；dp[bagSize]存放最大的价值；
        int[] dp = new int[bagSize + 1];
        // dp[0] = 0; 背包容量为0，肯定能装0个物品，对应价值也为0；
        // dp其他非0下标，只要初始化为非0的最小值就可以；这里给0；
        // 遍历顺序：正序物品，物品从0到goods-1，再倒序遍历背包容量
        for (int i = 0; i < goods; i++) {
            for (int j = bagSize; j >= weight[i]; j--) { // 只有j的背包容量大于物品i的重量,才能装下物品i
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        //打印dp数组
        System.out.println(Arrays.toString(dp));
    }

    /**
     * @param weight:
     * @param value:
     * @param bagSize:
     * @return void
     * @description 测试正序物品，正序背包
     *      重量   价值
     * 物品0  1    15
     * 物品1  3    20
     * 物品2  4    30
     * @author benjieqiang
     * @date 2024/12/14 11:46 AM
     */
    private void test1weiBagProblem_false(int[] weight, int[] value, int bagSize) {
        int goods = weight.length;
        //定义dp数组：dp[j]表示背包容量为j时，能获得的最大价值，j从0到bagSize；dp[bagSize]存放最大的价值；
        int[] dp = new int[bagSize + 1];
        // dp[0] = 0; 背包容量为0，肯定能装0个物品，对应价值也为0；dp其他非0下标，只要初始化为非0的最小值就可以；这里给0；
        // 测试正序遍历物品，正序遍历背包
        // 当i = 0时，即拿出物品0要依次往背包0,1，2中放,
        // 1. dp[0] = 0,空背包；
        // dp[1] = max(dp[1], dp[1-weight[0]] + 15) = max(0,15)=15
        // dp[2] = max(dp[2], dp[2 - weight[0]]+15) = max(0,30)=30
        // 背包2实际上能装下的最大价值是15，即只能装下重量为1的物品0。这里把物品0加入了两次；
        for (int i = 0; i < goods; i++) {
            for (int j = weight[i]; j <= bagSize; j--) { // 只有j的背包容量大于物品i的重量,才能装下物品i
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        //打印dp数组
        System.out.println(Arrays.toString(dp));
    }

    /**
     * @param weight:
     * @param value:
     * @param bagSize:
     * @return int
     * @description 错误：测试先遍历背包，再遍历物品
     * 重量   价值
     * 物品0  1    15
     * 物品1  3    20
     * 物品2  4    30
     * @author benjieqiang
     * @date 2024/9/27 5:16 PM
     */
    public int test1weiBagProblem2(int[] weight, int[] value, int bagSize) {
        int[] dp = new int[bagSize + 1];
        dp[0] = 0;
        // 数组初始化完dp=[0,0,0,0,0]
        // 当j = 4时，dp[4]表示容量为4的背包所能装的最大价值；
        // 从0开始遍历物品，dp[4] = max(dp[4],dp[4-weight[0]] + value[0]) = max(dp[4],dp[3]+15) = 15
        // 物品1，dp[4] = max(dp[4], dp[1]+20) = 20
        // 物品2，dp[4] = max(dp[4], dp[0]+30) = 30
        // 先背包后物品, 4号背包最大价值是30,即放入物品2，但是实际上背包4的最大价值是35，也就是放入物品0和物品1（重量分别是1和3）
        // j = 3. 物品0:dp[3] = max(dp[3], dp[3-weight[0]] + value[0]) = max(0,dp[2]+15) = 15
        // 物品1: dp[3] = max(dp[3], dp[3-weight[1]] + value[1]) = max(15, 20) = 20
        // 物品2: 因为j放不下weight[2]，所以dp[3]还是20
        // j = 2, 物品0: dp[2] = max(dp[2], dp[1] + 15) = 15
        // 物品1，2都放不下，
        // j = 1, 物品0: dp[1] = max(dp[1], dp[0]+ 15) = 15
        // 出现了物品0被装了两次，所以是错误的。
        for (int j = bagSize; j >= 0; j--) {
            for (int i = 0; i < weight.length; i++) {
                if (j >= weight[i]) {
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
                }
            }
        }
        //打印dp数组
        System.out.println(Arrays.toString(dp));

        return dp[bagSize];
    }


    @Test
    public void test1WeiBagProblem() {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        // 正序物品倒序背包；[0, 15, 15, 20, 35]
        test1weiBagProblem(weight, value, bagSize);
        // 测试正序物品，正序背包
        test1weiBagProblem_false(weight, value, bagSize);
        // 先背包，后物品[0, 15, 15, 20, 30]，固定了背包之后，也就是说从一堆物品中选，有多次选物品
        test1weiBagProblem2(weight, value, bagSize);

    }

    @Test
    public void testBagProblem() {
        int bagSize = 4;
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};

        int[] dp = new int[bagSize + 1];
        for (int i = 0; i < weight.length; i++) {
            for (int j = bagSize; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }

        System.out.println(dp[bagSize]);
    }
}
