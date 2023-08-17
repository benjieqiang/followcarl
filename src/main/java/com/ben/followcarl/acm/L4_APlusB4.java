package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-15  17:38
 * @Description: 计算每行的整数;
 * 每行的第一个数N，表示本行后面有N个数。
 * 如果N=0时，表示输入结束，且这一行不要计算。
 *
 * 输入:
 * 4 1 2 3 4
 * 5 1 2 3 4 5
 * 0
 *
 * 输出:
 * 10
 * 15
 * @Version: 1.0
 */
import java.util.Scanner;
public class L4_APlusB4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            if (n == 0) break;
            int sum = 0;
            while (n-- > 0) {
                sum += scanner.nextInt();
            }
            System.out.println(sum);
        }
    }
}
