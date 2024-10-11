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
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 * 题目解读:
 * wordDict可重复取, 对物品的顺序有要求, 先物品后背包 两个apple和一个pen只有组成s那样子才是所求;
 *  拿 s = "applepenapple", wordDict = ["apple", "pen"] 举例。
 *  "apple", "pen" 是物品，那么我们要求 物品的组合一定是 "apple" + "pen" + "apple" 才能组成 "applepenapple"。
 *  "apple" + "apple" + "pen" 或者 "pen" + "apple" + "apple" 是不可以的，那么我们就是强调物品之间顺序。
 * 1. 从wordDict这堆物品里面无限制拿字符串, 拼接成字符串s, 能否把背包s填满，并且对顺序有要求;
 * 完全背包，顺序，先背包后物品。
 * 背包的容量：字符串 s 的长度，我们要通过字典中的单词“填满”字符串。
 * 物品：每个字典中的单词就相当于不同的物品，每个单词有不同的长度，起到“填充”字符串的作用。
 *      字符串的长度len且字符串出现在s[i- len, i)中, 物品的重量
 * dp[j] 表示前 j 个字符能否由字典中的单词构成。j从0到s.length();
    boolean[] dp = new boolean[s.length() + 1]
 * 2. if([j, i] 这个区间的子串出现在字典里 && dp[j]是true) 那么 dp[i] = true
 *  从一组物品中选一个物品，当前物品字符串的长度是len；
 *   j - len的字符串出现在wordDict里面, 并且dp[j - len]前j-len的字符串也是可以被字典中的单词所构成的为true;
 *     if (j >= len && dp[j - len] && word.equals(s.substring(j - len, j))) {
 *         dp[j] = true;
 *     }
 * 3. dp[0] = true：空字符串默认可以被构成。完全是为了推导递归公式, 题意s不可能为0;但是dp[1]是在dp[0]基础上推导出来; 其他都初始化为false;
 * 4. 遍历顺序，先背包后物品；j从1到length；依次从字符串数组中拿物品（字符串）；
 * 5. 举例推导：dp[j].以输入: s = "leetcode", wordDict = ["leet", "code"]为例，dp状态如图：
 * j = 0, dp[0] = 1
 * 只有当s中的当前字符串大于字典中取出的单词长度时，进行比较；
 * j = 1,2,3 dp[] = 0;
 * j = 4 >= 4, s前4个单词出现在dict中，且除去这4个单词外，当字符串为空时，dp[j-len] = dp[0]= 1, 所以dp[4] = 1;
 *             遍历字典第二次拿到code，长度满足，但是和此时前4个字符串不一样，跳过；
 * j = 5 >= 4, s前5个单词不出现在dict中，跳过；dp[5] = 0
 * j = 6,7类似；
 * j = 8，  此时前4个单词是code出现在字典中，且dp[j - len] = dp[4] = 1, dp[8] = 1;
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
                // len前面的字符串（j - len的字符串）出现在字典里面, 并且dp[j - len]也为true;
                if (j >= len && dp[j - len] && word.equals(s.substring(j - len, j))) {
                    dp[j] = true;
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
