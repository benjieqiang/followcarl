package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-20  21:49
 * @Description: TODO
 * @Version: 1.0
 */
public class L5_131_patition {

    List<List<String>> res = new ArrayList<>();
    LinkedList<String> path = new LinkedList<>();

    private boolean isPalindrome(String s, int left, int right) {
        for (int i = left, j = right; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
    private void backtracking(String s, int startIndex) {
        if (startIndex >= s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isPalindrome(s, startIndex, i)) {
                path.add(s.substring(startIndex, i + 1));
            } else {
                continue;
            }
            backtracking(s, i + 1);
            path.removeLast();
        }
    }
    public List<List<String>> partition(String s) {
        backtracking(s, 0);
        return res;
    }

    @Test
    public void testPartition(){
        String s = "aab";
        List<List<String>> res = partition(s);
        System.out.println(res);
    }
}
