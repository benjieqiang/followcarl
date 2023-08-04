package com.ben.followcarl.c6_stackqueue;
import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

class EvalRPNTest {
    // 接受一个键盘输入一个字符串数组；
    // String str=scanner.nextLine(); //得到输入字符串
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = new String[5];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = scanner.next();
        }
        System.out.println("strings = " + Arrays.toString(strings));

        System.out.println(new L5_150_EvalRPN().evalRPN(strings));
    }
}
/**
 * @description 逆波兰表达式：
 * 卡壳：
 * 1. 使用equals比较字符串
 * 2. 字符串转int Integer.valueOf(str);
 * @author benjieqiang
 * @date 2023/7/26 10:33 PM
 */
public class L5_150_EvalRPN {
    public int evalRPN(String[] tokens) {
        Deque<Integer> st = new LinkedList<>();
        for (int i = 0; i < tokens.length; i++) { //也可以用增强for循环，判断时需要用equals，-和/需要特殊处理。
            String str = tokens[i];
            if (str.equals("+")) {
                st.addFirst(st.removeFirst() + st.removeFirst());
            } else if (str.equals("-")) {
                st.addFirst(-st.removeFirst() + st.removeFirst());
            } else if (str.equals("*")) {
                st.addFirst(st.removeFirst() * st.removeFirst());
            } else if (str.equals("/")) {
                int tmp1 = st.removeFirst();
                int tmp2 = st.removeFirst();
                st.addFirst(tmp2 / tmp1);
            } else {
                st.addFirst(Integer.valueOf(str));
            }
        }

        return st.removeFirst();
    }

    public int evalRPN2(String[] tokens) {
        Deque<Integer> st = new LinkedList<>();
        for (String str : tokens) {
            if (str.equals("+")) {
                st.push(st.pop() + st.pop());
            } else if (str.equals("*")) {
                st.push(st.pop() * st.pop());
            } else if (str.equals("-")) {
                Integer a = st.pop();
                Integer b = st.pop();
                st.push(b - a);
            } else if (str.equals("/")) {
                Integer a = st.pop();
                Integer b = st.pop();
                st.push(b/a);
            } else {
                st.push(Integer.valueOf(str));
            }
        }
        return st.pop();
    }

    @Test
    public void testEvalRpn() {
        String[] str = {"2","1","+","3","*"};
        System.out.println(evalRPN2(str));
    }
}