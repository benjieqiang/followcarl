package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-15  21:20
 * @Description: 有一天, 小明收到一张奇怪的信, 信上要小明计算出给定数各个位上数字为偶数的和。
 * 例如：5548，结果为12，等于 4 + 8 。
 * 小明很苦恼，想请你帮忙解决这个问题。
 * 输入:
 * 输入数据有多组。每组占一行，只有一个整整数，保证数字在32位整型范围内。
 * 输出:
 * 对于每组输入数据，输出一行，每组数据下方有一个空行。
 *
 * @Version: 1.0
 */
import java.util.Scanner;

public class L9_StrangeLetter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int num = in.nextInt();
            int res = 0;
            while (num > 0) {
                int tmp = num % 10;
                if (tmp % 2 == 0) res += tmp;
                num /= 10;
            }
            System.out.println(res);
            System.out.println();
        }
    }
}
