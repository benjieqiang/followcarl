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
 * 输入: password = "s3cur1tyC0d3", target = 4
 * 输出: "r1tyC0d3s3cu"
 * 示例 2：
 * <p>
 * 输入: password = "lrloseumgh", target = 6
 * 输出: "umghlrlose"
 * @Version: 1.0
 */
public class L5_182_dynamicPassword {
    // 方法1，借助string的substring性质；
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
}
