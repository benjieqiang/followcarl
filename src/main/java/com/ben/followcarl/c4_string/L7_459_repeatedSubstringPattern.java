package com.ben.followcarl.c4_string;

import org.junit.Test;

/**
 * 子串长度为 1：子串为 "a"，重复构成字符串为 "aaa"，不等于 "aba"。
 * 459. Repeated Substring Pattern
 * Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.

 * Example 1:
 *
 * Input: s = "abab"
 * Output: true
 * Explanation: It is the substring "ab" twice.
 * Example 2:
 *
 * Input: s = "aba"
 * Output: false
 * Example 3:
 *
 * Input: s = "abcabcabcabc"
 * Output: true
 * Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
 *
 */
public class L7_459_repeatedSubstringPattern {
    /**
     * @param s:
     * @return boolean
     * @description 当一个字符串由重复子串组成的，最长相等前后缀不包含的子串就是最小重复子串
     *
     * n % (n -m) == 0 说明有最小子串
     * s = "abcabcabc"
     * 1. n * x = 3 * 3 = 9; n:repetitions length. x: the smallest repeating unit length;
     * 2. LPS length: m = (n - 1) * x =6;
     * 3. n * x - m = 9 - 6 = 3;
     * n % (n - m) = 9 % (9 - 6) = 9 % 3 = 0;
     * @author benjieqiang
     * @date 2024/8/6 10:24 AM
     */
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] next = new int[n];
        getNext(next, s);
        if (next[n - 1] != 0 && n % (n - (next[n - 1])) == 0) {
            return true;
        }
        return false;
    }

    /**
     * @param next:
     * @param s:
     * @return void
     * @description 获取前缀表的模版；
     * @author benjieqiang
     * @date 2024/8/6 11:08 AM
     */
    private void getNext(int[] next, String s) {
        int j = 0;
        next[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
    }
    /**
     * @param s:
     * @return boolean
     * @description
     *举例：if s = abcabc
     * abcabc + abcabc = abc abcabc abc， middle string must have a s. so
     * 1. double string;
     * 2. from 1 to len - 1
     * 3. find s in doubleString.
     **
     * If \( s = "abcabc" \), then doubling the string gives us `"abcabcabcabc"`. After removing the first and last characters, we get `"bcabcabca"`. This middle string must contain \( s \) if \( s \) can be formed by repeating a substring.
     *
     * To check this, we follow these steps:
     *
     * 1. **Double the string**: Create a new string by concatenating \( s \) with itself.
     * 2. **Remove the first and last characters**: Extract the substring from index 1 to length \(- 1\) of the doubled string.
     * 3. **Check for \( s \) in the modified string**: If \( s \) is present in this modified string, it can be formed by repeating a substring. Otherwise, it cannot.
     *
     * ### Time Complexity
     *
     * The algorithm involves three main steps:
     *
     * 1. **Doubling the String**: This involves concatenating the string with itself, which takes linear time, \(O(n)\), where \(n\) is the length of the string.
     *
     * 2. **Creating a Substring**: We extract a substring from this doubled string, which also takes \(O(n)\) time.
     *
     * 3. **Checking for the Original String**: Using `contains` to check if the original string appears in this modified version takes \(O(n)\) time.
     *
     * Overall, each of these operations is linear with respect to the length of the string. So, the total time complexity is:
     *
     * \[ O(n) \]
     *
     * ### Space Complexity
     *
     * - We create a doubled version of the string, which requires \(O(n)\) space.
     * - We also create a substring of the doubled string, which requires another \(O(n)\) space.
     *
     * The total space complexity is therefore also linear:
     *
     * \[ O(n) \]
     *
     * ### Summary
     *
     * The algorithm efficiently checks if a string can be formed by repeating a substring with both time and space complexity of \(O(n)\), making it suitable for strings up to the given length constraint.
     * @author benjieqiang
     * @date 2024/8/6 9:59 AM
     */
    public boolean repeatedSubstringPattern2(String s) {
        if (s == null || s.length() < 2) return false;
        int n = s.length();
        // 倍增字符串并截掉首尾字符
        String doubledString = s + s;
        String substring = doubledString.substring(1, 2 * n - 1);

        // 检查原字符串是否存在于截掉后的倍增字符串中
        return substring.contains(s);
    }


    @Test
    public void test_repeatedSubstringPattern() {

        String str = "ababac";
        repeatedSubstringPattern(str);
    }
}