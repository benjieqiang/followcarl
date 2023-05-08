package com.ben.followcarl.c6_stackqueue;
import java.util.Deque;
import java.util.LinkedList;

class EvalRPNTest {
    public static void main(String[] args) {
        String[] str = {"2","1","+","3","*"};
        System.out.println(new L5_150_EvalRPN().evalRPN(str));
    }
}
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
}