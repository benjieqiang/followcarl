package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-16  16:42
 * @Description: 本题考察链表的基本操作。温馨提示：本题较为复杂，需要细心多花一些时间。
 * <p>
 * 3 3 2 1 // 第一个3表示这行有3个数字, 输入3,2,1 输出的链表应该为1 -》 2 -》 3
 * 21 // 表示下面还有21行
 * show
 * delete 1
 * show
 * delete 2
 * show
 * delete 1
 * show
 * delete 2
 * insert 2 5
 * show
 * insert 1 5
 * show
 * insert 1 7
 * show
 * insert 2 5
 * show
 * insert 3 6
 * show
 * insert 1 8
 * show
 * get 2
 * <p>
 * 如果是“get”，代表获得第a个元素。（a从1开始计数）
 * 如果是“delete”，代表删除第a个元素。（a从1开始计数）
 * 如果是“insert”，则其后跟着两个整数a和e，代表在第a个位置前面插入e。（a从1开始计数）
 * “show”之后没有正数，直接打印链表全部内容。
 * <p>
 * 输出:
 * 1 2 3 // show刚开始创建的链表;
 * delete OK
 * 2 3
 * delete OK
 * 2
 * delete OK
 * Link list is empty
 * delete fail
 * insert fail
 * Link list is empty
 * insert OK
 * 5
 * insert OK
 * 7 5
 * insert OK
 * 7 5 5
 * insert OK
 * 7 5 6 5
 * insert OK
 * 8 7 5 6 5
 * 7
 * @Version: 1.0
 */

import java.util.Scanner;

public class L18_LinkedList {
    /* 链表节点 */
    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    /* 链表 */
    static class LinkedList {
        private Node head;
        private int size;

        public LinkedList() {
            this.head = null;
            this.size = 0;
        }

        /* 头插法 */
        public void addFirst(int val) {
            Node node = new Node(val);
            node.next = head;
            head = node;
            size++;
        }

        /* 获取链表元素 */
        public int get(int idx) {
            int cnt = 0;
            Node curr = head;
            while (curr != null) {
                if (cnt == idx) {
                    return curr.val;
                }
                curr = curr.next;
                cnt++;
            }
            return -1;
        }

        /* 删除链表节点 */
        public boolean delete(int idx) {
            if (head == null || idx < 0 || idx >= size)
                return false;

            if (idx == 0) {
                head = head.next;
            } else {
                int cnt = 0;
                Node curr = head;
                while (cnt < idx - 1) { // 移动到待删节点的前一个位置
                    curr = curr.next;
                    cnt++;
                }
                curr.next = curr.next.next; // 待删节点的前一个位置指向删除节点的位置;
            }
            size--;
            return true;
        }

        /* 插入元素 */
        public boolean insert(int idx, int val) {
            if (idx < 0 || idx > size)
                return false;

            if (idx == 0) {
                addFirst(val);
                return true;
            }

            int cnt = 0;
            Node curr = head;
            while (curr != null && cnt < idx - 1) {
                curr = curr.next;
                cnt++;
            }
            if (curr == null)
                return false;

            Node node = new Node(val);
            node.next = curr.next;
            curr.next = node;
            size++;
            return true;
        }

        /* 输出链表 */
        public void show() {
            if (head == null) {
                System.out.println("Link list is empty");
                return;
            }
            Node curr = head;
            while (curr != null) {
                System.out.print(curr.val + " ");
                curr = curr.next;
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList linkedList = new LinkedList();

        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            linkedList.addFirst(num);
        }

        int m = sc.nextInt();
        while(m-- > 0) {
            String operation = sc.next();
            if ("get".equals(operation)) {
                int a = sc.nextInt();
                int result = linkedList.get(a - 1);
                if (result != -1) {
                    System.out.println(result);
                } else {
                    System.out.println("get fail");
                }
            } else if ("delete".equals(operation)) {
                int a = sc.nextInt();
                boolean deleteResult = linkedList.delete(a - 1);
                if (deleteResult) {
                    System.out.println("delete OK");
                } else {
                    System.out.println("delete fail");
                }
            } else if ("insert".equals(operation)) {
                int a = sc.nextInt();
                int e = sc.nextInt();
                boolean insertResult = linkedList.insert(a - 1, e);
                if (insertResult) {
                    System.out.println("insert OK");
                } else {
                    System.out.println("insert fail");
                }
            } else if ("show".equals(operation)) {
                linkedList.show();
            }
        }
        sc.close();
    }

}
