package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
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
     * @return void
     * @description * 时间复杂度: O(3^4)，IP地址最多包含4个数字，每个数字最多有3种可能的分割方式，则搜索树的最大深度为4，每个节点最多有3个子节点。
     * * 空间复杂度: O(n)
     * @author benjieqiang
     * @date 2023/9/2 3:05 PM
     */
    private void backtracking(String s, int index, int num) {
        // 逗点数量为3时，说明是四段；判断第四段⼦字符串是否合法，如果合法就放进res中
        if (num == 3 && isValid(s, index, s.length() - 1)) {
            res.add(s);
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (!isValid(s, index, i)) break;
            s = s.substring(0, i + 1) + "." + s.substring(i + 1); // 每次过来给i指向的字符后面加了一个.
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
        if (s.charAt(left) == '0' && left != right) return false; // s = "01"这种

        int num = 0;
        for (int i = left; i <= right; i++) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') return false; // 段中遇到⾮数字字符不合法
            num = num * 10 + (ch - '0');
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

    @Test
    public void testRestoreIPAddress() {
        String str = "25525511135";
        System.out.println(restoreIpAddresses(str));
    }
}
