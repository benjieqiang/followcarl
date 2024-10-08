package com.ben.followcarl.c9_greedy;

import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-05  23:59
 * @Description:
 *
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 *
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 *
 * 返回一个表示每个字符串片段的长度的列表。
 *
 *
 *
 * 示例 1：
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
 *
 * 思路:
 * 1. 利用hash来统计每一个字符最后出现的位置, 最远下标就是分割点; key是字符-'a', 键是最远下标;
 * 2. 遍历字符串, 不断更新最远下标(right和hash[s.charAt(i)-  'a']取最大值)，当前下标和最远下标相等时是分割点；
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)，使用的hash数组是固定大小
 *
 * @Version: 1.0
 */
public class L14_763_partitionLabels {
    public List<Integer> partitionLabels(String s) {
        int[] hash = new int[26]; // 26个字母，i为字符，hash[i]为字符出现的最后位置
        for (int i = 0; i < s.length(); i++) { // 统计每一个字符最后出现的位置
            hash[s.charAt(i) - 'a'] = i;
        }
        System.out.println(Arrays.toString(hash));
        List<Integer> res = new ArrayList<>();
        int left = 0; // 区间起始位置;
        int right = 0; // 区间最远下标位置;
        for (int i = 0; i < s.length(); i++) {
            right = Math.max(right, hash[s.charAt(i) - 'a']); // 所有元素中最远出现位置，找到最远的那个元素位置；
            if (i == right) { // 当遍历到了最远下标处, 这个区间已经包含了他前面的所有的字符;
                res.add(right - left + 1); //存放长度
                left = i + 1; // 新区间起始位置
            }
        }
        return res;
    }

    @Test
    public void testString() {
        String s = "jbabcbacadefegdehijhkliz";
        System.out.println(partitionLabels(s));
    }
}
