package com.ben.followcarl.c2_linkedlist;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-07  19:36
 * @Description: TODO
 * @Version: 1.0
 */
public class L3_206_reverseList {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}
