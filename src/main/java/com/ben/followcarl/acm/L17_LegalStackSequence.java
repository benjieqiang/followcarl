package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-16  16:08
 * @Description: 已知自然数1，2，...，N（1<=N<=100）依次入栈，请问序列C1，C2，...，CN是否为合法的出栈序列。
 * 输入:
 * 5 // 第一组表示下面有5个整数
 * 3 4 2 1 5
 * 5 // 第二组表示下面有5个整数
 * 3 5 1 4 2
 * 0 // 结束
 * 输出:
 * Yes //序列是合法的出栈序列
 * No //不是
 * @Version: 1.0
 */

import java.util.Scanner;
import java.util.Stack;

public class L17_LegalStackSequence {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n == 0) break;
            int[] nums = new int[n];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = sc.nextInt();
            }

            if (check(nums)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }

        }
        sc.close();
    }

    private static boolean isValid(int[] nums) {
        int n = nums.length;
        int[] st = new int[n];

        int top = -1; // 栈顶指针
        int index = 1; // 要压入栈的下一个数字
        for (int i = 0; i < n; i++) {
            while (top == -1 || st[top] < nums[i]) {
                st[++top] = index++;
            }
            // 如果栈顶元素不等于当前数组元素, 不是合法栈
            if (st[top] != nums[i]) return false;
            top--; // 出栈
        }
        return true;
    }

    private static boolean check(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int n = nums.length;
        int i = 0;
        int index = 1; // 要入栈的元素
        while (i < n) {
            if (!st.isEmpty() && st.peek() == nums[i]) {
                // 如果栈不为空且栈顶元素等于当前数列元素，弹出栈顶元素并移动到数列的下一个元素
                st.pop();
                i++;
            } else if (index <= n) {
                // 如果还有数字可以压入栈中
                st.push(index);
                index++;
            } else {
                return false;
            }
        }
        return true;
    }

}
