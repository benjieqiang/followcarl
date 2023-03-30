package com.ben.followcarl.c6_stackqueue;

import java.util.Deque;
import java.util.LinkedList;

public class L4_1047_removeDuplicates {
    public String removeDuplicates(String s) {
        Deque<Character> st = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (st.isEmpty() || st.peekFirst() != ch) {
                // 栈为空
                st.addFirst(ch);
            } else {
                st.removeFirst();
            }
        }
        String res = "";
        System.out.println("当前st：" +st); //当前st：[a, c]，栈顶元素是c
        while (!st.isEmpty()) {
            res = st.removeFirst() + res; // 输出的时候需要倒序往字符串加。
        }
        return res;
    }

    public String removeDuplicates2(String s) {
        StringBuffer sb = new StringBuffer();
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (sb.length() == 0 || sb.charAt(top) != ch) {
                sb.append(ch);
                top++;
            } else {
                sb.deleteCharAt(top);
                top--;
            }
        }
        return sb.toString();
    }
}
class RemoveDuplicatesTest {
    public static void main(String[] args) {
        L4_1047_removeDuplicates duplicates = new L4_1047_removeDuplicates();
        System.out.println(duplicates.removeDuplicates2("abbaca"));
    }
}