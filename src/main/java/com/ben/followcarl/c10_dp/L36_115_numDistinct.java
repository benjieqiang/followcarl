package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-24  10:17
 * @Description: todo: dp[i-1][j-1]
 * 求给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数。
 * s中删除元素后能变成多少个t；
 * 1. dp[i][j] 以i-1结尾的s中有以j-1为结尾的t的个数；
 * 2. 递归公式:
 *  s[i-1] == t[j-1]， dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
 *      - dp[i-1][j-1]: 当两个元素相同时，不考虑s[i-1]和t[j-1]的字母，就等于上一个元素s[i-2]的s中t[j-2]的结尾t的个数
 *      - dp[i-1][j]： 不使用s[i-1]的元素，
 *      比如s = bagg, t = bag， s[3] == t[2]
 *      可以使用s[3]进行匹配,也可以不使用s[3]进行匹配:
 *      使用s[3]匹配得到的是: s[0]s[1]s[3]
 *      不使用s[3]匹配, 那就意味着t[2]也不能用: s[0]s[1], t[0]t[1]
 *
 *  s[i -1] != t[j - 1]，此时dp[i][j] = dp[i - 1][j]，解释：模拟在s中删除这个元素,不考虑当前元素, 不使用s[i - 1]的元素进行匹配的个数；
 *
 * 3. 初始化 dp[i][j]从哪些可以推出来? 画图进行分析；
 * dp[i - 1][j - 1]  dp[i -1][j]
 * dp[i][j - 1]      dp[i][j]
 *
 * dp[i][j] 可以由左上和上方推导出来，因此，需要初始化的首行和首列以及dp[0][0]
 * dp[0][0] = 1 表示两个空串，s可有由1个t组成；
 * dp[i][0] = 首列；以i-1结尾的s中出现空串t的个数是1；把s中所有元素都删了, 那就有1个;
 * dp[0][j] = 首行；s是空串，t是j长度，所以个数是0；
 *
 * 4. 遍历顺序
 * i从1到s.length; j从1到t.length; 最后的结果就在dp[s.length()][t.length()]里
 * 5. 打印dp数组
 * @Version: 1.0
 */
public class L36_115_numDistinct {

    public int numDistinct(String s, String t) {
        if (s.length() < t.length()) return 0;
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < s.length(); i++) dp[i][0] = 1; // 初始化的重点; 首列

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i -1][j];
                } else {
                    dp[i][j] = dp[i -1][j];
                }
            }
        }

        return dp[s.length()][t.length()];
    }

    /**
     * @param s:
     * @param t:
     * @return int
     * @description
     * 1. dp数组的含义
     * 2. dp公式的推导
     *     元素相同时:
     *     元素不同时, 相当于删除下标i-1的字符剩下的s与t进行比较;
     * 3. 初始化和之前不一样
     * @author benjieqiang
     * @date 2023/8/22 9:19 PM
     */
    public int numDistinct2(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (tLen > sLen) return 0;

        int[][] dp = new int[sLen + 1][tLen + 1];
        for (int i = 0; i < sLen; i++) dp[i][0] = 1;

        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[sLen][tLen];
    }
    @Test
    public void testNumDistinct() {
        String s = "babgbag", t = "bag"; //5
        System.out.println(numDistinct(s, t));
    }
}
