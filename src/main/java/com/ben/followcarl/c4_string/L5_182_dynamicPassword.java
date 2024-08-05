package com.ben.followcarl.c4_string;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-10-10  20:35
 * @Description: 某公司门禁密码使用动态口令技术。初始密码为字符串 password，密码更新均遵循以下步骤：
 * <p>
 * 设定一个正整数目标值 target
 * 将 password 前 target 个字符按原顺序移动至字符串末尾
 * 请返回更新后的密码字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: password = "s3cu/r1tyC0d3", target = 4
 * 输出: "r1tyC0d3s3cu"
 * 示例 2：
 * <p>
 * 输入: password = "lrlose/umgh", target = 6
 * 输出: "umghlrlose"
 *
 * 举例：
 * 1. 整体反转：hgmu/esolrl
 * 2. 反转0~len-target-1;
 * 3. 反转target~len-1;
 * @Version: 1.0
 */
public class L5_182_dynamicPassword {
    // 方法1，借助string的substring性质；时间复杂度 O(N)： 其中 NNN 为字符串 password 的长度，字符串切片函数为线性时间复杂度（参考资料）。
    //空间复杂度 O(N) ： 两个字符串切片的总长度为 N 。
    public String dynamicPassword(String password, int target) {
        if (password == null || password.length() == 0) return password;
        return password.substring(target, password.length()) + password.substring(0, target);
    }

    public String dynamicPassword2(String password, int target) {
        if (password == null || password.length() == 0) return password;

        StringBuilder sb = new StringBuilder();

        for (int i = target; i < password.length(); i++) {
            sb.append(password.charAt(i));
        }
        for (int i = 0; i < target; i++) {
            sb.append(password.charAt(i));
        }
        return sb.toString();
    }

    //  * 1. 整体反转：
    // * 2. 反转0~len-target-1;
    // * 3. 反转len-target~len-1;
    public String dynamicPassword3(String password, int target) {
        if (password == null || password.length() == 0) return password;
        int len = password.length();
        if (target >= len) return password;
        char[] chars = password.toCharArray();
        reverse(chars, 0, len - 1);
        reverse(chars, 0, len - target - 1);
        reverse(chars, len - target, len - 1);
        return new String(chars);
    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
    }
}
