package com.ben.followcarl.c6_stackqueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class L1_232_MyQueue {
    public static void main(String[] args) {
    }
}
class MyQueue {

    Stack<Integer> stIn;
    Stack<Integer> stOut;

    public MyQueue() {
        stIn = new Stack<>();
        stOut = new Stack<>();
    }

    public void push(int x) {
        stIn.push(x);
    }

    public int pop() {
        if (stOut.empty()) {
            // 如果out栈为空，则我们把in栈的数据压入
            while (!stIn.empty()) {
                stOut.push(stIn.pop());
            }
        }
        // stOut不为空的情况，则返回栈顶的元素
        return stOut.pop();
    }

    public int peek() {
        int res = pop();
        stOut.push(res);
        return res;
    }

    public boolean empty() {
        return stIn.empty() && stOut.empty();
    }
}
class MyQueue2 {
    Deque<Integer> stIn;
    Deque<Integer> stOut;

    public MyQueue2() {
        stIn = new LinkedList<>();
        stOut = new LinkedList<>();
    }

    public void push(int x) {
        stIn.addFirst(x);
    }

    public int pop() {
        if(stOut.isEmpty()) {
            while (!stIn.isEmpty()) {
                stOut.addFirst(stIn.removeFirst());
            }
        }
        return stOut.removeFirst();
    }

    public int peek() {
        int tmp = pop();
        stOut.addFirst(tmp);
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