package com.ben.followcarl.acm;
import java.util.Scanner;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-18  12:31
 * @Description: 输入第一行包括一个数据组数t(1 < = t < = 100)
 * 接下来每行包括两个正整数a,b(1 <= a, b <= 1000)
 * 1<=t <=100, 1<=a,b <=1000
 * @Version: 1.0
 */
public class AplusB2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }
}