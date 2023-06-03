package com.ben.followcarl.c9_greedy;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-03  11:23
 * @Description: TODO
 * @Version: 1.0
 */
public class L8_134_canCompleteCircuit {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 暴力遍历：把每个加油站为起点，模拟一圈，中途油没断且最后还有油则返回那个起点；
        for (int i = 0; i < cost.length; i++) {
            int res = gas[i] - cost[i]; // 记录剩余油量
            int index = (i + 1) % cost.length;
            while (res > 0 && index != i) { // 模拟以i为起点行驶一圈
                res += gas[index] - cost[index];
                index = (index + 1) % cost.length;
            }
            // 如果以i为起点跑一圈，剩余油量>=0，返回该起始位置
            if (res >= 0 && index == i) return i;
        }
        return -1;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int res = 0; // 油箱最终的油量
        int curSum = 0; // 统计每个站点的剩余油量
        int start = 0; // 起始位置
        for (int i = 0; i < gas.length; ++i){
            curSum += (gas[i] - cost[i]);
            res += (gas[i] - cost[i]);
            if (curSum < 0) {
                // 说明从0到i这一段无论从哪里开始都会绕不过i这个位置耗油剧增，会变成负数，此时我们需要从i+1位置开始；
                start = i + 1;
                curSum = 0;
            }
        }
        return res < 0 ? -1: start;
    }
    @Test
    public void testCanCompleteCircuit(){
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        System.out.println(canCompleteCircuit(gas, cost));
    }
}
