package com.ben.followcarl.leetcode;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-12-18  20:47
 * @Description:
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 *
 * @Version: 1.0
 */
public class L83_deleteDuplicates {
    /**
     * @param head:
     * @return ListNode
     * @description 思路，删除链表中的重复项，遍历链表，看下一个节点与当前节点值是否一样，一样则让当前的next指针指向下一个节点指向的next节点；
     * 不一样，则继续遍历指针。直到cur指向null跳出
     * @author benjieqiang
     * @date 2023/12/18 8:48 PM
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * @param head:
     * @return ListNode
     * @description 双指针法：
     * slow 指向新链表的下标；
     * fast 用来遍历链表，只要找到与slow不同的值，说明得把新节点放到slow的下一个节点处，slow同时得移动到fast的位置；继续下一次遍历
     * 如果值相同，只需要继续移动fast
     *
     * 遍历完成后，把slow后面的节点置空；
     * 返回head节点；
     * @author benjieqiang
     * @date 2023/12/18 8:58 PM
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head; // 新链表的节点
        ListNode slow = head; // 新链表的下标
        while (fast != null) {
            if (fast.val != slow.val) {
                // nums[slow] = nums[fast];
                slow.next = fast;
                // slow++;
                slow = slow.next;
            }
            // fast++
            fast = fast.next;
        }
        // 断开与后面重复元素的连接
        slow.next = null;
        return head;
    }

}
