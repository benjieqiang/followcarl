package com.ben.followcarl.c9_greedy;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-03  12:41
 * @Description: 每一杯柠檬水的售价为 5 美元。
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 * 输入：[5,5,5,10,20]
 * 输出：true
 * 解释：
 * 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
 * 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
 * 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
 * 由于所有客户都得到了正确的找零，所以我们输出 true。
 *
 * 思路:
 * 1. 定义三个数记录钱数；
 * 2. 分三种情况：
 *  1. 买5美元的,直接买,5元进账;
 *  2. 买10美元的；先判断是否还有5美元给人家找零, 没有直接返回false,否则five--,ten++;
 *  3. 买20美元的, 先判断是否有10美元和5美元的零钱,有的话,five--,ten--,twenty++;
 *               如果上一个条件不满足, 看是否有大于3张以上的5美元, 有的话,five -= 3, twenty++;
 *               以上条件都不满足,则直接返回false;
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
        int[] bills = {5,5,5,10,20};
        System.out.println(lemonadeChange(bills));
    }
}
