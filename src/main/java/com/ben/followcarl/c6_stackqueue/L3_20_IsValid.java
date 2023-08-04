package com.ben.followcarl.c6_stackqueue;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @description 思路：
 * 遇到左括号，就往栈里方右括号，
 * 遇到右括号和栈顶元素比较，如果不等，就返回false
 * 分三种情况：
 * 1. 左括号多，"([{}]()"如果遍历完了，栈不为空，则左括号多了；
 * 2. 括号类型匹配不上"({[)})"，则最后栈还不是空的，
 * 3. 右括号多余，比如"([{}])))"，最后发现栈都空了，字符串还没完，说明右括号多了，
 *
 * 定义栈：Deque<Character> st = new LinkedList<>();
 * push pop peek
 * 队列：
 * Deque<Integer> queue = new LinkedList<>();
 * addFirst removeLast getFirst
 * </>
 * * @author benjieqiang
 * @date 2023/7/26 9:40 PM
 */
public class L3_20_IsValid {
    public boolean isValid(String s) {
        Deque<Character> st = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            System.out.println(st.size());
            char ch = s.charAt(i);
            // 1. 遇到左括号一律往栈里面放对应右括号
            if (ch == '(') {
                st.push(')');
            } else if (ch == '{') {
                st.addFirst('}');
            } else if (ch == '[') {
                st.addFirst(']');
            } else if (st.isEmpty() || st.peekFirst() != ch) {
                // 2. 经过上面的if条件，有左括号存在，栈一定不为空，如果为空那么说明遇到右括号，栈空的情况，比如")]","]", "(){}}{"
                return false;
            } else {
                // 右括号匹配到了
                st.removeFirst();
            }
        }
        return st.isEmpty();
    }

    public boolean isValid2(String s) {
        if (s.length() % 2 != 0) return false; //剪枝，肯定为偶数；一一对应
        Deque<Character> st = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                st.push(')');
            } else if (ch == '[') {
                st.push(']');
            } else if (ch == '{') {
                st.push('}');
            } else if (st.isEmpty() || st.peek() != ch) {
                return false;
            } else {
                st.pop();
            }
        }

        return st.isEmpty();
    }

    @Test
    public void testIsValid() {
//        String s = "()[]{}";
        String s = "(]";
        System.out.println(isValid2(s));
    }
}