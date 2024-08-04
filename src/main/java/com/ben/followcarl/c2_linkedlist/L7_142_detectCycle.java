package com.ben.followcarl.c2_linkedlist;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-11  20:08
 * @Description: 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 起始节点 -》环形节点入口 -》相遇节点 -》环形节点入口,距离分别是x, y, z
 * slow 走一步，fast走两步，fast走过的距离是slow走过的距离的2倍；
 * slow指针走的距离：x + y
 * fast 指针走的距离： x + y + n(y+z)
 * 所以： (x + y) * 2 = x + y + n (y + z)
 * x = (n - 1) (y + z) + z
 * n = 1,则说明，fast走了一圈就和slow指针相遇了，且入口节点就是相遇节点
 * x = z；说明从起始节点和相遇节点，两个指针同时走，相遇则是入口节点；
 * @Version: 1.0
 */
public class L7_142_detectCycle {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next; // 走两步
            slow = slow.next; // 走一步

            if (fast == slow) { // 相遇时，从头节点和相遇节点同时出发，再次相遇就是所求；
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

    public ListNode detectCycle2(ListNode head) {
        if (head == null) return null;

        ListNode slow = head;  // Slow pointer starts at the head
        ListNode fast = head;  // Fast pointer starts at the head

        // Phase 1: Detect if a cycle exists
        while (fast != null && fast.next != null) {
            fast = fast.next.next;  // Move fast pointer two steps
            slow = slow.next;       // Move slow pointer one step

            // Check if the two pointers meet
            if (fast == slow) {
                // Phase 2: Find the start of the cycle
                ListNode index1 = head; // Start from the head
                ListNode index2 = fast; // Start from the meeting point

                // Move both pointers one step at a time until they meet
                while (index1 != index2) {
                    index1 = index1.next; // Move index1 one step
                    index2 = index2.next; // Move index2 one step
                }

                // Return the starting node of the cycle
                return index1;
            }
        }

        // No cycle detected
        return null;
    }
}
