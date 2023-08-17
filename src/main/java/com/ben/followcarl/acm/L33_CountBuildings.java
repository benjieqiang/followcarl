package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-17  21:19
 * @Description:
 * 小明在周末的时候和他的小伙伴来到大城市逛街，一条步行街上有很多高楼，共有 n 座高楼排成一行。
 *
 * 小明从第一栋一直走到了最后一栋，小明从来都没有见到这么多的楼，所以他想知道他在每栋楼的位置处能看到多少栋楼呢？
 * （当前面的楼的高度大于等于后面的楼时，后面的楼将被挡住）
 * 输入:
 * 输入一个数组，表示每栋楼的高度。
 * [5,3,8,3,2,5]
 * 输出:
 * 输出一个数组，表示在每栋楼对应的位置上能看到多少栋楼。
 * [3,3,5,4,4,4]
 * @Version: 1.0
 */
import java.util.*;

public class L33_CountBuildings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String buildings = sc.nextLine();
            int[] heights = parseToArray(buildings);
            int n = heights.length;
            int[] visibleCounts = new int[n];
            Arrays.fill(visibleCounts, 1);
            calculateVisibleCounts(heights, visibleCounts);
            // 因为toString时 append的是", ", 所以要replaceAll(" ", "")
            System.out.println(Arrays.toString(visibleCounts).replaceAll(" ", ""));
        }
    }

    private static void calculateVisibleCounts(int[] heights, int[] visibleCounts) {
        int n = heights.length;
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        for (int i = 0; i < n; i++) {
            visibleCounts[i] += stack1.size();
            while (!stack1.empty() && stack1.peek() <= heights[i]) {
                stack1.pop();
            }
            stack1.push(heights[i]);
        }

        for (int i = n - 1; i >= 0; i--) {
            visibleCounts[i] += stack2.size();
            while (!stack2.empty() && stack2.peek() <= heights[i]) {
                stack2.pop();
            }
            stack2.push(heights[i]);
        }
    }

    private static int[] parseToArray(String buildings) {
        String[] strings = buildings.substring(1, buildings.length() - 1).split(",");
        int[] res = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            res[i] = Integer.parseInt(strings[i].trim());
        }
        return res;
    }
}
