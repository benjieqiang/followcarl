package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-15  09:25
 * @Description: 这个题目是01背包问题》 只不过背包是有两个维度;
 *
 * 理解为有一个容器，这个容器的特征是能装m个0，n个1，装满这个背包有最多有多少个物品
 * dp[i][j]表示装满i个0,j个1的这个背包最大能装dp[i][j]的物品；
 * 背包容量：m个0，n个1；
 * 物品重量：x个0，y个1；需要遍历整个字符串数组,得到0和1的个数;
 * dp[i][j] = max(dp[i][j], dp[i - x][j - y] + 1)
 * dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
 * 含义解释: 表示装满i个0,j个1的背包最大能装dp[i][j]的物品;
 * 联想一维dp数组: dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]) 这里zeroNum和oneNum就是背包o和1的重量;
 * <p>
 * dp[0][0] = 0 : 表示装满0个0,0个1的背包最大能装0个物品; 背包都为0了,那最大能装0个物品; 这和上一题取不取物品不一样;
 * 遍历顺序，先物品再背包
 *
 * 时间复杂度: O(kmn)，k 为strs的长度
 * 空间复杂度: O(mn)
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

            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
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
        System.out.println(findMaxForm2(strs, m, n));
    }
}
