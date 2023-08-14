package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-18  12:11
 * @Description: 输入包括两个正整数a,b(1 <= a, b <= 1000),输入数据包括多组。
 * 输出a+b的结果
 * @Version: 1.0
 */


import java.util.Scanner;
public class APlusB {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) { // while处理多个case
            // 采用hasNextXxxx() 的话，后面也要用nextXxxx():
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }
}
