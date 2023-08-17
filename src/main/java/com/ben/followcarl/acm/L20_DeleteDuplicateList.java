package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-16  17:59
 * @Description: 根据一个递增的整数序列构造有序单链表，删除其中的重复元素
 * @Version: 1.0
 */
import java.util.Scanner;

public class L20_DeleteDuplicateList {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            next = null;
        }
    }

    private static void printList(ListNode node) {
        ListNode cur = node;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }


    private static void deDuplicateList(ListNode head) {
        ListNode left = head;
        ListNode right = head.next;
        while (right != null) {
            if (left.val == right.val) {
                left.next = right.next;
                right = right.next;
            } else {
                left = left.next;
                right = right.next;
            }
        }
        printList(head);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n == 0) {
                System.out.println("list is empty");
                continue;
            }

            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;

            while (n-- > 0) {
                cur.next = new ListNode(sc.nextInt());
                cur = cur.next;
            }

            printList(dummy.next);
            deDuplicateList(dummy.next);
        }
    }

}
