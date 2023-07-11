package com.ben.followcarl.c2_linkedlist;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-11  20:08
 * @Description: 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 起始节点 -》环形节点入口 -》相遇节点 -》环形节点入口,距离分别是x, y, z
 * 则slow指针走的距离：x + y
 * fast 指针走的距离： x + y + n(y+z)
 * (x + y) * 2 = x + y + n (y + z)
 * x = (n - 1) (y + z) + z
 * n = 1,则说明，fast走了一圈就和slow指针相遇了，且入口节点就是相遇节点
 * x = z；
 * @Version: 1.0
 */
public class L7_142_detectCycle {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                ListNode index1 = head;
                ListNode index2 = fast;
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }
}
