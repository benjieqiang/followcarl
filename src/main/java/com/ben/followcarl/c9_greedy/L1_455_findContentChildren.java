package com.ben.followcarl.c9_greedy;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-31  11:01
 * @Description: 贪心算法：局部最优推出全局最优
 * @Version: 1.0
 */
public class L1_455_findContentChildren {
    public int findContentChildren(int[] g, int[] s) {
        // 排序之后，大饼干优先给大胃口的孩子
        Arrays.sort(g);
        Arrays.sort(s);
        int index = s.length - 1; // 饼干长度
        int res = 0; //结果集
        // 从后往前遍历， 优先满足大胃口孩子，
        for (int i = g.length - 1; i >= 0; i--) {
            if (index >= 0 && s[index] >= g[i]) {
                res++;
                index--;
            }
        }
        return res;
    }

    @Test
    public void testFindContentChildren(){
        int[] g = {1,2,3};
        int[] s = {1,1};
        System.out.println(findContentChildren(g, s));

    }
}
