package com.ben.neetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-26  17:44
 * @Description: TODO
 * @Version: 1.0
 */
public class N2_155_minStack {
    // O(1), O(N)栈空间
    class MinStack {
        private Deque<Integer> stack;
        private Deque<Integer> minStack; // 辅助栈用来记录最小元素
        public MinStack() {
            stack = new LinkedList<>();
            minStack = new LinkedList<>();
        }

        public void push(int val) {
            stack.push(val);
            // 刚开始minStack是空的，所以同步加入val，之后同步加入最小值
            if (minStack.isEmpty() || minStack.peek() >= val) {
                minStack.push(val);
            }
        }

        public void pop() {
            int val = stack.pop();
            // 同步删除相同的栈顶元素；
            if (val == minStack.peek()) minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    class MinStack2 {
        private Deque<int[]> st;
        public MinStack2() {
            st = new LinkedList<>();
            // 哨兵节点，0，最大值。
            st.push(new int[]{0, Integer.MAX_VALUE});
        }

        public void push(int val) {
            // push时，数组第一位放正常顺序的栈元素，第二位放最小的栈顶元素，和原来的栈顶元素比
            st.push(new int[]{val, Math.min(st.peek()[1], val)});
        }

        public void pop() {
            st.pop();
        }

        public int top() {
            return st.peek()[0];
        }

        public int getMin() {
            return st.peek()[1];
        }
    }
}
