package com.ben.followcarl.c2_linkedlist;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-07  17:15
 * @Description: 单链表的定义
 * @Version: 1.0
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
