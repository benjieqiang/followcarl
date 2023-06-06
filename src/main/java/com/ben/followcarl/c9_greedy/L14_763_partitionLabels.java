package com.ben.followcarl.c9_greedy;

import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-05  23:59
 * @Description: TODO
 * @Version: 1.0
 */
public class L14_763_partitionLabels {
    public List<Integer> partitionLabels(String s) {
        int[] hash = new int[27]; // 26个字母，i为字符，hash[i]为字符出现的最后位置
        for (int i = 0; i < s.length(); i++) { // 统计每一个字符最后出现的位置
            hash[s.charAt(i) - 'a'] = i;
        }
        System.out.println(Arrays.toString(hash));
        List<Integer> res = new ArrayList<>();
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            right = Math.max(right, hash[s.charAt(i) - 'a']); // 所有元素中最远出现位置，找到最远的那个元素位置；
            if (i == right) { //出现了left指向下一个元素
                res.add(right - left + 1);
                left = i + 1;
            }
        }
        return res;
    }

    @Test
    public void testString() {
        String s = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(s));
    }
}
