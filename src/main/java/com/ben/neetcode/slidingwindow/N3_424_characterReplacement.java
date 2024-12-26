package com.ben.neetcode.slidingwindow;

import org.junit.Test;

import java.util.HashMap;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-26  15:19
 * @Description:
 * 替换k的字符使连续字符串最长；
 * @Version: 1.0
 */
public class N3_424_characterReplacement {
    // 相同的最长子字符串(窗口) = 窗口内最大字符个数 + 反转次数
    // 一旦  窗口长度 - 窗口内最大字符个数 > 反转次数  窗口开始移动
    public int characterReplacement(String s, int k) {
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>(); // map用来统计窗口内每个字符的出现次数。
        int l = 0; //滑动窗口的左、右指针。
        int r = 0;
        int sum = 0; // 当前窗口内出现次数最多的字符的出现次数。
        // 扩大窗口
        while (r < s.length()) {
            map.put(s.charAt(r), map.getOrDefault(s.charAt(r), 0) + 1); // 更新当前字符 s.charAt(r) 的频率统计。
            sum = Math.max(sum, map.get(s.charAt(r))); // 更新 sum，保持窗口内出现最多的字符的频率。
            // 窗口大小 - 最大频率次数，一旦差大于k，说明窗口太大了，没法反转k次让窗口最大；
            while ((r - l + 1) - sum > k) {
                map.put(s.charAt(l), map.get(s.charAt(l)) - 1);
                l++;
            }
            // 窗口有效(r - l + 1) - sum <= k
            res = Math.max(res, r - l + 1);
            r++;
        }
        return res;
    }
    @Test
    public void test_character() {
        String str = "AABABBA";
        int k = 1;
        System.out.println(characterReplacement(str, k));
    }
}
