package com.ben.followcarl.c2_linkedlist;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-11  20:05
 * @Description: 链表相交的公共节点
 * O(m + n)，其中 m 和 n 是两个链表的长度。
 * O(1)
 * @Version: 1.0
 */
public class L6_160_getIntersectionNode {
    // A nodes count: a+c, B nodes code: b+c. c: the nodes count of intersection segment.
    // if intersect, a+c+b+c = b+c+a+c, the result is the intersection node;
    // if not，a + b = b+a, a,b will be null
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }

    @Test
    public void test_notIntersect() {
        // Create List A: 1 -> 2 -> 3 -> 4
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        // Create List B: 5 -> 6 -> 7
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        node5.next = node6;
        node6.next = node7;

        // Check for intersection
        ListNode intersection = getIntersectionNode(node1, node5);
        System.out.println(intersection);
    }
}
