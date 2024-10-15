package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-24  10:17
 * @Description:
 * 求给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数。
 * s中删除元素后能变成多少个t；
 * 翻译：从s中挑选子串，看能匹配t串多少次；
 *
 * 1. dp[i][j] 从开头到s[i-1]的子串中，出现『从开头到t[j-1]的子串』的 次数。即：前 i 个字符的 s 子串中，出现前 j 个字符的 t 子串的次数。
 * 2. dp公式:
 * s 为babgbag，t 为bag，末尾字符相同，于是 s 有两种选择：这两种情况是相互独立的，因此我们需要将它们的匹配次数相加，得到总的匹配次数。
 *      用s[s.length-1]去匹配掉t[t.length-1]，问题规模缩小：继续考察babgba和ba
 *      不这么做，但t[t.length-1]仍需被匹配，于是在babgba中继续挑，考察babgba和bag
 *  2.1. 末尾字符相同，选或者不选。
 *  s[i-1] == t[j-1]， dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
 *   - dp[i-1][j-1]: 选s末尾元素来匹配t末尾元素，那么问题变成看前i-1的s中有前j-1个t几个？即从开头到s[i-2]的子串s中有多少个从开头到t[j-2]的子串t；
 *   - dp[i-1][j]： 不选s的末尾元素，即s[i-1]，那就是说看前i-1个字符的s中有多少个出现j个字符的t子串个数；
 *  2.2 末尾元素不同，直接忽略当前s末尾元素，从前i-1个字符的s中选符合j个字符的t子串；
 *  s[i -1] != t[j - 1]，此时dp[i][j] = dp[i - 1][j]
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
        for (int i = 0; i < s.length(); i++) dp[i][0] = 1; // 前i个s子串中各自能匹配一个空串t

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
