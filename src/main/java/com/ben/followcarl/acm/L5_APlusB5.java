package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-15  17:44
 * @Description:
 * 输入包含若干行，每行输入两个整数a和b，由空格分隔。
 * 对于每组输入，输出a和b的和，每行输出后接一个空行。
 * 输入:
 * 2 4
 * 11 19
 *
 * 输出:
 * 6
 *
 * 30
 *
 * @Version: 1.0
 */
import java.util.Scanner;
public class L5_APlusB5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            System.out.println(a + b);
            System.out.println();
        }
    }
}
