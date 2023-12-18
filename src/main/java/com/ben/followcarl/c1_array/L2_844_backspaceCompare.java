package com.ben.followcarl.c1_array;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-05  19:52
 * @Description:
 * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。
 *
 * 注意：如果对空文本输入退格字符，文本继续为空。
 *
 * 示例 1：
 *
 * 输入：s = "ab#c", t = "ad#c"
 * 输出：true
 * 解释：s 和 t 都会变成 "ac"。
 * @Version: 1.0
 */
public class L2_844_backspaceCompare {
    public boolean backspaceCompare(String s, String t) {
        String sb = getString2(s);
        String st = getString2(t);
        return sb.equals(st);
    }

    /**
     * @param s:
     * @return String
     * @description 遍历字符串，遇到#则后退一步，把sb的末尾元素删了。
     * @author benjieqiang
     * @date 2023/12/16 8:22 PM
     */
    private static String getString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '#') {
                sb.append(s.charAt(i));
            } else {
                // 只有sb长度大于0,才能删除末尾元素, 比如来了一个字符串"#a#b",sb长度还是0.
                if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
            }
        }
        return sb.toString();
    }

    /** 双指针法
     * 思路：
     * slow指针指向新字符串的下标；
     * fast指针用来寻找新字符串，即遇到#后退一步，slow
     * */

    private static String getString2(String s) {
        char[] x = s.toCharArray();
        int slow = 0;
        for (int fast = 0; fast < x.length; fast++) {
            if (x[fast] != '#') {
                x[slow++] = x[fast];
            } else if (x[fast] == '#' && slow != 0)
                slow--;
        }

        return String.valueOf(x, 0, slow);
    }
    @Test
    public void testCompare() {
//        String s = "ab#c";
//        String t = "ad#c";
//        String s = "ab##";
//        String t = "a#d#";
        String s = "a##c";
        String t = "#a#c";
        System.out.println(backspaceCompare(s, t));
    }
}
