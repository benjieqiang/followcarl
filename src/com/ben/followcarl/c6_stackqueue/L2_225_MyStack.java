package com.ben.followcarl.c6_stackqueue;

import java.util.LinkedList;
import java.util.Queue;

public class L2_225_MyStack {
}

class MyStack {
    Queue<Integer> queue1;
    Queue<Integer> queue2;
    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }
    // 刚开始q1和q2都为空，入栈时，先往q2放，放完q2的元素赋值q1，清空q2；
    // 再来个元素入栈，q2队列先放，放完再把q1的元素往q2中一直加，直至q1为空；
    // 重复放完q2的元素赋值q1，清空q2
    // 这样保证q1中的元素顺序刚好和MyStack的顺序相反，能够直接调用peek把栈顶元素返回出来。
    // 比如栈1-》2
    // q1中顺序为2-》1，出队列的队首元素就是栈顶元素。
    public void push(int x) {
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        queue1 = queue2;
        queue2 = new LinkedList<>();
    }

    public int pop() {
        return queue1.poll();
    }

    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */