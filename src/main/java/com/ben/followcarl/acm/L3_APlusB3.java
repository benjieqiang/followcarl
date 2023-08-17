package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-15  17:33
 * @Description:
 * 输入中每行是一对a和b。其中会有一对是0和0标志着输入结束，且这一对不要计算。
 * 对于输入的每对a和b，你需要在相应的行输出a、b的和。
 * 如第二对a和b，他们的和也输出在第二行。
 * 输入:
 * 2 4
 * 11 19
 * 0 0
 *
 * 0 0 表示结束,所以输出
 * 6
 * 30
 *
 * @Version: 1.0
 */
import java.util.Scanner;
public class L3_APlusB3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (a == 0 && b == 0) break;

            System.out.println(a + b);
        }
    }
}
