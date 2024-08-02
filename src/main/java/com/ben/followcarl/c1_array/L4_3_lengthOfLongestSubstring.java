package com.ben.followcarl.c1_array;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-12-16  23:54
 * @Description:
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 思路：
 * 采用滑动窗口，因为求的是最大子串长度，定义一个窗口，以字符的ascii码为key，出现次数为值；
 * 从左往右滑动，如果发现窗口里面的某个字符频次大于1，则说明找到窗口此时已经不满足条件，
 * 则在while循环里面去移除左边界，最后while循环满足条件再跳出。说明找到了最优解。
 *
 *
 * @Version: 1.0
 */
public class L4_3_lengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] window = new int[128];
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < s.length()) {
            window[s.charAt(right)]++;
            while (window[s.charAt(right)] > 1) {
                window[s.charAt(left)]--;
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }

        return res;
    }

    public int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < s.length()) {
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);

            while (map.get(s.charAt(right)) >= 2) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }

        return res;
    }


}
