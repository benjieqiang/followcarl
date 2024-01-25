package com.ben.followcarl.c2_linkedlist;

import org.junit.Test;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-07  17:14
 * @Description: 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 *
 * 使用一个虚拟头节点，从虚拟头节点位置开始遍历，
 * 如果cur的next是目标节点，则让cur.next指向目标节点的next节点；
 * @Version: 1.0
 */
public class L1_203_removeElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy; // 指向虚拟头节点，假设第一个元素就是要删的元素，则直接把cur.next指向他的下一个节点cur.next.next
        // 从虚拟头节点的下一个节点开始遍历，也是头节点开始遍历；
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }

    @Test
    public void testRemoveElements() {
        int val = 6;
        // 1,2,6,3,4,5,6
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        printListNode(node1);


        removeElements(node1, val);
        System.out.println("");
        printListNode(node1);
    }

    private void printListNode(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

}
