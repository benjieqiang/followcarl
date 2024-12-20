package com.ben.neetcode.arrayshashing;

import org.junit.Test;

import java.util.HashMap;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-11  20:38
 * @Description: 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 注意：若s 和 t中每个字符出现的次数都相同，则称s 和 t互为字母异位词。
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-anagram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Version: 1.0
 */
public class N2_242_isAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int key = s.charAt(i) - 'a';
            hashMap.put(key, hashMap.getOrDefault(key, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            int key = t.charAt(i) - 'a';
            Integer val = hashMap.get(key);
            if (val == null) {
                return false;
            } else if (val > 1) {
                hashMap.put(key, val - 1);
            } else {
                hashMap.remove(key);
            }
        }
        return hashMap.size() == 0;
    }


    // Time complexity: O(n+m)
    //Space complexity: O(1) since we have at most 26 different characters.
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] map = new int[128];
        for (char ch : s.toCharArray()) {
            map[ch]++;
        }
        for (char ch : t.toCharArray()) {
            map[ch]--;
        }

        for (int i : map) {
            if (i > 0) return false;
        }

        return true;
    }


    @Test
    public void testIsAnagram() {
        String s = "anagram", t = "nagaram";

        System.out.println(isAnagram2(s, t));

    }
}
