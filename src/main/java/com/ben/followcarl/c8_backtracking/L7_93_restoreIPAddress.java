package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-22  23:24
 * @Description: TODO
 * @Version: 1.0
 */
public class L7_93_restoreIPAddress {
    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) return res; //最长是255255255255，长度为12；
        backtracking(s, 0, 0);
        return res;
    }

    // startIndex: 搜索的起始位置， pointNum:添加逗点的数量
    private void backtracking(String s, int startIndex, int pointNum) {
        if (pointNum == 3) {// 逗点数量为3时，说明是四段；
            // 判断第四段⼦字符串是否合法，如果合法就放进res中
            if (isValid(s, startIndex, s.length() - 1)) {
                res.add(s);
            }
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isValid(s, startIndex, i)) {
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);    //在str的后⾯插⼊⼀个逗点
                pointNum++;
                backtracking(s, i + 2, pointNum);// 插⼊逗点之后下⼀个⼦串的起始位置为i+2
                pointNum--;// 回溯
                s = s.substring(0, i + 1) + s.substring(i + 2);// 回溯删掉逗点
            } else {
                break;
            }
        }
    }

    // 判断字符串s在左闭右闭区间[start, end]所组成的数字是否合法
    private Boolean isValid(String s, int start, int end) {
        if (start > end) {
            return false;
        }
        if (s.charAt(start) == '0' && start != end) { // 段头是0，数字不合法
            return false;
        }
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') { // 段中遇到⾮数字字符不合法
                return false;
            }
            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255) { // 段中如果⼤于255了不合法
                return false;
            }
        }
        return true;
    }

    @Test
    public void testValidStr() {
        String str = "124";
        System.out.println(isValid(str, 0 , str.length() - 1));
    }
    @Test
    public void testRestoreIPAddress() {
        String str = "25525511135";
        List<String> res = restoreIpAddresses(str);
        System.out.println(res);
    }
}
