package com.ben.neetcode.math;

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
public class N4_isHappy {
    // O(logn) O(logn)
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
     * 一个数字 n 的位数（即它有多少个十进制数字）是与 n 的对数（log n）相关的。具体来说，n 的位数大约是 ⌊log₁₀(n)⌋ + 1。
     * 例如，n = 1000，它的位数是 4，因为 log₁₀(1000) = 3，所以位数是 3 + 1 = 4。
     * 对于 n = 9999，log₁₀(9999) ≈ 3.999，位数也是 4。
     * 假设 n 是一个有 d 位的数字，则可以通过以下方式描述 n 的范围：
     * 也就是说，n 必须是大于等于 10^{d-1} 且小于 10^d 的一个数字。
     * 取对数得 d -1 <= log(n) <= d; => d = logn + 1也就是说，位数和logn有关；
     * @author benjieqiang
     * @date 2023/12/31 3:28 PM
     */
    public int getSum(int n) {
        int sum = 0;
        while (n > 0) { // 注意
            int temp = n % 10; // n reminder 10, get the last digit of n;
            sum += temp * temp;
            n = n / 10; // n is divided by 10, remove the last digit of n;
        }
        return sum;
    }

    // Fast And Slow Pointers O(logn) O(1)
    // if n is happy，no circle，那么快跑者最终会比慢跑者先到达数字 1。
    // if n is not happy, has circle，那么最终快跑者和慢跑者将在同一个数字上相遇。
    //
    public boolean isHappy2(int n) {
        int slow = n;
        int fast = getSum(n);
        while (slow != fast) {
            slow = getSum(slow);
            fast = getSum(fast);
            fast = getSum(fast);
        }
        return slow == 1;
    }
}
