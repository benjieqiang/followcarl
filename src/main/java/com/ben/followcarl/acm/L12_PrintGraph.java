package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-15  23:20
 * @Description: TODO： 先要求你从键盘输入一个整数n（1<=n<=9），打印出指定的数字图形。
 * 输入包含多组测试数据。每组输入一个整数n（1<=n<=9）。
 * 输入:5
 * 输出:
 *     1
 *    121
 *   12321
 *  1234321
 * 123454321
 *  1234321
 *   12321
 *    121
 *     1
 * @Version: 1.0
 */

import java.util.Scanner;
public class L12_PrintGraph {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            int n = in.nextInt();
            if (n < 1 || n > 9) System.out.println("输入的n超过范围");

            for (int i = 1; i <= n; i++) {
                print(n - i, i);
            }

            for (int i = n - 1; i > 0; i--) {
                print(n - i, i);
            }
        }
    }
    public static void print(int blank, int n) {
        //  前面需要补齐空格
        for (int i = 0; i < blank; i++) {
            System.out.print(" ");
        }
        for (int i = 1; i <= n; i++) {
            System.out.print(i);
        }
        for (int i = n - 1; i > 0; i--) {
            System.out.print(i);
        }
        System.out.println();
    }
}
