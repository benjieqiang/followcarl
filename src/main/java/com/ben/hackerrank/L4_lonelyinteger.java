package com.ben.hackerrank;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-11-30  11:27
 * @Description: Given an array of integers, where all elements but one occur twice, find the unique element.
 * @Version: 1.0
 */
public class L4_lonelyinteger {
    public static int lonelyinteger(List<Integer> a) {
        int[] map = new int[101];
        for (int num : a) {
            map[num]++;
        }
        for (int i = 1; i < map.length; i++) {
            if (map[i] == 1) return i;
        }
        return 0;
    }

    /*
    异或操作有以下性质：
        任何数与自己异或的结果都是0，即 x ^ x = 0。
        0 与任何数异或的结果都是这个数本身，即 0 ^ x = x。
        异或操作满足交换律，即 a ^ b = b ^ a。
     */
    public static int lonelyinteger2(List<Integer> a) {
        int res = 0;
        for (int num : a) {
            res ^= num;
        }
        return res;
    }
}
