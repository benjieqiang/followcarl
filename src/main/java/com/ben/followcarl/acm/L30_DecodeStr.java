package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-17  14:59
 * @Description:
 * 题目描述
 * 给定一个被压缩过的字符串，压缩规则如下： 对于字符串中连续的m个相同字符串S将会压缩为[m|S](m为一个整数且0<=m<=100)，例如字符串ABCABCABC将会被压缩为[3|ABC]。
 *
 * 你的任务是解压被压缩过的字符串。
 *
 * "HG[3|B[2|CA]]F"
 *
 * "HGBCACABCACABCACAF"
 *
 * 1. 字符串转int: Integer.parseInt("0")
 * 2. str.substring(0, len) 不包括len;
 * 3. sb.toString();
 * @Version: 1.0
 */

import java.util.Scanner;

public class L30_DecodeStr {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        System.out.println(decode(line));
    }

    private static String decode(String str) {
        int x = -1, y = -1, z = -1;
        // 遍历分别找到[,|,] 出现的位置,
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '[') {
                x = i;
            } else if (str.charAt(i) == '|') {
                y = i;
            } else if (str.charAt(i) == ']') {
                z = i;
                break;
            }
        }
        if (x != -1 && y != -1 && z != -1) {
            int count = Integer.parseInt(str.substring(x + 1, y)); // [2|CA]
            String sub = str.substring(y + 1, z);
            StringBuilder sb = new StringBuilder();
            while (count-- > 0) {
                sb.append(sub);
            }
            // [0,x)的字符串 + 解压缩后的字符串 + ]后面的字符串;
            String decodeStr = str.substring(0, x) + sb.toString() + str.substring(z + 1);
            return decode(decodeStr);
        }

        return str;
    }
}
