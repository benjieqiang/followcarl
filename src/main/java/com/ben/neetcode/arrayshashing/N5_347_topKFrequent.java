package com.ben.neetcode.arrayshashing;

import org.junit.Test;

import java.util.*;

/**
 * @author benjieqiang
 * @description 小顶堆来实现
 * 这种方法的时间复杂度为 (O(N log k))，其中 (N) 是数组的长度，因为堆操作是对数时间复杂度。该方法在内存和时间上都很高效，非常适合解决这个问题。
 * 1. 先用map统计每个元素出现的频次，遍历map，加入元素，
 * 2. 堆中比较器规则是：频率大小比较，即map.get(num)比较。用最小堆保存频率最大的k个元素，只要长度超过k，弹出堆顶元素(出现频率最小)，之后从前往后遍历小顶堆，拿到k个元素。
 * 使用最大堆保存加入map的所有key，之后遍历k次，拿到前k个元素。
 * O(N log k)，其中 N是数组的长度，因为堆操作是对数时间复杂度
 * @date 2023/7/26 11:51 PM
 */
public class N5_347_topKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        // 定义一个map，key：num；value：num's freq；
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 定义一个小顶堆，存储元素
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (n1, n2) -> map.get(n1) - map.get(n2)
        );

        // map = {1=3,2=2,3=1}, 用最小堆保存频率最大的k个元素 [2,1]. head is 2,
        for (Integer key : map.keySet()) { //小顶堆只需要维持k个元素有序
            pq.add(key); // 3进来，因为3频率是1，所以pq变成[3,1,2]，此时pq要存放最大的k个元素，所以需要弹出最小的那个元素，即堆顶的3，变成[2,1]
            if (pq.size() > k) {//如果小顶堆的元素数量超过k个，就把堆顶的元素弹出去。
                pq.poll(); // 在最小堆中，根元素是堆中最小的元素。这里是key出现次数最少的那个元素；
            }
        }
        int[] res = new int[k];
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

        // map = {1=3,2=2,3=1}, 比较freq最大的在堆顶，加入大顶堆[1,2,3] head is 1
        pq.addAll(map.keySet());

        // 提取前 k 个频率最高的元素
        int[] topK = new int[k];
        for (int i = 0; i < k; i++) {
            topK[i] = pq.poll();  // 取出堆顶元素
        }

        return topK;
    }

    // 桶排序 On
    public int[] topKFrequent3(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //桶排序
        //频率作为数组下标，数组长度是给定数组长度+1，list[nums.length]这组数都相同；
        List<Integer>[] list = new List[nums.length + 1];
        for (int key : map.keySet()) {
            // key表示元素出现频率，相同频率放到一个list中；
            int freq = map.get(key);
            if (list[freq] == null) list[freq] = new ArrayList();
            list[freq].add(key);
        }
        int[] res = new int[k];
        int index = 0;
        // 倒序遍历list频率数组，只遍历到k
        for (int i = nums.length; i > 0; i--) {
            if (list[i] == null) continue;
            for (int n : list[i]) {
                res[index++] = n;
                if (index == k) return res;
            }
        }
        return res;
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