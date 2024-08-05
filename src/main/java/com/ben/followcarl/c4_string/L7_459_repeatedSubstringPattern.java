package com.ben.followcarl.c4_string;

import org.junit.Test;

public class L7_459_repeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] next = new int[n];
        getNext(next, s);
        if (next[n - 1] != 0 && n % (n - (next[n - 1])) == 0) {
            return true;
        }
        return false;
    }

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

    @Test
    public void test_repeatedSubstringPattern() {

        String str = "ababac";
        repeatedSubstringPattern(str);
    }
}