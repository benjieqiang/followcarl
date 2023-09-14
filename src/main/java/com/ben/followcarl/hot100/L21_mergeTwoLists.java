package com.ben.followcarl.hot100;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-11  17:15
 * @Description: 合并两个链表,
 * 思路:
 * 首先需要一个哨兵节点, 然后用一个cur指向它, 利用while循环遍历两个链表, 比较list1的值和list2的值,那么就让cur下一步指向小的节点;
 * 并都向后前进一步;
 * 最后只要遇到一个list遍历完了,说明还剩一个链表的数
 * 在循环终止的时候， l1 和 l2 至多有一个是非空的。由于输入的两个链表都是有序的，
 * 所以不管哪个链表是非空的，它包含的所有元素都比前面已经合并链表中的所有元素都要大。
 * 这意味着我们只需要简单地将非空链表接在合并链表的后面，并返回合并链表即可
 * 时间复杂度：O(n+m)O(n + m)O(n+m)，其中 nnn 和 mmm 分别为两个链表的长度。因为每次循环迭代中，l1 和 l2 只有一个元素会被放进合并链表中， 因此 while 循环的次数不会超过两个链表的长度之和。所有其他操作的时间复杂度都是常数级别的，因此总的时间复杂度为 O(n+m)O(n+m)O(n+m)。
 *
 * 空间复杂度：O(1)O(1)O(1)。我们只需要常数的空间存放若干变量。
 *
 * 作者：力扣官方题解
 * 链接：https://leetcode.cn/problems/merge-two-sorted-lists/solutions/226408/he-bing-liang-ge-you-xu-lian-biao-by-leetcode-solu/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * list
 * @Version: 1.0
 */
public class L21_mergeTwoLists {
    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null && list2 == null) return list1;
            if (list1 == null && list2 != null) return list2;
            if (list1 != null && list2 == null) return list1;

            ListNode dummy = new ListNode(-1);
            ListNode cur = dummy;
            while (list1 != null && list2 != null) {
                if (list1.val <= list2.val) {
                    cur.next = list1;
                    list1 = list1.next;
                } else {
                    cur.next = list2;
                    list2 = list2.next;
                }
                cur = cur.next;
            }

            cur.next = list1 == null ? list2 : list1;
            return dummy.next;
        }
    }
}
