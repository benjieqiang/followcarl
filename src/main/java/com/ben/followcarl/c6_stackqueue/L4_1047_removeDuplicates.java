package com.ben.followcarl.c6_stackqueue;

import org.junit.Test;

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
        System.out.println("当前st：" + st); //当前st：[a, c]，栈顶元素是a
        while (!st.isEmpty()) {
            res = st.removeFirst() + res; // c + a
        }
        return res;
    }

    public String removeDuplicates3(String s) {
        Deque<Character> st = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            if (st.isEmpty() || st.peek() != ch) {
                st.push(ch);
            } else {
                st.pop();
            }
        }
        // 栈是[a,c]，栈顶元素是a，所以先把a输出，再输出时c+a;
        String res = "";
        while(!st.isEmpty()) {
            res = st.pop() + res;
        }

        return res;
    }

    /**
     * @param s:
     * @return String
     * @description 不使用栈，使用sb字符串的性质
     * top指向新字符串的下标；
     * 如果栈顶的元素和当前遍历的字符不同，直接append入sb
     * 如果相同，弹出
     *
     * sb.append()
     * sb.deleteCharAt(index)
     * sb.charAt(index)
     * @author benjieqiang
     * @date 2023/7/26 10:04 PM
     */
    public String removeDuplicates2(String s) {
        StringBuilder sb = new StringBuilder();
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

    @Test
    public void testRemoveDuplicates() {

        System.out.println(removeDuplicates("abbaca"));
    }
}

