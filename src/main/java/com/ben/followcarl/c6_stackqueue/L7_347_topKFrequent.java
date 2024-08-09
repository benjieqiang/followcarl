package com.ben.followcarl.c6_stackqueue;

import org.junit.Test;

import java.util.*;

/**
 * @description 小顶堆来实现
 * 这种方法的时间复杂度为 \(O(N \log k)\)，其中 \(N\) 是数组的长度，因为堆操作是对数时间复杂度。该方法在内存和时间上都很高效，非常适合解决这个问题。
 * @author benjieqiang
 * @date 2023/7/26 11:51 PM
 */
public class L7_347_topKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        // 定义一个map，key：元素；value：数字的频率；
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 定义一个小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (n1, n2) -> map.get(n1) - map.get(n2)
        );

        // 遍历map，用最小堆保存频率最大的k个元素
        for(Integer key : map.keySet()) { //小顶堆只需要维持k个元素有序
            pq.add(key);
            if(pq.size() > k) {//如果小顶堆的元素数量超过k个，就把堆顶的元素弹出去。
                pq.poll(); // 在最小堆中，根元素是堆中最小的元素。这里是key出现次数最少的那个元素；
            }
        }
        int[] res = new int[k];
//        for(int i = k - 1; i >= 0; i--){//依次弹出小顶堆, 因为小顶堆元素最小的在根节点，所以倒序弹出。
//            res[i] = pq.poll();
//        }
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        return res;
    }

    public int[] topKFrequent2(int[] nums, int k) {
        // 统计每个元素的频率
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 创建大顶堆，按频率排序
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (n1, n2) -> map.get(n2) - map.get(n1)  // 频率较高的排在前面
        );

        // 将所有元素加入大顶堆
        pq.addAll(map.keySet());

        // 提取前 k 个频率最高的元素
        int[] topK = new int[k];
        for (int i = 0; i < k; i++) {
            topK[i] = pq.poll();  // 取出堆顶元素
        }

        return topK;
    }

    @Test
    public void test_topKFrequent() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = topKFrequent(nums, k);
        int[] result2 = topKFrequent2(nums, k);
        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(result2));
    }
}