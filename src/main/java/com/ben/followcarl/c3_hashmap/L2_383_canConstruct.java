package com.ben.followcarl.c3_hashmap;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-11  21:01
 * @Description: 救赎信
 * 长字符串和短字符串，短字符串中的字符能否由长字符串的字符构成；
 * @Version: 1.0
 */
public class L2_383_canConstruct {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] res = new int[26];
        for (char ch : magazine.toCharArray()) {
            res[ch - 'a']++;
        }
        for (char ch : ransomNote.toCharArray()) {
            res[ch - 'a']--;
        }
        for (int count : res) {
            if (count < 0) return false;
        }
        return true;
    }
}
