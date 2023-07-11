package com.ben.followcarl.c2_linkedlist;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-07  19:36
 * @Description: 反转链表
 * 思路：双指针：left最后指向头节点，right用来循环整个链表；
 * 首先定义一个null的空节点做left，right指向头节点；
 * 此时要交换left和right的节点，即让right的下一个元素指向left: right.next = left;
 *
 * 上面只交换了节点1和节点2位置的元素，题目要求的是交换所有元素，那就必须借助left和right两两交换完成。
 * 那么需要在更改指向之前先存一下right原来的指向 tmp = right.next;
 * 接着交换节点：right.next = left;
 * left和right都得前进一位；所以left = right; right = tmp;
 * @Version: 1.0
 */
public class L3_206_reverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode left = null;
        ListNode right = head;
        while (right != null) {
            ListNode tmp = right.next;
            right.next = left;
            left = right;
            right = tmp;
        }
        return left;
    }

    /**
     * @param head:
     * @return ListNode
     * @description
     * @author benjieqiang
     * @date 2023/7/11 5:05 PM
     */

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        return reverse(null, head);
    }

    /**
     * @param pre: 指向前一个元素，最终指向链表头
     * @param cur: 用来遍历整个链表
     * @return ListNode
     * @description 递归
     * 1、递归的返回值ListNode型和参数
     * 2、终止条件：如果当前节点为空，已经到链表的尾部，返回pre节点；
     * 3、单层递归的逻辑：记录当前节点的下一个位置，让当前节点指向他的前一个节点，
     * 继续递归时更改pre 和 cur都向后走一步；
     * @author benjieqiang
     * @date 2023/7/11 5:06 PM
     */
    private ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null) return pre;
        ListNode tmp = cur.next;
        cur.next = pre;
        return reverse(cur, tmp);
    }
}
