package com.ben.followcarl.c3_hashmap;

import org.junit.Test;

import java.util.*;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-11  21:07
 * @Description:
 * 给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-all-anagrams-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Version: 1.0
 */
public class L2_438_findAnagrams {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        char[] arrP = p.toCharArray();
        Arrays.sort(arrP);
        p = new String(arrP);
        int last = p.length();
        for (int i = 0; i <= s.length()- last; i++) {
            String tmp = s.substring(i, i + last);
            char[] arr = tmp.toCharArray();
            Arrays.sort(arr);
            tmp = new String(arr);
            if (tmp.equals(p)) {
                res.add(i);
            }
        }
        return res;
    }


    @Test
    public void tesFind() {
        String s = "cbaebabacd", p = "abc";
        System.out.println(findAnagrams(s, p));
    }
}
