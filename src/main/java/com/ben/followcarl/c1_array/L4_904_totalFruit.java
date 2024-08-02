package com.ben.followcarl.c1_array;

import org.junit.Test;

import java.util.HashMap;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-06  17:27
 * @Description: 滑动窗口法
 * 窗口
 * 只有两个篮子，只能存放两种水果或只能放两种元素，即fruit[i]
 * 可以往map中不断放水果，当水果数目超过2，则需要移动窗口
 * start
 * 从0开始，只要发现水果数目大于2，则需要缩小窗口，如果发现该水果没了，则需要剔除，start往前移动一位
 * 记录结果，取最大的连续子序列
 * end
 * for循环不断往map中存放每种水果的数目
 * map
 * 果种类为键
 * 果数目为值
 * <p>
 * 1. 窗口: map形成的数组,key是水果种类,键是水果的出现的次数;
 * 2. 窗口起始位置如何移动: 当发现窗口里的水果种类数大于2个,则不断的去减少左边的元素,直至满足2个;
 * 3. 窗口终止位置如何移动: for循环遍历整个数组;while循环遍历整个数组;
 *
 *
 * 时间复杂度：O(n)，其中 n 是数组 fruit的长度。
 *
 * 空间复杂度：O(1)。哈希表中最多会有三个键值对，可以看成使用了常数级别的空间。
 *
 *
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
                // 当篮子放的水果种类大于2，不满足只能装2种水果的条件，所以需要在while循环内不断缩小窗口，移除左边的篮子
                hashMap.put(fruits[i], hashMap.get(fruits[i]) - 1);
                if (hashMap.get(fruits[i]) == 0) {
                    hashMap.remove(fruits[i]);
                }
                i++; // left右移
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
                if (hashMap.get(fruits[left]) == 0) { // 水果都没有了，不能还继续占着map的位置，所以得删除掉；
                    hashMap.remove(fruits[left]);
                }
                left++;
            }
            res = Math.max(right - left + 1, res);
            right++;
        }
        return res;
    }
    /**
     * @param fruits:
     * @return int
     * @description 这次是真看样例，都理解不了是啥题意：其实就是从任意位置开始，同时使用两个篮子采集，一旦选择后不能修改篮子所装的水果种类，当所有树处理完或遇到第一棵种类不同的树则停止。
     *
     * 滑动窗口模拟题：使用 j 和 i 分别代表滑动窗口的两端，窗口种类不超过 222 种为合法。
     *
     * 作者：宫水三叶
     * 链接：https://leetcode.cn/problems/fruit-into-baskets/solutions/1437444/shen-du-jie-xi-zhe-dao-ti-he-by-linzeyin-6crr/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @author benjieqiang
     * @date 2023/12/18 6:17 PM
     */
    public int totalFruit3(int[] fruits) {
        int res = 0;
        int left = 0;
        int right = 0;
        int n = fruits.length;
        int[] cnts = new int[n + 1];
        int count = 0;

        while (right < n) {
            if (++cnts[fruits[right]] == 1) count++;
            while (count > 2) {
                if (--cnts[fruits[left]] == 0) count--;
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }

        return res;
    }

    @Test
    public void testFruits() {
//        int[] fruits = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        int[] fruits = {1, 2, 3, 2, 2};
//        int[] fruits = {1, 2, 1};
        System.out.println(totalFruit(fruits));
    }
}
