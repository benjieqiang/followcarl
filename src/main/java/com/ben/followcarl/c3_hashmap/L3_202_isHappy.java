package com.ben.followcarl.c3_hashmap;

import java.util.HashSet;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-17  23:14
 * @Description:
 * 「快乐数」 定义为：
 *
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 *
 * 无限循环-》会重复出现-》把所有的n放到集合里面，如果重复出现，则说明无限循环，用set判断。
 * 快速判断一个元素是否出现集合里的时候，哈希法
 * 每位的平方和如果是1就是快乐数
 * 两个难点：
 * 1. 判断使用set；只要n不为1，则一直循环，求当前n的各位和，如果和在set中出现，则说明循环，返回false。否则先加入set，n赋值为sum，继续循环。
 * 2. 如果求n的各位和；
 *  1. 只要n大于0，一直while循环
 *  2. 取个位数：求余:n % 10；
 *  3. 平方累加：
 *  4. 去掉个位：n/10
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

    /**
     * @param n:
     * @return int
     * @description 求各元素的和
     * n % 10 : 取余，
     * n / 10 : 取整，取商。
     * @author benjieqiang
     * @date 2023/12/31 3:28 PM
     */
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
