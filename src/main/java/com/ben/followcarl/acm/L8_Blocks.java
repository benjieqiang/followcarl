package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-15  20:47
 * @Description: 小明很喜欢玩积木。一天，他把许多积木块组成了好多高度不同的堆，每一堆都是一个摞一个的形式。
 * 然而此时，他又想把这些积木堆变成高度相同的。但是他很懒，他想移动最少的积木块来实现这一目标，你能帮助他吗？
 * 输入:
 * 6
 * 5 2 4 1 7 5
 * 0
 *
 * 输出
 * 5
 * @Version: 1.0
 */
import java.util.Scanner;
import java.util.ArrayList;

public class L8_Blocks {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            ArrayList<Integer> list = new ArrayList<>(); // 不止一次输入, 所以放到循环里面;
            int sum = 0;
            int count = in.nextInt();
            if (count == 0) break;
            for(int i = 0; i < count; i++) {
                int num = in.nextInt();
                sum += num;
                list.add(num);
            }

            int avg = sum / count;
            int res = 0;
            for (int num : list) {
                if (num > avg) res += num - avg;
            }
            System.out.println(res);
            System.out.println();
        }
    }
}
