package com.ben.neetcode.arrayshashing;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-18  09:42
 * @Description: Encode and Decode Strings
 * Solved
 * Design an algorithm to encode a list of strings to a single string. The encoded string is then decoded back to the original list of strings.
 * <p>
 * Please implement encode and decode
 * <p>
 * Example 1:
 * <p>
 * Input: ["neet","code","love","you"]
 * <p>
 * Output:["neet","code","love","you"]
 * Example 2:
 * <p>
 * Input: ["we","say",":","yes"]
 * <p>
 * Output: ["we","say",":","yes"]
 * Constraints:
 * <p>
 * 0 <= strs.length < 100
 * 0 <= strs[i].length < 200
 * strs[i] contains only UTF-8 characters.
 * @Version: 1.0
 */
public class N6_241_EncodeAndDecode {
    // length#str
    public String encode(List<String> strs) {
        if (strs == null || strs.size() == 0) return null;
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append('#').append(str);
        }
        return sb.toString();
    }

    // 解码注意边界条件；
    public List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) return res;
        int i = 0;
        while (i < str.length()) {
            int j = i;
            // j find the first not #. 4#leet, j = 1
            while (str.charAt(j) != '#') {
                j++;
            }
            // i-j is the integer. str.substring(0,1) = "4"
            int length = Integer.parseInt(str.substring(i, j));
            // (2,6) 6取不到。
            res.add(str.substring(j + 1, j + 1 + length));
            // 移动i到下一个字符串开始位置；
            i = j + 1 + length;
        }
        return res;
    }
}
