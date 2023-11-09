package com.ben.followcarl.c4_string;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-10-07  21:37
 * @Description: 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * <p>
 * 示例 2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * <p>
 * 示例 3：
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * @Version: 1.0
 */
public class L4_151_reverseWords {
    class Solution {
        private StringBuilder removeSpaces(String s) {
            int left = 0;
            int right = s.length() - 1;
            while (s.charAt(left) == ' ') left++;
            while (s.charAt(right) == ' ') right--;

            StringBuilder sb = new StringBuilder();
            for (int i = left; i <= right; i++) {
                char ch = s.charAt(i);
                if (ch != ' ') {
                    sb.append(ch);
                    //从最左边出发，不为空就往sb里加， 如果发现字符为空,且sb的最后元素不是空，则往sb里add空格；
                    // 始终只能push一次空格，所以就去掉了空格；
                } else if (sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(ch);
                }
            }
            return sb;
        }

        public String reverseWords(String s) {
            if (s == null || s.length() == 0) return s;
            // 去除首尾空格，字符串中间多余空格
            StringBuilder sb = removeSpaces(s);
            // 翻转字符串
            reverse(sb, 0, sb.length() - 1);
            // 翻转每个单词
            reverseEachWord(sb);

            return sb.toString();
        }

        public void reverse(StringBuilder sb, int left, int right) {
            while (left < right) {
                char temp = sb.charAt(left);
                sb.setCharAt(left, sb.charAt(right));
                sb.setCharAt(right, temp);
                left++;
                right--;
            }
        }

        public void reverseEachWord(StringBuilder sb) {
            int length = sb.length();
            int start = 0;
            int end = 0;
            while (start < length) {
                // 找单词的末尾: 字符串内部：遇到空字符，end停下来，说明找到了;
                // 字符串末尾， end到达边界即停；
                while (end < length && sb.charAt(end) != ' ') {
                    end++;
                }
                // 翻转单词
                reverse(sb, start, end - 1);
                start = end + 1; //下一个单词
                end++;
            }
        }
    }

    class Solution2 {
        public String reverseWords(String s) {
            if (s == null || s.length() == 0) return s;
            StringBuilder sb = removeSpaces(s);
            reverse(sb, 0, sb.length() - 1);
            reverseEachWord(sb);
            return sb.toString();
        }

        private StringBuilder reverseEachWord(StringBuilder sb) {
            int left = 0;
            int end = 0;
            while (left < sb.length()) {
                while (end < sb.length() && sb.charAt(end) != ' ') {
                    end++;
                }
                reverse(sb, left, end - 1);
                left = end + 1;
                end++;
            }
            return sb;
        }

        private StringBuilder reverse(StringBuilder sb, int left, int right) {
            while (left < right) {
                char tmp = sb.charAt(left);
                sb.setCharAt(left, sb.charAt(right));
                sb.setCharAt(right, tmp);
                left++;
                right--;
            }

            return sb;
        }
        private StringBuilder removeSpaces(String s) {
            int left = 0;
            int right = s.length() - 1;
            StringBuilder sb = new StringBuilder();
            while (s.charAt(left) == ' ') left++;
            while (s.charAt(right) == ' ') right--;

            for (int i = left; i <= right; i++) {
                char ch = s.charAt(i);
                if (ch != ' ') {
                    sb.append(ch);
                } else if (sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(ch);
                }
            }
            return sb;
        }
    }
    @Test
    public void testReverse() {
        String s = "  hello  world  ";
        System.out.println(new Solution2().reverseWords(s));
    }

}


