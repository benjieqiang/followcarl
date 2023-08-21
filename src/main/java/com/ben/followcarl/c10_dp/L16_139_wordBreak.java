package com.ben.followcarl.c10_dp;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-21  09:52
 * @Description: 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * <p>
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * <p>
 * 题目解读:
 *
 *        1. 从wordDict这堆物品里面拿字符串, 求能够拼接成字符串s, 能否把背包s填满;
 *         dp[i] : 字符串长度为i的话，dp[i]为true，表示可以拆分为一个或多个在字典中出现的单词。
 *         2. wordDict可重复取, 对物品的顺序有要求, 先物品后背包 两个apple和一个pen只有组成s那样子才是所求;
 *          物品的重量是? 列表中拿出一个字符串, 字符串的长度且字符串出现在s[i- len, i)中就是物品的重量
 *          背包的容量是? s.length()
 *         3. if([j, i] 这个区间的子串出现在字典里 && dp[j]是true) 那么 dp[i] = true
 *             if (word.equals(s.substring(i - len, i)) && dp[i - len])
 *         4. dp[0] = true, 完全是为了推导递归公式, 题意s不可能为0;但是dp[1]是在dp[0]基础上推导出来; 其他都初始化为false;
 *         5.
 * @Version: 1.0
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class L16_139_wordBreak {
    /**
     * @param s:
     * @param wordDict:
     * @return boolean
     * @description 完全背包的解法
     * 对顺序有要求, 先背包后物品;
     * @author benjieqiang
     * @date 2023/8/21 10:37 AM
     */
    public boolean wordBreak(String s, List<String> wordDict) {

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int j = 1; j <= s.length(); j++) { // 先背包后物品
            for (String word : wordDict) {
                int len = word.length();
                // j - len的字符串出现在wordDict里面, 并且dp[j - len]也为true;
                if (j >= len && dp[j - len] && word.equals(s.substring(j - len, j))) {
                    dp[j] = true;
                }
            }
        }

        return dp[s.length()];
    }

    /**
     * @param s:
     * @param wordDict:
     * @return boolean
     * @description dp数组的解法
     * 1. dp[i] 含义: dp[i]表示s.substring(0, i)是否可以用字典表示
     *  二是:
     * 2. 如果(0,j)这段字符串可以用
     * @author benjieqiang
     * @date 2023/8/21 10:37 AM
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int j = 1; j <= s.length(); j++) {
            for (int i = 0; i < j; i++) {
                // i到j这段字符串出现在字典里面, 且i之前的字符串也能用字典取表示
                if (dp[i] && wordDict.contains(s.substring(i, j))) {
                    dp[j] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
    @Test
    public void testWordBreak() {
        String s = "applepenapple";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("apple");
        wordDict.add("pen");
        System.out.println(wordBreak(s, wordDict));
    }
}
