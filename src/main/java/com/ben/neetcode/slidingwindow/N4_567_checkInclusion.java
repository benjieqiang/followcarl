package com.ben.neetcode.slidingwindow;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-26  16:16
 * @Description: 判断字符串 s2 中是否包含字符串 s1 的排列，即判断 s2 是否包含 s1 的某个排列的子串。
 * @Version: 1.0
 */
public class N4_567_checkInclusion {
    // 笨方法，Omn, O1， 遍历s2,每len1个字符串和s1进行比较。
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        for (int i = 0; i <= s2.length() - len1; i++) {
            String sub = s2.substring(i, i + len1);
            if (check(s1, sub)) return true;
        }
        return false;
    }
    private boolean check(String s1, String s2) {
        int[] map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            map[s1.charAt(i) - 'a']++;
            map[s2.charAt(i) - 'a']--;
        }
        for (int i : map) {
            if (i != 0) return false;
        }
        return true;
    }

    // O(n) O(1)
    public boolean checkInclusion2(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (n1 > n2) return false;
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];

        // 统计 s1 和 s2 前 n1 个字符的频率
        for (int i = 0; i < n1; i++) {
            cnt1[s1.charAt(i) - 'a']++;
            cnt2[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(cnt1, cnt2)) return true;

        // 滑动窗口是每次增加右边窗口，减少左边窗口对吧，再比较窗口中的字符频率和s1是否相同
        for (int i = n1; i < n2; i++) {
            cnt2[s2.charAt(i) - 'a']++;
            cnt2[s2.charAt(i - n1) - 'a']--;
            if (Arrays.equals(cnt1, cnt2)) return true;
        }
        return false;
    }
}
