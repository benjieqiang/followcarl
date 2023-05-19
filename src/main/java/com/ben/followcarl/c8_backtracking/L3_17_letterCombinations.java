package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-18  23:11
 * @Description: TODO
 * @Version: 1.0
 */
public class L3_17_letterCombinations {

    List<String> res = new ArrayList<>(); // 存放字符串结果的集合
    String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}; // 建立数字和字符串的映射
    StringBuilder sb = new StringBuilder();
    /**
     * @param digits: 题目给出的digits字符串 比如，digits = "23"
     * @param index: 用来标识字符串中第几个数字
     * @return void
     * @description TODO
     * @author benjieqiang
     * @date 2023/5/19 12:50 AM
     */
    public void backtracking(String digits, int index) {
        if (index == sb.length()) {
            res.add(sb.toString());
            return;
        }
        // 根据index来确定"23"中第几个数字；
        int num = digits.charAt(index) - '0'; // 转为数字；
        // 从映射中找对应的字符串；
        String letters = numString[num];
        // 首先sb里存了a，然后继续回溯，传入的应该是index的下一位即index+1位置的字符串letters = "def",
        for(int i = 0; i < letters.length(); i++) {
            sb.append(letters.charAt(i));
            backtracking(digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return res;
        backtracking(digits, 0);
        return res;
    }
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()) {
//            String digits = scanner.next();
//            List<String> res = new L3_17_letterCombinations().letterCombinations(digits);
//            System.out.println(res);
//        }
//    }

    @Test
    public void testLetterCombination() {
        String digits = "23";
        List<String> res = letterCombinations(digits);
        System.out.println(res);
    }
}
