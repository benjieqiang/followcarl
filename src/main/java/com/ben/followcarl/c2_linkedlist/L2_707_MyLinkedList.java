package com.ben.followcarl.c2_linkedlist;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-07  19:35
 * @Description: 设计链表
 * get和deleteAtIndex不能越界；
 * addAtIndex时，可以从<0或等于size的位置加上节点；
 * @Version: 1.0
 */
public class L2_707_MyLinkedList {
    class MyLinkedList {
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
            // 真实头节点开始遍历，找cur位置；
            while (index-- > 0) {
                cur = cur.next;
            }

            return cur.val;
        }


        //新节点的下一个节点指向原来虚拟头节点指向的头节点。 虚拟头节点的下一个节点指向新节点，
        public void addAtHead(int val) {
            ListNode node = new ListNode(val, dummyHead.next);
            dummyHead.next = node;
            size++;
        }

        // 末尾加节点，先遍历整个链表到最后一个节点，从dummyHead开始遍历让他指向新节点。
        public void addAtTail(int val) {
            ListNode node = new ListNode(val);
            ListNode cur = dummyHead;
            // Iterate up to size steps to reach the last node
            for (int i = 0; i < size; i++) {
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
            ListNode node = new ListNode(val); // 新节点
            // 遍历到index-1的节点，所以从dummyHead出发开始遍历的；
            ListNode cur = dummyHead;
            while (index-- > 0) {
                cur = cur.next;
            }
            // 先后再前
            node.next = cur.next;
            cur.next = node;
            size++;
        }

        // index 范围： [0,size)
        // 遍历到index-1的节点，指向下一个节点；
        public void deleteAtIndex(int index) {
            if (index >= size || index < 0) return;
            ListNode cur = dummyHead; // 从dummyhead开始遍历
            while (index-- > 0) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            size--;
        }

        public int get2(int index) {
            if (index <  0 || index >= size) return -1;
            ListNode cur = dummyHead.next;
            while(index-- > 0) cur = cur.next;
            return cur.val;
        }

        public void addAtHead2(int val) {
            ListNode node = new ListNode(val, dummyHead.next);
            dummyHead.next = node;
            size++;
        }

        public void addAtTail2(int val) {
            ListNode node = new ListNode(val);
            int len = size;
            ListNode cur = dummyHead;
            while(len-- > 0) cur = cur.next;
            cur.next = node;
            size++;
        }

        public void addAtIndex2(int index, int val) {
            if (index < 0) return;
            // 也可以不写
//            if (index == 0)  {
//                // 必须返回，不然继续会执行下面，多添加元素；
//                addAtHead(val);
//                return;
//            }
//            if (index == size)  {
//                addAtTail(val);
//                return;
//            }
            if (index > size) return;
            ListNode node = new ListNode(val);
            ListNode cur = dummyHead;
            while (index-- > 0) cur = cur.next;
            node.next = cur.next;
            cur.next = node;
            size++;
        }

        public void deleteAtIndex2(int index) {
            if (index < 0 || index >= size) return;
            ListNode cur = dummyHead;
            while(index-- > 0) cur = cur.next;
            cur.next = cur.next.next;
            size--;
        }
    }

    /*
    ["MyLinkedList","addAtHead","deleteAtIndex","addAtHead","addAtHead","addAtHead",
    "addAtHead","addAtHead","addAtTail","get","deleteAtIndex","deleteAtIndex"]

     [[],[2],[1],[2],[7],[3],[2],[5],[5],[5],[6],[4]]

     */

    @Test
    public void test1() {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(2);
        printListNode(list.dummyHead);
        list.deleteAtIndex(1);
        printListNode(list.dummyHead);
        list.addAtHead(1);
        list.addAtHead(2);
        list.addAtHead(7);
        list.addAtHead(3);
        list.addAtHead(2);
        printListNode(list.dummyHead);

        list.addAtTail(5);
        printListNode(list.dummyHead);

        list.get(5);
        list.deleteAtIndex(6);
        printListNode(list.dummyHead);

        list.deleteAtIndex(4);
        printListNode(list.dummyHead);
    }

    /**
     * 0 2
     * <p>
     * 0 2
     * <p>
     * 0 2 3 7 2 1 2
     * <p>
     * 0 2 3 7 2 1 5
     * <p>
     * 0 2 3 7 2 1 5
     * <p>
     * 0 2 3 7 2 1 5
     * <p>
     * <p>
     * <p>
     * 0 2
     * <p>
     * 0 2
     * <p>
     * 0 2 3 7 2 1 2
     * <p>
     * 0 2 3 7 2 1 2 5
     * <p>
     * 0 2 3 7 2 1 2
     * <p>
     * 0 2 3 7 2 2
     */
    @Test
    public void test2() {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(2);
        list.addAtHead(1);
        list.addAtHead(2);
        list.addAtIndex(3, 20);
        System.out.println(list.size);
        System.out.println(list.get(3));
    }

    @Test
    public void test3() {
        // "MyLinkedList", "addAtHead", "addAtHead", "addAtHead", "addAtIndex", "deleteAtIndex", "addAtHead", "addAtTail", "get", "addAtHead", "addAtIndex", "addAtHead"]
        // [[],[7],[2],[1],[3, 0],[2],[6],[4],[4],[4],[5, 0],[6]]
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(7);
        list.addAtHead(2);
        list.addAtHead(1);
        printListNode(list.dummyHead);
        list.addAtIndex(3, 0);
        printListNode(list.dummyHead);
        list.deleteAtIndex(2);
        printListNode(list.dummyHead);
        list.addAtHead(6);
        list.addAtTail(4);
        System.out.println(list.get(4));
        printListNode(list.dummyHead);
        list.addAtHead(4);
        list.addAtIndex(5, 0);
        list.addAtHead(6);
        printListNode(list.dummyHead);
        /*
        0 1 2 7

        0 1 2 7 0

        0 1 2 0

        4
        0 6 1 2 0 4

        0 6 4 6 1 2 0 0 4


        0 1 2 7

        0 1 2 7 0 0

        0 1 2 0 0

        0
        0 6 1 2 0 0 4

        0 6 4 6 1 2 0 0 0 4
         */
    }

    @Test
    public void test4() {
        // "MyLinkedList", "addAtHead", "addAtHead", "addAtHead", "addAtIndex", "deleteAtIndex", "addAtHead", "addAtTail", "get", "addAtHead", "addAtIndex", "addAtHead"]
        // [[],[7],[2],[1],[3, 0],[2],[6],[4],[4],[4],[5, 0],[6]]
        MyLinkedList list = new MyLinkedList();
        list.addAtHead2(7);
        list.addAtHead2(2);
        list.addAtHead2(1);
        printListNode(list.dummyHead);
        list.addAtIndex2(3, 0);
        printListNode(list.dummyHead);
        list.deleteAtIndex2(2);
        printListNode(list.dummyHead);
        list.addAtHead2(6);
        list.addAtTail2(4);
        System.out.println(list.get2(4));
        printListNode(list.dummyHead);
        list.addAtHead2(4);
        list.addAtIndex2(5, 0);
        list.addAtHead2(6);
        printListNode(list.dummyHead);
    }

    private void printListNode(MyLinkedList.ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println("\n");
    }
}
