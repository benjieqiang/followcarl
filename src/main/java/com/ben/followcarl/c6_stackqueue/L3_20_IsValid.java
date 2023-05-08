package com.ben.followcarl.c6_stackqueue;

import java.util.Deque;
import java.util.LinkedList;

class L3_20_IsValidTest {
    public static void main(String[] args) {
        new L3_20_IsValid().isValid("(){}}{");
    }
}
public class L3_20_IsValid {
    public boolean isValid(String s) {
        Deque<Character> st = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            System.out.println(st.size());
            char ch = s.charAt(i);
            // 遇到左括号一律往栈里面放对应右括号
            if (ch == '(') {
                st.addFirst(')');
            } else if (ch == '{') {
                st.addFirst('}');
            } else if (ch == '[') {
                st.addFirst(']');
            } else if (st.isEmpty() || st.peekFirst() != ch) {
                //经过上面的if条件，有左括号存在，栈一定不为空，如果为空那么说明遇到右括号，栈空的情况，比如"]", "(){}}{"
                return false;
            } else {
                // 右括号匹配到了
                st.removeFirst();
            }
        }
        return st.isEmpty();
    }
}