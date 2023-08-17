package com.ben.followcarl.acm;

/**
 * @description 计算a+b，但输入方式有所改变。
 * 第一行是一个整数N，表示后面会有N行a和b，通过空格隔开。
 *
 * 对于输入的每对a和b，你需要在相应的行输出a、b的和。
 * 如第二对a和b，对应的和也输出在第二行。
 *
 * @author benjieqiang
 * @date 2023/8/15 5:27 PM
 */
import java.util.Scanner;

public class L2_APlusB2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) { // 注意 while 处理多个 case
            int n = scanner.nextInt();
            while (n-- > 0) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                System.out.println(a + b);
            }
        }
    }
}