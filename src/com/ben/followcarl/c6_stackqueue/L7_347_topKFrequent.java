package com.ben.followcarl.c6_stackqueue;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class L7_347_topKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        // 定义一个map，key：元素；value：数字的频率；
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 定义一个小顶堆
//        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2)->pair1[1]-pair2[1]); //lambda表达式
        // 遍历map，用最小堆保存频率最大的k个元素
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });

        for(Integer key : map.keySet()) { //小顶堆只需要维持k个元素有序
            pq.add(key);
            if(pq.size() > k) {//如果小顶堆的元素数量超过k个，就把堆顶的元素弹出去。
                pq.poll();
            }
        }
        int[] res = new int[k];
        for(int i = k - 1; i >= 0; i--){//依次弹出小顶堆, 因为小顶堆元素最小的在根节点，所以倒序弹出。
            res[i] = pq.poll();
        }

        return res;
    }
}