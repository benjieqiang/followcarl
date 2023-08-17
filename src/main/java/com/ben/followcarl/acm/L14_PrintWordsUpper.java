package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-15  23:49
 * @Description: 输出一个词组中每个单词的首字母的大写组合。
 * 输入的第一行是一个整数n，表示一共有n组测试数据。（输入只有一个n，没有多组n的输入）
 * 1
 * ad dfa     fgs
 *
 * ADF
 * @Version: 1.0
 */
import java.util.Scanner;

public class L14_PrintWordsUpper {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            sc.nextLine(); // 吸收一个回车，因为输入n之后，要输入一个回车
            while (n-- > 0) {
                String line = sc.nextLine().trim();
                char[] arr = line.toCharArray();
                StringBuilder sb = new StringBuilder();
                int j = 0;
                while (j < arr.length) {
                    if (arr[j] <= 'z' && arr[j] >= 'a') {
                        arr[j] = Character.toUpperCase(arr[j]);
                    }
                    sb.append(arr[j]);
                    while (j < arr.length && arr[j] != ' ') {
                        j++;
                    }
                    while (j < arr.length && arr[j] == ' ') {
                        j++;
                    }
                }
                System.out.println(sb);
            }

        }
    }
}
