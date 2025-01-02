package com.ben.neetcode.bit;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-27  11:23
 * @Description: 统计二进制中1的个数
 * 从左到右利用x & (x - 1) 删掉最低位的1；统计次数；
 * @Version: 1.0
 */
public class N2_192_hammingWeight {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}
