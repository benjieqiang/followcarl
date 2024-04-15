package com.ben.followcarl.c6_stackqueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class L1_232_MyQueue {
    public static void main(String[] args) {
    }
}
class MyQueue {
    Deque<Integer> stIn;
    Deque<Integer> stOut;

    public MyQueue() {
        stIn = new LinkedList<>();
        stOut = new LinkedList<>();
    }

    public void push(int x) {
        if (x < 0 || x > 10) return;
        stIn.push(x);
    }

    public int pop() {
        // 如果out栈为空，则我们把in栈的数据压入out栈中。栈顶元素就是队首元素。
        if(stOut.isEmpty()) {
            while (!stIn.isEmpty()) {
                stOut.push(stIn.pop());
            }
        }
        // stOut不为空的情况，或者in栈清空，这样能让第一个元素放在栈顶，完成fifo。则返回栈顶的元素
        return stOut.pop();

//        if (!st2.isEmpty()) return st2.pop();
//        while (!st1.isEmpty()) {
//            st2.push(st1.pop());
//        }
//
//        return st2.pop();
    }

    public int peek() {
        int tmp = pop();
        stOut.push(tmp);
        return tmp;
    }

    public boolean empty() {
        return stIn.isEmpty() && stOut.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */