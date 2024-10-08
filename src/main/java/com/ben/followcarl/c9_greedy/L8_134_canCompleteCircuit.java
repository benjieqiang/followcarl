package com.ben.followcarl.c9_greedy;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-03  11:23 环形加油站 while循环来模拟一圈;
 *
 * @Description: 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 示例 1: 输入:
 *
 * gas = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * 输出: 3 解释:
 *
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 *
 * @Version: 1.0
 */
public class L8_134_canCompleteCircuit {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 暴力遍历：把每个加油站为起点，模拟一圈，中途油没断且最后还有油则返回那个起点；
        for (int i = 0; i < cost.length; i++) {
            int res = gas[i] - cost[i]; // 记录剩余油量
            // 假设 cost 数组的长度为 5，表示有 5 个加油站，索引范围是 0 到 4：
            //如果 index 当前为 4（最后一个加油站），执行 index + 1 后，index 会变成 5。
            //然后，5 % 5 的结果是 0，这样 index 就循环回到了第一个加油站（索引为 0）。
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

    /**
     * @param gas:
     * @param cost:
     * @return int
     * @description 局部最优:
     *
     *  1. 统计每个站点的剩余油量之和一定是大于等于0的,这样才能跑完一圈;
     *  2. 如果小于0,说明i以前无论哪个位置出发都跑不过来;
     *  3. 此时更新i的下一个站点i+1作为新的出发点，并重新统计剩余油量;
     *  4. 最终发现res还是小于0, 说明跑不完一圈,所以返回-1,否则返回start;
     * @author benjieqiang
     * @date 2023/8/28 3:23 PM
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int res = 0; // 油箱总剩余油量
        int curSum = 0; // 每个站点剩余油量
        int start = 0; // 起始位置
        for (int i = 0; i < gas.length; ++i){
            curSum += gas[i] - cost[i];
            res += gas[i] - cost[i];
            if (curSum < 0) {
                // 剩余油量变成负数,就选下一个站点作为新的起点, 说明从0到i这一段无论从哪里开始都会绕不过i这个位置耗油剧增，会变成负数；
                start = i + 1;
                curSum = 0;
            }
        }
        // 总剩余油量如果小于0，则跑不完一圈
        return res < 0 ? -1: start;
    }
    @Test
    public void testCanCompleteCircuit(){
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        System.out.println(canCompleteCircuit2(gas, cost));
    }
}
