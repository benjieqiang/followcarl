package com.ben.followcarl.c4_string;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-10-07  21:31
 * @Description:
 * 假定一段路径记作字符串 path，其中以 "." 作为分隔符。现需将路径加密，加密方法为将 path 中的分隔符替换为空格 " "，请返回加密后的字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：path = "a.aef.qerf.bb"
 *
 * 输出："a aef qerf bb"
 *
 * 借助stringbuilder替换分隔符；
 *
 * 方法2： 双指针
 * 如果要把.替换成%20,则使用双指针
 * @Version: 1.0
 */
public class L3_lcr122_pathEncryption {
    class Solution {
        public String pathEncryption(String path) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.length(); i++) {
                char ch = path.charAt(i);
                if (ch == '.') {
                    sb.append(' ');
                } else {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }
    }

    class Solution2 {
        /**
         * @param path:
         * @return String
         * @description 双指针解法：
         * 1. 遍历原字符串得到需扩容多个字符；往原字符串末尾加上多少个空格；
         * 2. 双指针，把字符串转成字符数组进行赋值；left指向原字符串的末尾，right指向新字符串的末尾；
         * 3. 从右向左遍历直至left==0, 遇到.则改成02%的顺序赋值，
         * @author benjieqiang
         * @date 2023/10/24 5:39 PM
         */
        public String pathEncryption(String path) {
            if (path == null || path.length() == 0) return path;
            StringBuilder sb = new StringBuilder();
            // 新字符串比原字符串多2*点数的字符；
            for (char ch : path.toCharArray()) {
                if (ch == '.') sb.append("  ");
            }
            // sb为空，说明无.，直接返回
            if (sb.length() == 0) return path;
            // 扩容path成目标字符串字符数目；
            int left = path.length() - 1; // 原字符串末尾
            path += sb.toString();

            int right = path.length() - 1; // 新字符串末尾
            char[] chars = path.toCharArray();

            // 从右向左进行遍历字符串
            while (left >= 0) {
                if (chars[left] == '.') {
                    chars[right--] = '0';
                    chars[right--] = '2';
                    chars[right] = '%';
                } else {
                    chars[right] = chars[left];
                }
                left--;
                right--;
            }

            return new String(chars);
        }


    }

    @Test
    public void testPathEncryption() {
        String path = "a.aef.qerf.bb";
        System.out.println(new Solution2().pathEncryption(path));
    }
}
