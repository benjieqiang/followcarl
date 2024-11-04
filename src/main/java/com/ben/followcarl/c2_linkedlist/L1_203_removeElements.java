package com.ben.followcarl.c2_linkedlist;

import org.junit.Test;

import static com.ben.followcarl.c2_linkedlist.ListUtils.createLinkedList;
import static com.ben.followcarl.c2_linkedlist.ListUtils.printListNode;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-07  17:14
 * @Description: 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * <p>
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

    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) return head;

        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    @Test
    public void testRemoveElements() {
        int val = 6;
        // 1,2,6,3,4,5,6
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode linkedList = createLinkedList(nums);
        printListNode(linkedList);
        removeElements(linkedList, val);
        System.out.println("");
        printListNode(linkedList);
    }


}
