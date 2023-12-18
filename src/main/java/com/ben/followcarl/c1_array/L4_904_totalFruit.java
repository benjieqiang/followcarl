package com.ben.followcarl.c1_array;

import org.junit.Test;

import java.util.HashMap;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-06  17:27
 * @Description: 滑动窗口法
 * 窗口
 * 只有两个篮子，只能存放两种水果或者说只能放两种元素，即fruit[i]
 * 可以往map中不断放水果，当水果数目超过2，则需要移动窗口
 * start
 * 从0开始，只要发现水果数目大于2，则需要缩小窗口，如果发现该水果没了，则需要剔除，start往前移动一位
 * 记录结果，取最大的连续子序列
 * end
 * for循环不断往map中存放每种水果的数目
 * map
 * 果种类为键
 * 果数目为值
 *
 * 1. 窗口: map形成的数组,key是水果种类,键是水果的出现的次数;
 * 2. 窗口起始位置如何移动: 当发现窗口里的水果种类数大于2个,则不断的去减少左边的元素,直至满足2个;
 * 3. 窗口终止位置如何移动: for循环遍历整个数组;while循环遍历整个数组;
 * @Version: 1.0
 */
public class L4_904_totalFruit {
    public int totalFruit(int[] fruits) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int i = 0;
        int sub = 0;
        // 滑动窗口的右指针，指向某个种类的果树，
        for (int j = 0; j < fruits.length; j++) {
            // 往篮子中放某种类型的水果。map中有某个水果，在原有数目上加1，没有某个水果的话加1
            hashMap.put(fruits[j], hashMap.getOrDefault(fruits[j], 0) + 1);
            while (hashMap.size() > 2) {
                // 当篮子放的水果种类大于2，则说明需要缩小窗口，移除左边的篮子
                hashMap.put(fruits[i], hashMap.get(fruits[i]) - 1);
                if (hashMap.get(fruits[i]) == 0) {
                    hashMap.remove(fruits[i]);
                }
                i++;
            }
            sub = Math.max(j - i + 1, sub);
        }
        return sub;
    }

    public int totalFruit2(int[] fruits) {
        HashMap<Integer, Integer> hashMap = new HashMap<>(); // key是水果种类，value是数量
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < fruits.length) {
            hashMap.put(fruits[right], hashMap.getOrDefault(fruits[right], 0) + 1);
            // 最长的子序列，不满足要求的时候，移除left对应元素，left右移
            while (hashMap.size() > 2) {
                hashMap.put(fruits[left], hashMap.get(fruits[left]) - 1);
                if (hashMap.get(fruits[left]) == 0) {
                    hashMap.remove(fruits[left]);
                }
                left++;
            }
            res = Math.max(right - left + 1, res);
            right++;
        }
        return res;
    }

    @Test
    public void testFruits() {
        int[] fruits = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        System.out.println(totalFruit(fruits));
    }
}
