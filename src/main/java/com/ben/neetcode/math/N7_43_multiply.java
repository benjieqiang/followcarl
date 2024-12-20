package com.ben.neetcode.math;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-19  10:32
 * @Description:
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 * 123
 * 456
 * res数组长度为6（范围0-5）n+m
 * 1. i = 2, j = 2, i+j= 4, i+j + 1= 5
 * mult = 3 * 6 = 18;
 * sum = 18 + res[i+j+1] = 18 + 0 = 18
 * res[4] = 18/10 = 1
 * res[5] = 18%10 = 8
 * 2. i = 2, j = 1, i+j = 3, i+j+1=4
 * mult = 3 * 5 = 15;
 * sum = mult + res[4] = 15+1=16
 * res[3] = 1
 * res[4] = 6
 * @Version: 1.0
 */
public class N7_43_multiply {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        int n = num1.length();
        int m = num2.length();
        int[] res = new int[n + m];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int mult = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); // 计算i，j位的乘机
                int sum = mult + res[i + j + 1];
                res[i + j] += sum / 10;
                res[i + j + 1] = sum % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n + m; i++) {
            // neglect leading 0, sb.length() == 0 sb为空
            if (sb.length() == 0 && res[i] == 0) continue;
            sb.append(res[i]);
        }

        return sb.toString();
    }
}
