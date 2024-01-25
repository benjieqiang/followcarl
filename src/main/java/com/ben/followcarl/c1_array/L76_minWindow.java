package com.ben.followcarl.c1_array;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-12-18  21:22
 * @Description: TODO
 * @Version: 1.0
 */
public class L76_minWindow {
    public String minWindow(String s, String t) {
        if (s == null || s == "" || t == null || t == "" || s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        // 统计t中各字符出现的次数
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int minStart = 0;
        int minLen = Integer.MAX_VALUE;
        int valid = 0; // 已经匹配的字符个数

        while (right < s.length()) {
            char c = s.charAt(right);
            // 更新 window
            window.put(c, window.getOrDefault(c, 0) + 1);

            // 如果need中有该字符，且个数与窗口里的字符数目一样，则说明找到一个字符了。
            if (need.containsKey(c) && window.get(c).equals(need.get(c))) {
                valid++;
            }

            // 如果发现窗口里面的字符和need中的字符数目一样，则说明已经找到解，缩小窗口找最优解
            while (valid == need.size()) {
                char d = s.charAt(left);
                if (need.containsKey(d) && window.get(d).equals(need.get(d))) {
                    valid--;
                }
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }
                window.put(d, window.get(d) - 1);
                left++;
            }
            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    // 其中 65～90号为 26个大写英文字母，97～122号为 26个小写英文字母，其余为一些标点符号、运算符号等

    // 对于数组类型，其下标为 int类型
    // 可以直接使用 char类型变量，默认强制转换，存储的就是字母对应的 ASCII码
    // 比如 t.charAt(i) = "a'，存储的就是 map[97]
    // 条件 need[c] >= window[c] 表示在扩大窗口时，确保窗口中的字符次数不少于字符串 t 中的需求次数。
    // 而条件 need[d] >= window[d] 表示在缩小窗口时，确保缩小后的窗口仍然包含了字符串 t 中字符的正确数量。

    public String minWindow2(String s, String t) {
        int[] need = new int[128]; // need存放t中每个字符出现的词频；
        int[] window = new int[128]; // 统计窗口中每个字符出现次数

        // t中每个字符出现的频次放入need
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }

        // 窗口的左右边；
        int left = 0;
        int right = 0;
        int valid = 0; // 窗口中已经匹配的字符个数
        int minLen = Integer.MAX_VALUE; // 记录最小子串的长度
        int minStart = 0; // 记录最小子串的起点

        while (right < s.length()) {
            char c = s.charAt(right);
            window[c]++; // 扩大窗口
            //
            if (need[c] > 0 && need[c] >= window[c]) valid++;
            // 当满足条件时出现了解，不满足条件，则继续向右移动窗口。
            while (valid == t.length()) {
                char d = s.charAt(left);
                if (need[d] > 0 && need[d] >= window[d]) valid--;
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }
                // 窗口缩小，left右移
                window[d]--;
                left++;
            }
            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }


    public static void main(String[] args) {
//        String s = "ADOBECODEBANC", t = "ABC";
        String s = "aa", t = "aa";
//        输出："BANC"
        System.out.println(new L76_minWindow().minWindow2(s, t));
    }
}
