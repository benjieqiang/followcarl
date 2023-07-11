package com.ben.followcarl.c2_linkedlist;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-11  20:05
 * @Description: 链表相交的公共节点
 * @Version: 1.0
 */
public class L6_160_getIntersectionNode {
    //若相交，链表A： a+c, 链表B : b+c. a+c+b+c = b+c+a+c 。则会在公共处c起点相遇。若不相交，a +b = b+a 。因此相遇处是NULL
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }
}
