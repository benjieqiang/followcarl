package com.ben.followcarl.c3_hashmap;

import java.util.HashSet;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-17  23:14
 * @Description: 快速判断一个元素是否出现集合里的时候，哈希法
 * 每位的平方和如果是1就是快乐数
 * @Version: 1.0
 */
public class L3_202_isHappy {
    public boolean isHappy(int n) {
        HashSet<Integer> hSet = new HashSet<>();
        while (n != 1) { // 不是1就不是快乐数
            int sum = getSum(n);
            if (hSet.contains(sum)) return false; // 如果set中出现了，说明进入了循环；
            hSet.add(sum); //没有的话，直接往set放；
            n = sum; // 重新赋值n
        }
        return true;
    }

    // 求各元素的和
    public int getSum(int n) {
        int sum = 0;
        while (n > 0) {
            int temp = n % 10;
            sum += temp * temp;
            n = n / 10;
        }
        return sum;
    }
}
