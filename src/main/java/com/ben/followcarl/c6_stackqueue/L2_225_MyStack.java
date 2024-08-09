package com.ben.followcarl.c6_stackqueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;


public class L2_225_MyStack {
    public static void main(String[] args) {
        // Instantiate MyStack
//        MyStack myStack = new MyStack();
        MyStack2 myStack = new MyStack2();

        // Push 1
        // st = [],q1 = [], q2 = [];
        myStack.push(1);
        // st = [1] (top is 1),q1 = [1], q2 = [];
        myStack.push(2);
        // st = [1] (top is 1), st.push(2) => q2.add(2) => q2 = [2], q1 !empty q2.add(q1.remove) q2=[2,1], q1 = q2, q2=new list;
        // st = [2,1](top is 2), q1 = [2,1] (front is 2)
        // st.push(3) => st = [3,2,1], q2 = [3], q1=[2,1](front is 2), q2.add(q1.remove) => q2=[3,2,1](q2 add last and q1 remove first)
        // q1 = [3,2,1] (front is 3) q2 =[].
        myStack.push(3);
        // st.top => q1.getFirst() = 3;
        System.out.println("mystack top: " + myStack.top());
        // st.pop => st = [2,1] => q1 = [3,2,1](front is 3), q1.removeFirst();
        System.out.println("mystack pop: " + myStack.pop());
        myStack.push(5);
    }
}


class MyStack {
    Deque<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    // 进来一个元素就重新排列队列，比如原来是
    // 队首1 ，新加入元素2， 得到1-》2；
    // 此时队列排序成： 2-》1， 对应栈顶2-》1；返回栈顶元素getFirst(); 移除栈顶元素remove();
    public void push(int x) {
        queue.add(x);
        int size = queue.size();
        //移动除了 x 的其它数
        while (size-- > 1)
            queue.add(queue.remove());
    }

    public int pop() {
        return queue.remove();
    }

    public int top() {
        return queue.getFirst();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}


/**
 * @author benjieqiang
 * @description 一个队列用来存，一个用来备份；
 * @date 2023/10/11 8:15 PM
 */
class MyStack2 {
    Deque<Integer> queue1;
    Deque<Integer> queue2;

    public MyStack2() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    // 刚开始q1和q2都为空，入栈时，先往q2放，放完q2的元素赋值q1，清空q2；
    // 再来个元素入栈，q2队列先放，放完再把q1的元素往q2中一直加，直至q1为空；
    // 重复放完q2的元素赋值q1，清空q2
    // 这样保证q1中的元素顺序刚好和MyStack的顺序相反，能够直接调用peek把栈顶元素返回出来。
    // 比如栈1-》2
    // q1中顺序为2-》1，出队列的队首元素就是栈顶元素1。
    public void push(int x) {
        if (x < 1 || x > 9) return;
        queue2.add(x);
        while (!queue1.isEmpty()) {
            queue2.add(queue1.remove());
        }
        queue1 = queue2;
        queue2 = new LinkedList<>();
    }

    public int pop() {
        return queue1.remove();
    }

    public int top() {
        return queue1.getFirst();
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