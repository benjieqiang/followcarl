package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-16  15:59
 * @Description: 给定一个长度为偶数位的字符串，请编程实现字符串的奇偶位互换。
 * 输入;
 * 2 //2组数据
 * 0aa0
 * bb00
 *
 * 输出:
 * a00a
 * bb00
 *
 * @Version: 1.0
 */
import java.util.Scanner;
public class L16_ChangePosition {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            sc.nextLine();

            while (n-- > 0) {
                String str = sc.nextLine();
                char[] array = str.toCharArray();
                for (int i = 1; i < array.length; i += 2) {
                    char tmp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = tmp;
                }
                System.out.println(new String(array));
            }
        }
    }
}
