package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-24  20:09
 * @Description: 回文子串
 * 1、dp[i][j]表示区间[i,j]的子串是否是回文子串，是true，否false
 * 2、递推公式的推导：看s[i]和s[j]的关系
 *      - s[i] ！= s[j] 一定不是回文子串
 *      - s[i] == s[j]
 *          i和j下标相等，比如a，肯定是
 *          i和j的下标相差1，比如aa，肯定也是
 *          i和j的下标相差大于1，比如cabac，此时i指向首c，j指向尾c，还得看一下dp[i + 1][j - 1]是不是true，
 *
 * 3、初始化都为false，dp[i][j]
 * 4、遍历顺序
 *      *                dp[i][j]
 *      dp[i + 1][j - 1]     *
 *          dp[i][j]从左下角推出来，那么遍历的时候由下到上，从左到右
 *          i从s.length() - 1到0，j是大于等于i的，所以从i开始到s.length()结束
 * 5、举例:
 * @Version: 1.0
 */
public class L39_647_countSubstrings {
    public int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
//        for (boolean[] res : dp) {
//            System.out.println(Arrays.toString(res));
//        }
//        System.out.println("初始化结束");
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) {
                        res++;
                        dp[i][j] = true;
                    } else if (dp[i + 1][j - 1]) {
                        res++;
                        dp[i][j] = true;
                    }
                }
//                for (boolean[] arr : dp) {
//                    System.out.println(Arrays.toString(arr));
//                }
//                System.out.println("****");
            }
        }
        return res;
    }

    // 精简版本
    public int countSubStrings2(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1 || dp[i + 1][j - 1]) {
                        res++;
                        dp[i][j] = true;
                    }
                }
            }
        }
        return res;
    }
    /**
     * @param s:
     * @return int
     * @description 双指针法
     * @author benjieqiang
     * @date 2023/8/22 10:13 PM
     */
    public int countSubstrings2(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += count(s, i, i);
            res += count(s, i, i + 1);
        }

        return res;
    }

    private int count(String s, int i, int j) {
        int res = 0;
        // ij分别左右扩散
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
            res++;
        }
        return res;
    }

    @Test
    public void testCount() {
        String s = "abc"; //3
        System.out.println(countSubstrings2(s));
    }
}
