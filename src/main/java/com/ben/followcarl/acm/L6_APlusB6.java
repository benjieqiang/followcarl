package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-15  17:47
 * @Description: 你的任务是计算若干整数的和。
 * 输入:
 * 3 // 表示有三行;
 * 4 1 2 3 4 // 4表示这行有4个整数
 * 5 1 2 3 4 5
 * 3 1 2 3
 * 输出:
 * 10
 *
 * 15
 *
 * 6  // 最后一行没有空格
 * @Version: 1.0
 */
import java.util.Scanner;
public class L6_APlusB6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            while (n-- > 0) {
                int m = scanner.nextInt();
                int sum = 0;
                while (m-- > 0) {
                    sum += scanner.nextInt();
                }
                System.out.println(sum);
                if (n > 0) System.out.println();
            }
        }
    }
}
