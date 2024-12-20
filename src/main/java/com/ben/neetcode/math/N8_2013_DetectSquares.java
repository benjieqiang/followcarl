package com.ben.neetcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-19  12:21
 * @Description: TODO
 * @Version: 1.0
 */
public class N8_2013_DetectSquares {
    class DetectSquares {
        // {x: {y: (x,y)count}}
        //  1: {2: 1, 3: 1},  // 表示 (1, 2) 和 (1, 3) 各出现 1 次。
        Map<Integer, Map<Integer, Integer>> row2col;
        public DetectSquares() {
            row2col = new HashMap<>();
        }

        public void add(int[] point) {
            int x = point[0];
            int y = point[1];
            Map<Integer, Integer> countMap = row2col.getOrDefault(x, new HashMap<>());
            countMap.put(y, countMap.getOrDefault(y, 0) + 1);
            row2col.put(x, countMap);
        }

        public int count(int[] point) {
            int x = point[0];
            int y = point[1];
            int res = 0;
            // 获取横坐标为x有多少个坐标（x, diffy）.
            Map<Integer, Integer> countMap = row2col.getOrDefault(x, new HashMap<>());
            // 遍历和当前点 point 在同一竖线上但不同位置的点。
            for (int ny : countMap.keySet()) {
                if (ny == y) continue; // 跳过当前坐标；
                int c1 = countMap.get(ny); // 该坐标的数量；
                // square，获取两个坐标(x, y), (x, ny) 之间的差值作为正方形一条边
                // (x,y) (nx, y)
                // (x,ny) (nx, ny)
                // len 正负没事，取决于，(x,ny) 在当前坐标上方还是下方。nx可以取x+len/x-len.
                int len = y - ny;
                int[] nums = new int[] {x + len, x - len};
                for (int nx : nums) {
                    Map<Integer, Integer> temp = row2col.getOrDefault(nx, new HashMap<>());
                    int c2 = temp.getOrDefault(y, 0); // (nx, y) 是否存在
                    int c3 = temp.getOrDefault(ny, 0); // （nx, ny) 是否存在
                    res += c1 * c2 * c3;
                }
            }

            return res;
        }
    }

}
