package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-15  23:04
 * @Description:
 * 小明发现和小宇有共同祖先！现在小明想知道小宇是他的长辈，晚辈，还是兄弟。
 *
 * @Version: 1.0
 */
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class L11_Ancestor {
    static Map<Integer, Integer> map = new HashMap();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                int a = in.nextInt();
                int b = in.nextInt();
                map.put(a, b);
            }
            int xiaoming = 1;
            int count1 = 0;
            while (map.containsKey(xiaoming)) {
                xiaoming = map.get(xiaoming);
                count1++;
            }
            int xiaoyu = 2;
            int count2 = 0;
            while (map.containsKey(xiaoyu)) {
                xiaoyu = map.get(xiaoyu);
                count2++;
            }
            // 晚辈在map里存的辈分多;
            if (count1 < count2) {
                System.out.println("You are my younger");
            } else if (count1 > count2){
                System.out.println("You are my elder");
            } else {
                System.out.println("You are my brother");
            }
        }
    }
}
