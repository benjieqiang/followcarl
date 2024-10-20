package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-20  09:31
 * @Description: 输入：
 * 6
 * hit cog
 * hot
 * dot
 * dog
 * lot
 * log
 * cog
 * <p>
 * 输出： 5
 * 无向图，bfs求最短路径；
 * @Version: 1.0
 */

import java.util.*;

public class L8_ladderLength_bfs {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        sc.nextLine();
        String[] strs = sc.nextLine().split(" ");

        List<String> wordList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            wordList.add(sc.nextLine());
        }

        int res = 0;
        String beginWord = strs[0];
        String endWord = strs[1];

        // bfs
        HashSet<String> set = new HashSet<>(wordList);
        // System.out.println(set);
        // 没有要求endWord必须在wordList中出现。
        // if (!set.contains(endWord)) {
        //     System.out.println(res);
        //     return;
        // }
        Deque<String> queue = new LinkedList<>();
        queue.add(beginWord);

        HashMap<String, Integer> map = new HashMap<>();
        map.put(beginWord, 1); // key: word, value: path

        while (!queue.isEmpty()) {
            String word = queue.remove();
            int path = map.get(word);

            for (int i = 0; i < word.length(); i++) {
                char[] wordChars = word.toCharArray(); // 重要：放在for循环下面，每次都是从原始的 word 进行替换，
                for (char k = 'a'; k <= 'z'; k++) {
                    wordChars[i] = k;

                    String newWord = String.valueOf(wordChars);
                    if (newWord.equals(endWord)) {
                        System.out.println(path + 1);
                        return;
                    }
                    if (set.contains(newWord) && !map.containsKey(newWord)) {
                        queue.add(newWord);
                        map.put(newWord, path + 1);
                    }

                }
            }

        }

        System.out.println(res);
    }
}
