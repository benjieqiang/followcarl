package com.ben.followcarl.c10_dp;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-15  09:25
 * @Description: 理解为有一个容器，这个容器的特征是能装m个0，n个1，装满这个背包有最多有多少个物品
 * dp[i][j]表示装满i个0j个1的这个背包最大能装dp[i[j]的物品；
 * 背包容量：m个0，n个1；
 * 物品重量：x个0，y个1；
 * dp[i][j] = max(dp[i][j], dp[i - x][j - y] + 1)
 * dp[0][0] = 0
 * 遍历顺序，先物品再背包
 * @Version: 1.0
 */
public class L12_474_findMaxForm {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        int oneNum, zeroNum;
        for (String str : strs) {
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
}
