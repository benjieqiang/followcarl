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

        System.out.println(new L5_150_EvalRPN().evalRPN2(strings));
    }
}
/**
 * @description 逆波兰表达式：
 * 卡壳：
 * 1. 使用equals比较字符串
 * 2. 字符串转int Integer.valueOf(str);
 * 3. stack中存的数据类型是Integer类型；
 *
 * 1. 定义栈用来存储遍历过程中遇到的数字，所以得是Integer类型，
 * 2. 遍历字符串，equals比较字符串，遇到+，-，*，/则进行弹出两个数，运算完的值入栈；弹出两个数，注意顺序，+和*无所谓，-和/需要使用第二次弹出的数去操作第一个数；
 * 3. 否则入栈操作；
 * 4. 栈顶元素就是最终值；
 * @author benjieqiang
 * @date 2023/7/26 10:33 PM
 */
public class L5_150_EvalRPN {
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

    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        // stack
        Deque<Integer> stack = new LinkedList<>();
        // +,-,*,/
        for (String str : tokens) {
            if (str.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (str.equals("-")) {
                stack.push(-stack.pop() + stack.pop());
            } else if (str.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (str.equals("/")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 / num1);
            } else {
                stack.push(Integer.valueOf(str));
            }
        }
        // stack.pop()
        return stack.pop();
    }

    @Test
    public void testEvalRpn() {
        String[] str = {"2","1","+","3","*"};
        System.out.println(evalRPN2(str));
    }
}