package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-08-05  15:00
 * @Description: 题目描述
 * 字符串的右旋转操作是把字符串尾部的若干个字符转移到字符串的前面。给定一个字符串 s 和一个正整数 k，请编写一个函数，将字符串中的后面 k 个字符移到字符串的前面，实现字符串的右旋转操作。
 *
 * 例如，对于输入字符串 "abcdefg" 和整数 2，函数应该将其转换为 "fgabcde"。
 *
 * 输入描述
 * 输入共包含两行，第一行为一个正整数 k，代表右旋转的位数。第二行为字符串 s，代表需要旋转的字符串。
 * 输出描述
 * 输出共一行，为进行了右旋转操作后的字符串。
 * 输入示例
 * 2
 * abcdefg
 * 输出示例
 * fgabcde
 * @Version: 1.0
 */

import java.util.Scanner;

public class L55_reverseStr {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int target = sc.nextInt();
        String str = sc.next();
        String res = reverse(str, target);
        System.out.println(res);
    }

    private static String reverse(String str, int target) {
        if (str == null || str.length() == 0) return str;
        char[] chars = str.toCharArray();
        int length = chars.length;
        reverse(chars, 0, length - 1);
        reverse(chars, 0, target - 1);
        reverse(chars, target, length - 1);

        return new String(chars);
    }

    private static void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
    }
}

