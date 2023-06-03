package com.ben.followcarl.c9_greedy;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-03  12:41
 * @Description: TODO
 * @Version: 1.0
 */
public class L10_860_lemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        //1. 定义三个数记录钱数；
        int five = 0, ten = 0, twenty = 0;
        //2. 分三种情况：
        for (int bill : bills) {
            // 1. 买5美元的
            if (bill == 5) five++;
            // 2. 买10美元的；
            if (bill == 10) {
                if (five <= 0) return false;
                ten++;
                five--;
            }
            // 3. 买20美元的，局部最优：优先消耗10+5组合，其次是5+5+5组合，因为5美元可以给10美元来找零。
            if (bill == 20) {
                if (five >0 && ten >0) {
                    five--;
                    ten--;
                    twenty++;
                } else if (five >= 3) {
                    five -= 3;
                    twenty++;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void testRes() {

    }
}
