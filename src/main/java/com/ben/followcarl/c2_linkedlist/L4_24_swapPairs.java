package com.ben.followcarl.c2_linkedlist;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-11  17:09
 * @Description: 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * 这道题需要画图去理解；
 *
 * @Version: 1.0
 */
public class L4_24_swapPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            ListNode node1 = cur.next; //记录cur的下一个元素
            ListNode node2 = cur.next.next; //记录cur的下下一个元素
            // 交换工作
            cur.next = node2; // cur指向node2；
            node1.next = node2.next; // 提前把node1的节点和node2的下一节点串起来，
            node2.next = node1;
            cur = node1;
        }
        return dummy.next;
    }

    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyHead = new ListNode(0, head);
        ListNode cur = dummyHead; // 引入dummyHead,这样头节点不用单独处理；cur始终指向slow节点的前一个节点；
        while (cur.next != null && cur.next.next != null) {
            ListNode slow = cur.next;
            ListNode fast = cur.next.next;
            cur.next = fast; // 先让cur指向第二个节点；
            slow.next = fast.next; // 断开slow和fast的连接，即让slow的下一个节点指向第三个节点；
            fast.next = slow; // fast的下一个节点指向slow节点；
            cur = slow; // slow此时处于第二个节点的位置。cur -> fast -> slow -> 第三个节点；
        }

        return dummyHead.next;
    }
    public ListNode swapPairs4(ListNode head) {
        // Base case: if the list is empty or has only one node, return head
        if (head == null || head.next == null) {
            return head;
        }

        // Nodes to be swapped
        ListNode firstNode = head;
        ListNode secondNode = head.next;

        // Recursively swap the remaining pairs
        firstNode.next = swapPairs4(secondNode.next);

        // Swap the first two nodes
        secondNode.next = firstNode;

        // Return the new head of the swapped pair
        return secondNode;
    }


    @Test
    public void testSwapPairs() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListUtils.printListNode(swapPairs2(node1));
        ListUtils.printListNode(swapPairs4(node1));
    }
}
