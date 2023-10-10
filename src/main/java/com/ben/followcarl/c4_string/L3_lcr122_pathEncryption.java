package com.ben.followcarl.c4_string;

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
}
