package com.ben.followcarl.c6_stackqueue;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-10-11  19:42
 * @Description: stack和queue测试
 * @Version: 1.0
 */
public class L0_StackAndQueue {
    /**
     * @param :
     * @return void
     * @description stack: 栈顶-》栈底；
     * push, pop, peek
     * @author benjieqiang
     * @date 2023/10/11 8:23 PM
     */
    @Test
    public void testStack() {
        Deque<String> st = new LinkedList<>();
        st.push("baidu");
        st.push("ali");
        st.push("meituan");

        System.out.println(st); //[meituan, ali, baidu]

        System.out.println(st.peek());
        System.out.println("测试pop");
        System.out.println(st.pop()); //meituan
        System.out.println(st); //[ali, baidu]
        System.out.println(st.isEmpty());
    }

    /**
     * @param :
     * @return void
     * @description queue: 首-》尾
     * add/addLast, remove/removeFirst, getFirst
     * [1,2,3,4] front is 1, tail is 4
     * @author benjieqiang
     * @date 2023/10/11 8:22 PM
     */
    @Test
    public void testQueue() {
        Deque<String> queue = new LinkedList<>();
        queue.add("1");
        System.out.println(queue);
        queue.add("2");
        System.out.println(queue);
        queue.add("3");
        System.out.println(queue);
        System.out.println("测试addLast");
        queue.addLast("4");
        System.out.println(queue);

        System.out.println("获取队列第一个元素");
        System.out.println(queue.getFirst());
        System.out.println("测试remove");
        String str = queue.remove();
        System.out.println(queue);

        System.out.println("测试remove");
        queue.remove();
        System.out.println(queue);
        /*
            [1]
            [1, 2]
            [1, 2, 3]
            测试addLast
            [1, 2, 3, 4]
            获取队列第一个元素
            1
            测试remove
            [2, 3, 4]
            测试remove
            [3, 4]
        * */
    }
}
