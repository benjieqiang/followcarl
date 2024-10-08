package com.ben.followcarl.c9_greedy;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-31  11:01
 * @Description:
 *
 * @Version: 1.0
 */
public class L1_455_findContentChildren {
    // 错误写法：大饼干满足大胃口孩子，修改为：只能先遍历胃口，再遍历饼干
//    public int findContentChildren(int[] g, int[] s) {
//        // 排序之后，大饼干优先给大胃口的孩子
//        Arrays.sort(g);
//        Arrays.sort(s);
//        int index = g.length - 1; // 饼干长度
//        int res = 0; //结果集
//        // 从后往前遍历
//        for (int i = s.length - 1; i >= 0; i--) {
//            // 饼干从后往前遍历, 如果大饼干尺寸大于等于孩子胃口,收集结果;
//            if (index >= 0 && s[i] >= g[index]) {
//                res++;
//                index--;
//            }
//        }
//        return res;
//    }
    public int findContentChildren(int[] g, int[] s) {
        // 排序之后，大饼干优先给大胃口的孩子
        Arrays.sort(g);
        Arrays.sort(s);
        int index = s.length - 1; // 饼干长度
        int res = 0; //结果集
        // 从后往前遍历，先固定胃口，for循环中
        for (int i = g.length - 1; i >= 0; i--) {
            // 饼干从后往前遍历, 如果大饼干尺寸大于等于孩子胃口,收集结果;
            if (index >= 0 && s[index] >= g[i]) {
                res++;
                index--;
            }
        }
        return res;
    }
    public int findContentChildren2(int[] g, int[] s) {
        // 排序之后, 小饼干先给小胃口孩子;
        // 先遍历饼干 分发饼干给孩子,满足孩子胃口;
        Arrays.sort(g);
        Arrays.sort(s);
        int index = 0;
        for (int i = 0; i < s.length; i++) {
            if (index < g.length && s[i] >= g[index]) {
                index++;
            }
        }
        return index;
    }

    @Test
    public void testFindContentChildren(){
        int[] g = {7,8,9,10};
        int[] s = {5,6,7,8};
        System.out.println(findContentChildren(g, s));
        System.out.println(findContentChildren2(g, s));

    }
}
