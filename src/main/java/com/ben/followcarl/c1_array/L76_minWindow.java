package com.ben.followcarl.c1_array;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-12-18  21:22
 * @Description:
 *
 * 这段代码实现了一个算法，用于在字符串 `s` 中找到包含字符串 `t` 所有字符的最小窗口子串。如果不存在这样的子串，函数返回一个空字符串。代码使用滑动窗口技术来动态地调整窗口的大小，以便在 `s` 中找到满足条件的最小窗口。
 *
 * 以下是代码的详细解释：
 *
 * 1. **输入验证**：
 *    - 首先检查 `s` 和 `t` 是否为空字符串或 `null`，以及 `s` 的长度是否小于 `t`。如果是，则直接返回空字符串，因为不可能找到满足条件的子串。
 *
 * 2. **初始化**：
 *    - 创建两个哈希映射 `need` 和 `window`：
 *      - `need` 用于记录字符串 `t` 中每个字符的需要数量。
 *      - `window` 用于记录当前窗口中每个字符的数量。
 *    - 遍历字符串 `t`，将每个字符的出现次数记录到 `need` 中。
 *
 * 3. **滑动窗口初始化**：
 *    - 定义左右指针 `left` 和 `right`，分别表示窗口的左边界和右边界。
 *    - 初始化 `minStart` 和 `minLen`，用于记录最小窗口的起始位置和长度。
 *    - 初始化 `valid`，用于记录当前窗口中满足需要条件的字符数量。
 *
 * 4. **右移窗口**：
 *    - 当 `right` 小于 `s` 的长度时，执行循环：
 *      - 获取 `right` 位置的字符 `c`，并将其加入 `window` 中，增加其计数。
 *      - 如果字符 `c` 在 `need` 中存在，并且当前窗口中字符 `c` 的数量与 `need` 中所需的数量相等，则将 `valid` 增加 1，表示一个字符已满足条件。
 *
 * 5. **缩小窗口**：
 *    - 当 `valid` 等于 `need` 的大小时，表示当前窗口已经包含了所有 `t` 中的字符：
 *      - 检查当前窗口的长度是否小于 `minLen`，如果是，更新 `minLen` 和 `minStart`。
 *      - 通过移动 `left` 来缩小窗口：
 *        - 取出 `left` 位置的字符 `d`。
 *        - 如果 `d` 在 `need` 中且 `window` 中的数量与 `need` 相等，则将 `valid` 减少 1。
 *        - 减少 `d` 在 `window` 中的计数。
 *        - 将 `left` 向右移动一位。
 *
 * 6. **返回结果**：
 *    - 循环结束后，检查 `minLen` 是否被更新（是否找到过符合条件的窗口）。如果 `minLen` 仍为初始值 `Integer.MAX_VALUE`，说明没有找到符合条件的子串，返回空字符串。
 *    - 否则，返回 `s` 中从 `minStart` 开始长度为 `minLen` 的子串。
 *
 * 这段代码有效地通过滑动窗口机制找到了字符串 `s` 中满足条件的最小窗口子串。滑动窗口的机制通过动态地调整窗口的大小来有效地找到满足条件的子串，并在找到符合条件的窗口后继续尝试寻找更小的窗口。
 * @Version: 1.0
 */
public class L76_minWindow {
    public String minWindow(String s, String t) {
        if (s == null || s == "" || t == null || t == "" || s.length() < t.length()) return "";

        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        // 统计t中各字符出现的次数
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int minStart = 0;
        int minLen = Integer.MAX_VALUE;
        int valid = 0; // 已经匹配的字符个数

        while (right < s.length()) {
            char c = s.charAt(right);
            // 更新 window
            window.put(c, window.getOrDefault(c, 0) + 1);

            // 如果need中有该字符，且个数与窗口里的字符数目一样，则说明找到一个字符了。
            if (need.containsKey(c) && window.get(c).equals(need.get(c))) {
                valid++;
            }

            // 如果发现窗口里面的字符和need中的字符数目一样，则说明已经找到解，缩小窗口找最优解
            while (valid == need.size()) {
                char d = s.charAt(left);
                if (need.containsKey(d) && window.get(d).equals(need.get(d))) {
                    valid--;
                }
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }
                window.put(d, window.get(d) - 1);
                left++;
            }
            right++;
        }

        // s = "HELLO" 和 t = "XYZ"：遍历完，minLen一直是MAX_VALUE，所以直接返回""
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    // 其中 65～90号为 26个大写英文字母，97～122号为 26个小写英文字母，其余为一些标点符号、运算符号等

    // 对于数组类型，其下标为 int类型
    // 可以直接使用 char类型变量，默认强制转换，存储的就是字母对应的 ASCII码
    // 比如 t.charAt(i) = "a'，存储的就是 map[97]
    // 条件 need[c] >= window[c] 表示在扩大窗口时，确保窗口中的字符次数不少于字符串 t 中的需求次数。
    // 而条件 need[d] >= window[d] 表示在缩小窗口时，确保缩小后的窗口仍然包含了字符串 t 中字符的正确数量。

    public String minWindow2(String s, String t) {
        if (s == null || s == "" || t == null || t == "" || s.length() < t.length()) return "";
        int[] need = new int[128]; // need存放t中每个字符出现的词频；
        int[] window = new int[128]; // 统计窗口中每个字符出现次数

        // t中每个字符出现的频次放入need
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }

        // 窗口的左右边；
        int left = 0;
        int right = 0;
        int valid = 0; // 窗口中已经匹配的字符个数
        int minLen = Integer.MAX_VALUE; // 记录最小子串的长度
        int minStart = 0; // 记录最小子串的起点

        while (right < s.length()) {
            char c = s.charAt(right);
            window[c]++; // 扩大窗口
            //
            if (need[c] > 0 && need[c] >= window[c]) valid++;
            // 最小窗口，找到则收获结果
            while (valid == t.length()) {
                char d = s.charAt(left); // 最左字符
                if (need[d] > 0 && need[d] >= window[d]) valid--;
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }
                // 窗口缩小，left右移
                window[d]--;
                left++;
            }
            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }


    @Test
    public void test(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
//        String s = "aa", t = "aa";
//        输出："BANC"
        System.out.println(minWindow2(s, t));
    }
}
