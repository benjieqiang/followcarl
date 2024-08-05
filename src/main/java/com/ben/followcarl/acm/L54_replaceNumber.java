package com.ben.followcarl.acm;


/**
 * @Author: benjieqiang
 * @CreateTime: 2024-08-04  21:55
 * @Description: 54.
 * 题目描述
 * 给定一个字符串 s，它包含小写字母和数字字符，请编写一个函数，将字符串中的字母字符保持不变，而将每个数字字符替换为number。 例如，对于输入字符串 "a1b2c3"，函数应该将其转换为 "anumberbnumbercnumber"。
 * 输入描述
 * 输入一个字符串 s,s 仅包含小写字母和数字字符。
 * 输出描述
 * 打印一个新的字符串，其中每个数字字符都被替换为了number
 * 输入示例
 * a1b2c3
 * 输出示例
 * anumberbnumbercnumber
 * 提示信息
 * 数据范围：
 * 1 <= s.length < 10000。
 * @Version: 1.0
 */

import org.junit.Test;

import java.util.Scanner;

public class L54_replaceNumber {

    // 不用双指针的话，从前往后遍历
    // 两个注意点：1. Scanner的用法要熟；2. ch 也是可以比较大小的；
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String str = sc.next();
            String res = replaceNumber(str);
            System.out.println(res);
        }
    }

    private static String replaceNumber(String str) {
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (ch <= '9' && ch >= '0') {
                sb.append("number");
            } else {
                sb.append(ch);
            }

        }
        return sb.toString();
    }

    class Solution {
        private String replaceNumber(String str) {
            int length = str.length();
            StringBuilder sb = new StringBuilder();
            for (char ch : str.toCharArray()) {
                if (ch >= '0' && ch <= '9') sb.append("     ");
            }
            // 说明str中没有数字，直接返回
            if (sb.length() == 0) return str;

            // 扩容str至新数组的长度，记录新旧数组长度，
            int left = length - 1;
            str += sb.toString();
            int right = str.length() - 1;
            // 从后往前遍历，遇到数字，则倒序填充number字符到新数组，填充也有技巧，从后往前填充，填完right-1；
            char[] nums = str.toCharArray();
            while (left >= 0) {
                if (nums[left] >= '0' && nums[left] <= '9') {
                    nums[right--] = 'r';
                    nums[right--] = 'e';
                    nums[right--] = 'b';
                    nums[right--] = 'm';
                    nums[right--] = 'u';
                    nums[right] = 'n';
                } else {
                    nums[right] = nums[left];
                }
                left--;
                right--;
            }

            return new String(nums);
        }


        /**
         * @param str:
         * @return String
         * @description 不使用sb也可以，遍历先拿到数字的个数，新建一个char数组，长度是愿数组长度+count*5；5是number字符串的长度-原来数字占1个字符；
         * 之后从后往前遍历，遇到数字：先填充r，right-1，直至n；不遇到数字，直接覆盖：right和left同时后退一步；最后返回new String(nums);
         * @author benjieqiang
         * @date 2024/8/4 10:31 PM
         */
        private  String replaceNumber3(String str) {
            if (str == null || str.length() == 0) return str;

            int dotCount = 0;
            for (char ch : str.toCharArray()) {
                if (ch >= '0' && ch <= '9') {
                    dotCount++;
                }
            }

            if (dotCount == 0) return str;

            int newLength = str.length() + dotCount * 5;
            char[] chars = new char[newLength];

            int left = str.length() - 1;
            int right = newLength - 1;

            while (left >= 0) {
                if (str.charAt(left) >= '0' && str.charAt(left)  <= '9') {
                    chars[right--] = 'r';
                    chars[right--] = 'e';
                    chars[right--] = 'b';
                    chars[right--] = 'm';
                    chars[right--] = 'u';
                    chars[right] = 'n';
                } else {
                    chars[right] = str.charAt(left);
                }
                left--;
                right--;
            }

            return new String(chars);
        }

    }

    @Test
    public void test_NoDoublePointer() {
        String str = "a1b2c3";
        System.out.println(new Solution().replaceNumber("a1b2c3"));
    }
}
