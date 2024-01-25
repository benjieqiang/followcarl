package com.ben.followcarl.c3_hashmap;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-11  21:01
 * @Description: 救赎信
 * 长字符串和短字符串，短字符串中的字符能否由长字符串的字符构成；
 * 先遍历长字符串里面的字符，存入map，键是对应的ascii码，值是频次；
 * 再遍历段字符串里面的字符，map[ch]--, 最后遍历map，如果map里面有小于0的频次，则说明长字符串不能完全覆盖短字符串。
 * @Version: 1.0
 */
public class L2_383_canConstruct {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] res = new int[26]; // ransomNote and magazine consist of lowercase English letters
        for (char ch : magazine.toCharArray()) {
            res[ch]++;
        }
        for (char ch : ransomNote.toCharArray()) {
            res[ch]--;
        }
        for (int count : res) {
            if (count < 0) return false;
        }
        return true;
    }

    public boolean canConstruct2(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        int[] map = new int[128];
        for (char ch : magazine.toCharArray()) {
            map[ch]++;
        }

        for (char ch : ransomNote.toCharArray()) {
            map[ch]--;
        }

        for (int count : map) {
            if (count < 0) return false;
        }
        return true;
    }
}
