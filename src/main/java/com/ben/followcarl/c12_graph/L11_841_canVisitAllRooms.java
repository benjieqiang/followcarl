package com.ben.followcarl.c12_graph;

import org.junit.Test;

import java.util.*;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-10  21:37
 * @Description: 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 * <p>
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 * <p>
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 * <p>
 * 你可以自由地在房间之间来回走动。
 * <p>
 * 如果能进入每个房间返回 true，否则返回 false。
 * <p>
 * 示例 1：
 * <p>
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释: 我们从 0 号房间开始，拿到钥匙 1。 之后我们去 1 号房间，拿到钥匙 2。 然后我们去 2 号房间，拿到钥匙 3。 最后我们去了 3 号房间。 由于我们能够进入每个房间，我们返回 true。
 * 示例 2：
 * <p>
 * 输入：[[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 * @Version: 1.0
 */
public class L11_841_canVisitAllRooms {
    private void dfs(int key, List<List<Integer>> rooms, boolean[] visited) {
        if (visited[key]) return;
        visited[key] = true;
        for (int k : rooms.get(key)) {
            dfs(k, rooms, visited);
        }
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()]; // 初始化一个visited数组
        dfs(0, rooms, visited);

        //检查是否都访问到了
        for (boolean flag : visited) {
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param rooms:
     * @return boolean
     * @description bfs
     * @author benjieqiang
     * @date 2023/9/10 9:41 PM
     */
    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];    // 用一个 visited 数据记录房间是否被访问
        visited[0] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);           // 第 0 个房间标记为已访问
        while (!queue.isEmpty()) {
            int curKey = queue.poll();
            for (int key : rooms.get(curKey)) {
                if (visited[key]) continue;
                visited[key] = true;
                queue.add(key);
            }
        }
        for (boolean key : visited)
            if (key == false) return false;
        return true;
    }

    /**
     * @param rooms:
     * @return boolean
     * @description bfs2 时间优化
     * @author benjieqiang
     * @date 2023/9/10 9:41 PM
     */
    public boolean canVisitAllRooms3(List<List<Integer>> rooms) {
        int count = 1;      // 用来记录可以被访问的房间数目，因为初始状态下 0 号房间可以被访问，所以置为 1
        boolean[] visited = new boolean[rooms.size()];      // 用一个 visited 数据记录房间是否被访问
        visited[0] = true;          // 第 0 个房间标记为已访问
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int curKey = queue.remove();
            for (int key : rooms.get(curKey)) {
                if (visited[key]) continue;
                ++count;                // 每访问一个访问房间就让 count 加 1
                visited[key] = true;
                queue.add(key);
            }
        }
        return count == rooms.size();       // 如果 count 等于房间数目，表示能进入所有房间，反之不能
    }

    @Test
    public void testRoom() {
        // [[1],[2],[3],[]]
        List<List<Integer>> rooms = new ArrayList<>();

        // 创建第一个房间，并添加元素1
        List<Integer> room1 = new ArrayList<>();
        room1.add(1);
//        room1.add(40);
        rooms.add(room1);

        // 创建第二个房间，并添加元素2
        List<Integer> room2 = new ArrayList<>();
        room2.add(2);
        rooms.add(room2);

        // 创建第三个房间，并添加元素3
        List<Integer> room3 = new ArrayList<>();
        room3.add(3);
        rooms.add(room3);

        // 创建第四个房间，不添加任何元素
        List<Integer> room4 = new ArrayList<>();
        rooms.add(room4);

//        System.out.println(canVisitAllRooms(rooms));
        System.out.println(canVisitAllRooms2(rooms));

    }
}
