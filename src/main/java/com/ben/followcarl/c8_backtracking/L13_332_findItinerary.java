package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.*;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-03  20:07
 * @Description: 给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。
 * <p>
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。
 * <p>
 * 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
 * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。
 * <p>
 * 如果存在多种有效的行程，请你按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
 * 所有的机场都用三个大写字母表示（机场代码）。
 * 假定所有机票至少存在一种合理的行程。
 * 所有的机票必须都用一次 且 只能用一次。
 * <p>
 * 输入：[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
 * 递归三部曲:
 * 1. res存放结果集, path存放有效的路径; 返回值为boolean,只要有符合的行程,直接返回,不需要再遍历,
 * 入参: tickets, used数组记录已经遍历过的元素，长度就是tickets.size()。
 * 返回值：因为我们只需要找到一个行程，就是在树形结构中唯一的一条通向叶子节点的路线
 * 2. 当path的长度等于所给票的长度加1, （比如4个航班的话，只要行程机票是5）收获结果,返回true,找到一个行程,直接返回;
 * 3. 单层递归逻辑: 首先path从JFK起飞,
 * 首先for循环从0开始遍历到所给集合结束;
 * 当前元素: tickets.get(i).get(1)
 * 如果当前飞机场去过了used数组进行判断, 直接跳过
 * 如果此时path的最后一个元素和要开始飞机场tickets.get(i).get(0)不一致,则跳过; 因为path集合始终存放的是要去的地方,比如刚开始得从JFK开始, 下一把存入MUC之后, 那就得从 LHR开始找机场;
 * <p>
 * 加入结果集;
 * 该飞机场去过了,标记一下;
 * 如果找到一个集合就返回;
 * 回退,移除最后一个元素, 抹掉去过该机场的痕迹;
 * <p>
 * 时间复杂度：
 * Collections.sort(tickets, (a, b) -> a.get(1).compareTo(b.get(1))); 这一行代码使用 Java 的 Collections 类对机票列表进行排序。排序的时间复杂度取决于排序算法，通常为 O(n log n)，其中 n 是机票的数量。
 * backtracking(tickets) 方法中的回溯算法将尝试不同的机票组合。在最坏情况下，它需要尝试所有可能的组合。假设有 n 张机票，最坏情况下的时间复杂度为 O(n!)，即阶乘级别的复杂度。因为每一次尝试都会排除一些不合法的机票，所以实际运行时间可能会比阶乘稍微短一些。
 * 总的时间复杂度可以表示为 O(n log n + n!)。
 * <p>
 * 空间复杂度：
 * List<String> res 和 LinkedList<String> path 分别用于存储结果和当前路径，它们的空间复杂度都与机票数量相关，因此是 O(n)。
 * boolean[] used 用于跟踪机票的使用情况，它的大小与机票数量相同，也是 O(n)。
 * 递归调用 backtracking(tickets) 可能会导致递归栈的深度达到 n 层，因此在最坏情况下，递归调用栈的空间复杂度为 O(n)。
 * 综合考虑，总的空间复杂度是 O(n)。
 * @Version: 1.0
 */
public class L13_332_findItinerary {
    LinkedList<String> res = new LinkedList<>(); //结果集
    LinkedList<String> path = new LinkedList<>(); // 存放有效的飞行行程, 它的长度应该是tickets的长度加1
    boolean find;
    private void backtracking(List<List<String>> tickets, boolean[] used) {
        if (find) return;
        // 如果找到一个完整的行程（即路径长度 path.size() 等于 tickets.size() + 1，即使用完所有机票）
        if (path.size() == tickets.size() + 1) {
            res = new LinkedList<>(path);
            find = true;
            return;
        }
        for (int i = 0; i < tickets.size(); i++) {
            // 树层剪枝，有多张相同的票；
            if (i > 0 && tickets.get(i).get(0).equals(tickets.get(i - 1).get(0))
                    && tickets.get(i).get(1).equals(tickets.get(i - 1).get(1))
                    && used[i - 1] == false)
                continue;

            if (used[i]) continue; // 当前机票已经用过了, 跳过;
            if (!tickets.get(i).get(0).equals(path.getLast())) continue;// 路径最后的机场（已到的机场）跟下一个出发的机场（当前tickets的机场）不匹配,跳过;
            // 将机票的目的地添加到路径中
            path.add(tickets.get(i).get(1));
            used[i] = true;
            backtracking(tickets, used);// 找到第一个就是所求的
            used[i] = false;
            path.removeLast();
        }
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        Collections.sort(tickets, (a, b) -> a.get(1).compareTo(b.get(1))); // 机票列表被按目的地字母序排序，
        path.add("JFK"); // 注意加入JFK;
        boolean[] used = new boolean[tickets.size()];
        backtracking(tickets, used);
        return res;
    }

    @Test
    public void testFindItinerary() {
        // {{"MUC","LHR"},{"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}}
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("MUC", "LHR"));
        tickets.add(Arrays.asList("JFK", "MUC"));
        tickets.add(Arrays.asList("SFO", "SJC"));
        tickets.add(Arrays.asList("LHR", "SFO"));

        System.out.println(findItinerary(tickets));
    }
}
