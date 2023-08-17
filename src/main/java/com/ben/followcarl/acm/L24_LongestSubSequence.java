package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-17  09:54
 * @Description: 给你一个序列X和另一个序列Z，当Z中的所有元素都在X中存在，并且在X中的下标顺序是严格递增的，那么就把Z叫做X的子序列。
 * 例如：Z=<a,b,f,c>是序列X=<a,b,c,f,b,c>的一个子序列，Z中的元素在X中的下标序列为<1,2,4,6>。
 * 现给你两个序列X和Y，请问它们的最长公共子序列的长度是多少？
 * 输入:
 * abcfbc abfcab
 * programming contest //有返回公共子序列长度
 * abcd mnp // 没有返回0
 * <p>
 * 输出:
 * 4
 * 2
 * 0
 * @Version: 1.0
 */

import java.util.Scanner;

public class L24_LongestSubSequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] line = sc.nextLine().split(" ");

            String str1 = line[0];
            String str2 = line[1];

            int res = findRes(str1, str2);
            System.out.println(res);
        }
    }

    private static int findRes(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        int res = 0;
        for (int i = 1; i <= str1.length(); i++) {
            char ch1 = str1.charAt(i - 1);
            for (int j = 1; j <= str2.length(); j++) {
                char ch2 = str2.charAt(j - 1);
                if (ch1 == ch2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                if (dp[i][j] > res) res = dp[i][j];
            }
        }
        return res;
    }
}
