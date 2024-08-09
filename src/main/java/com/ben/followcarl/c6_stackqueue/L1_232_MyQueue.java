package com.ben.followcarl.c6_stackqueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class L1_232_MyQueue {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();

        // Test pushing elements into the queue
        System.out.println("Pushing elements 1, 2, 3 into the queue.");
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);

        // queue = [1,2,3,4], st1 = [4,3,2,1](top is 4)
        System.out.println("Peeking front element (should be 1): " + queue.peek());
        // peek: queue = [1,2,3,4], st1 = [], st2 = [1,2,3,4](top is 1), queue.peek() = st2.pop() = 1; st2.push(1)
        // now: queue = [1,2,3,4],st1 = [], st2=[1,2,3,4](top is 1)
        // queue.pop => st2.pop() = 1 => st2 = [2,3,4](top is 2),queue = [2,3,4]
        System.out.println("Popping front element (should be 1): " + queue.pop());
        // pop: queue = [2,3,4], queue.pop => st2.pop() = 2 => st2 = [3,4](top is 3), queue = [3,4]
        System.out.println("Popping front element (should be 2): " + queue.pop());
        //queue = [3,4], st2 = [3,4](top is 3), queue.push(5) => st1.push(5) => queue = [5,3,4],st1 = [5], st2 = [3,4](top is 3)
        System.out.println("Pushing element 5 into the queue.");
        queue.push(5);

        //queue = [3,4,5],st1 = [5], st2 = [3,4](top is 3)
        System.out.println("Popping front element (should be 3): " + queue.pop());
        System.out.println("Popping front element (should be 4): " + queue.pop());


        // Test if the queue is empty
        System.out.println("Is the queue empty? (should be true): " + queue.empty());
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
        if (x < 0 || x > 9) return;
        stIn.push(x);
    }

    public int pop() {
        // stOut不为空的情况，或者in栈清空，这样能让第一个元素放在栈顶，完成fifo。则返回栈顶的元素
        if (!stOut.isEmpty()) return stOut.pop();
        // 如果out栈为空，则我们把in栈的数据压入out栈中。栈顶元素就是队首元素。
        while (!stIn.isEmpty()) {
            stOut.push(stIn.pop());
        }

        return stOut.pop();
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