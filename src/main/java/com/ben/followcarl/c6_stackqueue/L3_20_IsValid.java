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
 * 2. 左括号和右括号一样多，括号类型匹配不上"({[)})"，则最后栈还不是空的，
 * 3. 右括号多，比如"([{}])))"，最后发现栈都空了，字符串还没完，说明右括号多了，
 *
 * 定义栈：Deque<Character> st = new LinkedList<>();
 * push pop peek
 * 队列：
 * Deque<Integer> queue = new LinkedList<>();
 * addFirst removeLast getFirst
 * add/remove/get

 * </>
 * * @author benjieqiang
 * @date 2023/7/26 9:40 PM
 */
public class L3_20_IsValid {

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) return false; //剪枝，肯定为偶数；一一对应
        Deque<Character> st = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                st.push(')');
            } else if (ch == '[') {
                st.push(']');
            } else if (ch == '{') {
                st.push('}');
                // 3. 右括号多：经过上面的if条件，有左括号存在，栈一定不为空，如果栈为空那么说明遇到右括号，栈空的情况，比如")]","]", "(){}}{"
                // 2. 括号数目一样多，但是类型不一致，即栈顶元素不等于当前遍历的字符；
            } else if (st.isEmpty() || st.peek() != ch) { // isEmpty()放到peek前面；
                return false;
            } else {
                st.pop();
            }
            // 要么直接st.pop() != ch弹出栈顶元素，不需要else分支
        }
        // 1. false: 左括号多，遍历完了，栈不为空，说明没有相匹配数目的右括号匹配左括号;true: 完全匹配；
        return st.isEmpty();
    }

    public boolean isValid2(String s) {
        // 1. () 2. ]}}] 3. (]{)
        if (s == null || s.length() == 0) return false;
        Deque<Character> stack = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') stack.push(')');
            else if (ch == '[') stack.push(']');
            else if (ch == '{') stack.push('}');
            else if (stack.isEmpty() || stack.pop() != ch) return false;
        }
        return stack.isEmpty();
    }

    @Test
    public void testIsValid() {
//        String s = "()[]{}";
        String s = "()";
        System.out.println(isValid(s));
    }
}