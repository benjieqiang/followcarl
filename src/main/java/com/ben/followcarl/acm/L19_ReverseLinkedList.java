package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-16  17:23
 * @Description: 根据一个整数序列构造一个单链表，然后将其反转。
 * <p>
 * 例如：原单链表为 2 3 4 5 ，反转之后为5 4 3 2
 * 输入包括多组测试数据，每组测试数据占一行，第一个为大于等于0的整数n，表示该单链表的长度，后面跟着n个整数，表示链表的每一个元素。整数之间用空格隔开
 * <p>
 * 5 1 2 3 4 5
 * 0
 * 输出:
 * 针对每组测试数据，输出包括两行，分别是反转前和反转后的链表元素，用空格隔开
 * 如果链表为空，则只输出一行，list is empty
 * 1 2 3 4 5
 * 5 4 3 2 1
 * list is empty
 * @Version: 1.0
 */


import java.util.Scanner;

public class L19_ReverseLinkedList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            next = null;
        }
    }

    private static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    private static void reverseList(ListNode head) {
        ListNode left = null;
        ListNode right = head;
        while (right != null) {
            ListNode tmp = right.next;
            right.next = left;
            left = right;
            right = tmp;
        }

        printList(left);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n == 0) {
                System.out.println("list is empty");
            }
            ListNode dummy = new ListNode(0); //虚拟头节点
            ListNode cur = dummy;
            while (n-- > 0) {
                ListNode tmp = new ListNode(sc.nextInt());
                cur.next = tmp;
                cur = cur.next;
            }
            printList(dummy.next);
            reverseList(dummy.next);
        }

    }

}
