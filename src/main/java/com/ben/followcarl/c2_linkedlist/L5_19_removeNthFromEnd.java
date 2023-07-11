package com.ben.followcarl.c2_linkedlist;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-11  17:38
 * @Description: TODO
 * @Version: 1.0
 */
public class L5_19_removeNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;
        ListNode cur = head;
        int len = 0;
        // 求链表总长度
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        // 借助dummy来删元素
        // 1. n大于size，那直接返回head；
        // 2. [1], n = 1的情况，此时直接删除dumy指向的下一个元素，即头节点；
        // 3. 正常删除，先把cur2移动到前一个元素的位置，再继续删；
        ListNode dummyHead = new ListNode(0, head);
        ListNode cur2 = dummyHead;
        for (int i = 1; i < len - n + 1; i++) {
            cur2 = cur2.next;
        }
        cur2.next = cur2.next.next;

        return dummyHead.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) return head;
        ListNode dummyHead = new ListNode(0, head);
        ListNode left = dummyHead;
        ListNode right = dummyHead;
        // right 走n+1步， 为了让left指向待删节点的前一个节点
        while (n-- > 0) {
            right = right.next;
        }
        right = right.next;

        // 让left去删
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return dummyHead.next; // [1,2]， n = 2。则需要删除1，所以是dummyHead.next;
    }

    @Test
    public void testRemove() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

//        removeNthFromEnd2(node1, 2);
//        removeNthFromEnd2(new ListNode(1), 1);

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        removeNthFromEnd2(n1, 2);
    }

}
