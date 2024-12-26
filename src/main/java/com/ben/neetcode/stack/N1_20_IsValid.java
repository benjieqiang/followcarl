package com.ben.neetcode.stack;

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
public class N1_20_IsValid {

    public boolean isValid(String s) {
        // 1. () 2. ]}}] 3. (]{)
        if (s == null || s.length() == 0) return false;
        if (s.length() % 2 != 0) return false;
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