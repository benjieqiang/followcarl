package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-15  09:25
 * @Description: 这个题目是01背包问题》 只不过背包是有两个维度;
 *
 * 理解为有一个容器，这个容器的特征是能装m个0，n个1，装满这个背包有最多有多少个物品
 * dp[i][j]表示装满i个0,j个1的这个背包最大能装dp[i][j]的物品；
 * 即表示装满i 个 0 和j 个 1 时，最多能选择的字符串个数。
 * 背包容量：m个0，n个1；
 * 每个物品可以看作一个字符串，字符串的重量是它包含的 0 和 1 的数量。
 * 物品重量：每个字符串中拥有x个0，y个1；遍历整个字符串数组,找出每个字符串中0和1的数量；
 * dp[i][j] = max(dp[i][j], dp[i - x][j - y] + 1)

 * 所以递推公式：dp[i][j] = max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
 * 假设背包最多能装入 5 个 0 和 3 个 1，现在遇到一个字符串，它包含 2 个 0 和 1 个 1。
 * 那么对于当前的背包 dp[5][3]，我们有两个选择：
 *
 * 不放这个字符串：最大值就是之前的状态，即 dp[5][3]。
 * 放这个字符串：那么背包剩下的容量是 dp[5 - 2][3 - 1] = dp[3][2]，并且由于放入了当前的字符串，最大值要加 1，所以是 dp[3][2] + 1。
 *
 * 字符串的zeroNum和oneNum相当于物品0和物品1的重量（weight[i]），字符串本身的个数相当于物品的价值（value[i]）
   将当前字符串放入背包的情况，意味着之前背包容量少了zeroNum 个 0 和 oneNum 个 1，但多装了一个字符串，所以价值加 1。
 * dp[0][0] = 0 : 表示装满0个0,0个1的背包最大能装0个物品; 背包都为0了,那最大能装0个物品; 这和上一题取不取物品不一样;
 * 遍历顺序，先物品再背包
 *
 * 时间复杂度: O(kmn)，k 为strs的长度
 * 空间复杂度: O(mn)
 *
 * ### 1. **经典 0/1 背包问题**：
 * - 每个物品只能选一次，需要从前一层（前 \(i-1\) 个物品）继承状态。
 * - 因为当前物品的选择依赖于前 \(i-1\) 层，不能在同一层更新，否则会导致重复选择物品的问题。
 * - 公式：`dp[i][j] = max(dp[i-1][j], dp[i-1][j - weight[i]] + value[i])`
 *
 * ### 2. **多维背包问题或字符串背包问题**：
 * - 物品可以根据多个维度（如 0 和 1 的数量）来选择，当前状态不会影响同一层的其他状态。
 * - 同一层更新是安全的，因为每次更新都会减少对应的维度容量，不会重复选择物品。
 * - 公式：`dp[i][j] = max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1)`
 *
 * ### 区别：
 * - **经典 0/1 背包问题**：逐层更新，防止重复选择物品。
 * - **多维背包问题**：同层更新，不会出现重复选择问题。
 *
 * @Version: 1.0
 */
public class L12_474_findMaxForm {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        int oneNum, zeroNum;
        for (String str : strs) { // 先物品,统计0和1的个数;
            oneNum = 0;
            zeroNum = 0;
            for (char ch : str.toCharArray()) {
                if (ch == '0') {
                    zeroNum++;
                } else {
                    oneNum++;
                }
            }
            //倒序遍历
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }


    public int findMaxForm2(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            int zeroNum = 0;
            int oneNum = 0;
            for (char ch : str.toCharArray()) {
                if (ch == '0') zeroNum++;
                else if (ch == '1') oneNum++;
            }

            for (int i = zeroNum; i <= m; i++) {
                for (int j = oneNum; j <= n; j++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }

        return dp[m][n];
    }

    @Test
    public void testFindMaxForm() {
//        strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;
        System.out.println(findMaxForm(strs, m, n));
        System.out.println(findMaxForm2(strs, m, n));
    }
}
