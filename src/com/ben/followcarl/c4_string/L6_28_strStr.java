package com.ben.followcarl.c4_string;

public class L6_28_strStr {
        public int strStr(String haystack, String needle) {
            int n = haystack.length();
            int m = needle.length();
            if (n < m || m == 0) return -1;
            int[] next = new int[m]; // next数组用来存放模式串的前缀表，也就是每个字符串对应的最长相等前后缀。
            getNext(next, needle); // 获取模式串的前缀表

            int j = 0;
            for (int i = 0; i < n; i++) {
                //i从0开始遍历文本串，j从0到m进行遍历，如果i和j字符不等，那就j往前跳；
                while (j > 0 && needle.charAt(j) != haystack.charAt(i))
                    j = next[j - 1];
                if (needle.charAt(j) == haystack.charAt(i))
                    //i和j对应的字符一样，i和j同时向后加，直到要么到模式串的长度，
                    // 要么找到一个不等的字符，那么就需要执行上面while的逻辑
                    j++;
                if (j == m)
                    return i - m + 1;
            }
            return -1;
        }

        private void getNext(int[] next, String s) {
            //1. 初始化，i指向后缀末尾，j指向前缀末尾
            int j = 0; //第一个字符串的最长相等前后缀肯定为0，原因看最长相等前后缀的定义。
            next[0] = 0;
            //2. 循环遍历该模式字符串，来更新next[i]的值，next[i]表示的就是i之前的字符串的最长相等前后缀；
            for (int i = 1; i < s.length(); i++) {
                //3. 当前后缀不同的时候，此时j根据前一位字符所对应的next[j-1]的值来决定会退几步。
                while (j > 0 && s.charAt(j) != s.charAt(i)) {
                    j = next[j - 1];
                }
                // 4. 前后缀相同，我们需要更新next数组
                if (s.charAt(j) == s.charAt(i))
                    j++;
                next[i] = j;
            }
        }
}

class strStrTest {
    public static void main(String[] args) {
        String haystack = "aabaabaaf";
        String needle = "aabaaf";

        int i = new L6_28_strStr().strStr(haystack,needle);
        System.out.println(i);
    }

}
