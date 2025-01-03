package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-22  23:24
 * @Description: 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * <p>
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 * <p>
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 * <p>
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 * <p>
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 * <p>
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * 提示：
 * <p>
 * 0 <= s.length <= 3000
 * s 仅由数字组成
 * @Version: 1.0
 */
public class L7_93_restoreIPAddress {


    List<String> res = new ArrayList<>();

    /**
     * @param s:     题目给的字符串
     * @param index: 用来决定for循环下一层从哪里开始
     * @param num:   统计字符串中小数点的个数,为3会收获结果;
     * @return void  O of three to the power of four
     * @description * 时间复杂度: O(3^4)，IP地址最多包含4个数字，每个数字最多有3种可能的分割方式，则搜索树的最大深度为4，每个节点最多有3个子节点。
     * * 空间复杂度: O(n)
     * @author benjieqiang
     * @date 2023/9/2 3:05 PM
     */
    // s：字符串，index：递归下一层for循环起始位置；num：小数点的数量，3表示分割成了4段；
    private void backtracking(String s, int index, int num) {
        if (num >= 4) return;
        // 逗点数量为3时，说明是四段；判断第四段⼦字符串是否合法，如果合法就放进res中
        if (num == 3 && isValid(s, index, s.length() - 1)) {
            res.add(s);
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (!isValid(s, index, i)) break; // 如果不合法直接终止此次递归流程；
            s = s.substring(0, i + 1) + "." + s.substring(i + 1); // 每次过来给i指向的字符后面加个点.
            backtracking(s, i + 2, num + 1); //所以index要从i+2位置开始
            s = s.substring(0, i + 1) + s.substring(i + 2); // 去掉.,因为.在i+1的位置，所以拼接字符串从i+2开始；
        }
    }

    /**
     * @param s:
     * @param left:
     * @param right:
     * @return boolean
     * @description 取s的[left, right]的字符串转成数字，判断是否合法；
     * num*10的解释: s="2.5.5.25511135"过来之后,left=6,right=13
     * 此时需要i从left遍历到13来找到这个数num, num第一次=2; 接着left++变7, s[7] = 5, 原来的值进一位+当前值
     * num = 2* 10 + 5 = 25; 只有这样才能变成一个合法整数;
     * @author benjieqiang
     * @date 2023/9/2 3:11 PM
     */
    private boolean isValid(String s, int left, int right) {
        if (left > right) return false;
        if (s.charAt(left) == '0' && left != right) return false; // 子串起始是0，且子串的长度大于 1，比如s = "01"这种

        int num = 0;
        for (int i = left; i <= right; i++) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') return false; // 段中遇到⾮数字字符不合法
            num = num * 10 + (ch - '0'); // 比如当我们处理子串123 时，首先 num 为 1，然后变为 12，最后变为 123
            if (num > 255) return false; // 段中如果⼤于255了不合法
        }
        return true;
    }

    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) return res; //最长是255255255255，长度为12；
        backtracking(s, 0, 0);
        return res;
    }

    @Test
    public void testValidStr() {
        String str = "124";
        System.out.println(isValid(str, 0, str.length() - 1));
    }

    class Solution0925 {
        List<String> res = new LinkedList<>();

        public List<String> restoreIpAddresses(String s) {
            if (s == null || s.length() == 0) return res;
            backtracking(s, 0, 0); // index, dotNum;
            return res;
        }

        private void backtracking(String s, int index, int num) {
            if (num == 3 && isValid(s, index, s.length() - 1)) {
                res.add(s);
                return;
            }
            for (int i = index; i < s.length(); i++) {
                if (!isValid(s, index, i)) break;
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                backtracking(s, i + 2, num + 1);
                s = s.substring(0, i + 1) + s.substring(i + 2);
            }
        }

        private boolean isValid(String s, int left, int right) {
            if (left > right) return false;
            if (s.charAt(left) == '0' && left != right) return false;
            int num = 0;
            for (int i = left; i <= right; i++) {
                char ch = s.charAt(i);
                if (ch < '0' || ch > '9') return false;
                num = num * 10 + ch - '0';
                if (num > 255) return false;
            }
            return true;
        }
    }

    class Solution {
        List<String> res = new LinkedList<>();

        public List<String> restoreIpAddresses(String s) {
            if (s == null || s.length() == 0 || s.length() > 12) return res;
            backtracking(s, 0, 0); // s, index, dotNum;
            return res;
        }

        private void backtracking(String s, int index, int num) {
            if (num >= 4) return;
            if (num == 3 && isValid(s, index, s.length() - 1)) {
                res.add(s);
                return;
            }
            for (int i = index; i < s.length(); i++) {
                if (!isValid(s, index, i)) break;
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                backtracking(s, i + 2, num + 1); // 把s分成0-i+1， 点，i+1到末尾三段。在递归的下一层再进行判断。
                s = s.substring(0, i + 1) + s.substring(i + 2);
            }
        }

        private boolean isValid(String s, int left, int right) {
            if (left > right) return false;
            if (s.charAt(left) == '0' && left != right) return false;
            int num = 0;
            for (int i = left; i <= right; i++) {
                char ch = s.charAt(i);
                if (ch < '0' || ch > '9') return false;
                num = num * 10 + ch - '0';
                if (num > 255) return false;
            }

            return true;
        }
    }

    //方法一：但使用stringBuilder，故优化时间、空间复杂度，因为向字符串插入字符时无需复制整个字符串，从而减少了操作的时间复杂度，也不用开新空间存subString，从而减少了空间复杂度。
    class Solution2 {
        List<String> result = new ArrayList<>();

        public List<String> restoreIpAddresses(String s) {
            StringBuilder sb = new StringBuilder(s);
            backTracking(sb, 0, 0);
            return result;
        }

        private void backTracking(StringBuilder s, int startIndex, int dotCount) {
            if (dotCount == 3 && isValid(s, startIndex, s.length() - 1)) {
                result.add(s.toString());
                return;
            }
            for (int i = startIndex; i < s.length(); i++) {
                if (!isValid(s, startIndex, i)) break;
                s.insert(i + 1, '.');
                backTracking(s, i + 2, dotCount + 1);
                s.deleteCharAt(i + 1);
            }
        }

        //[start, end]
        private boolean isValid(StringBuilder s, int start, int end) {
            if (start > end) return false;
            if (s.charAt(start) == '0' && start != end) return false;
            int num = 0;
            for (int i = start; i <= end; i++) {
                int digit = s.charAt(i) - '0';
                num = num * 10 + digit;
                if (num > 255) return false;
            }
            return true;
        }
    }

    @Test
    public void testRestoreIPAddress() {
        String str = "25525511135";
        System.out.println(restoreIpAddresses(str));
    }
}
