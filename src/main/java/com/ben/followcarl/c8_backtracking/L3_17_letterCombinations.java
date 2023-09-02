package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-18  23:11
 * @Description:
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 1. 难点: 1. 确定树的宽度是》每个数字,比如2对应的值是“abc", 那它的宽度是“abc".length();
 *      树的高度是递归的层级, 从0开始到digits.length()结束;
 *      2. 单层遍历的逻辑:
 *          2.1. 先根据index取digits中去取数字, 得把"2"转成数字2, 所以是“2” - ’0‘;
 *          2.2. 拿到2之后得用map数组找到2对应的字符串: “abc"
 *          2.3. “abc"的宽度就作为树的宽度
 *          2.4. 遍历树, 先把结果集加入到sb中, 下一层递归时, 需要用index+1来控制访问的是digits的下一个字符“3”,
 *       3. 递归结束条件: 如果index加到了digits的length长度. 则收获结果;
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
     * @description 这里和组合问题其实一样, 树形结构就是 23 -》取2 =》剩下3 -》取3;
     * index用来控制for循环的下一层从i+1的位置开始, 比如把2取了, 就不能再取2了,
     * 这里绕了一下, 取到2之后还得转成数字,再去map表中找对应的字符串, 单层递归遍历的是该字符串, 从0开始去依次匹配;
     * @author benjieqiang
     *
     * 时间复杂度: O(3^m * 4^n)，其中 m 是对应四个字母的数字个数，n 是对应三个字母的数字个数
     * 空间复杂度: O(m+n)除了返回值以外，空间复杂度主要取决于哈希表以及回溯过程中的递归调用层数，哈希表的大小与输入无关，
     * 可以看成常数，递归调用层数最大为 m+n。
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

    @Test
    public void testLetterCombination() {
        String digits = "23";
        List<String> res = letterCombinations(digits);
        System.out.println(res);
    }
}
