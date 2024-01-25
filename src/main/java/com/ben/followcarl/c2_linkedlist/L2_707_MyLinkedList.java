package com.ben.followcarl.c2_linkedlist;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-07  19:35
 * @Description: 设计链表
 * get和deleteAtIndex不能越界；
 * addAtIndex时，可以从<0或等于size的位置加上节点；
 *
 * @Version: 1.0
 */
public class L2_707_MyLinkedList {
    static class MyLinkedList {
        class ListNode {
            int val;
            ListNode next;

            ListNode(int val) {
                this.val = val;
            }

            ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }
        }

        // 链表长度，链表的虚拟头节点；
        private int size;
        private ListNode dummyHead;

        public MyLinkedList() {
            this.size = 0;
            this.dummyHead = new ListNode(0);
        }

        // index从0开始，先判断范围[0, size-1]. 从真实头节点开始遍历，找到对应的index位置。返回val
        public int get(int index) {
            if (index >= size || index < 0) return -1;
            ListNode cur = dummyHead.next;
            while (index-- > 0) {
                cur = cur.next;
            }

            return cur.val;
        }


        // 虚拟头节点的下一个节点指向新节点，新节点的下一个节点指向原来虚拟头节点指向的节点。
        public void addAtHead(int val) {
            ListNode node = new ListNode(val, dummyHead.next);
            dummyHead.next = node;
            size++;
        }

        // 末尾加节点，先遍历整个链表到最后一个节点，从dummyHead开始遍历让他指向新节点。
        public void addAtTail(int val) {
            ListNode node = new ListNode(val);
            ListNode cur = dummyHead;
            while (cur.next != null) { // 如果cur.next为空，说明cur是最后一个节点
                cur = cur.next;
            }
            cur.next = node;
            size++;
        }

        // 首先判断范围，[0,size], index=size表示在末尾加, index < 0表示在头加；
        // 遍历到index-1的位置，把node加上去；
        public void addAtIndex(int index, int val) {
            if (index > size) return;
            if (index < 0) index = 0;
            ListNode node = new ListNode(val);
            ListNode cur = dummyHead;
            // 遍历到index-1的节点
            while (index-- > 0) {
                cur = cur.next;
            }
            node.next = cur.next;
            cur.next = node;
            size++;
        }

        // index 范围： [0,size)
        // 遍历到index-1的节点，指向下一个节点；
        public void deleteAtIndex(int index) {
            if (index >= size || index < 0) return;
            ListNode cur = dummyHead;
            while (index-- > 0) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            size--;
        }
    }

    /*
    ["MyLinkedList","addAtHead","deleteAtIndex","addAtHead","addAtHead","addAtHead",
    "addAtHead","addAtHead","addAtTail","get","deleteAtIndex","deleteAtIndex"]

     [[],[2],[1],[2],[7],[3],[2],[5],[5],[5],[6],[4]]

     */

    private static void test1() {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(2);
        list.deleteAtIndex(1);
        list.addAtHead(1);
        list.addAtHead(2);
        list.addAtHead(7);
        list.addAtHead(3);
        list.addAtHead(2);
        list.addAtTail(5);
        list.get(5);
        list.deleteAtIndex(6);
        list.deleteAtIndex(4);

        System.out.println(list);
    }

    private static void test2() {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(2);
        list.addAtHead(1);
        list.addAtHead(2);
        list.addAtIndex(3, 20);
        System.out.println(list.size);
        System.out.println(list.get(3));
    }
    public static void main(String[] args) {
//        test1();
        test2();
    }
}
