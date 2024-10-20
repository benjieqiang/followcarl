package com.ben.followcarl.c12_graph;

import org.junit.Test;

import java.util.*;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-10  21:26
 * @Description: 单词接龙
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 * 示例 1：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * 示例 2：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 * <p>
 * 思路:
 * 1. 图中的线是如何连在一起的
 * 2. 起点和终点的最短路径长度: 这里无向图求最短路，广搜最为合适，广搜只要搜到了终点，那么一定是最短的路径。
 * 因为广搜就是以起点中心向四周扩散的搜索。
 * <p>
 * 本题是一个无向图，需要用标记位，标记着节点是否走过，否则就会死循环！
 * <p>
 * 1. 使用set来检查字符串是否出现在字符串集合里更快一些，所以给的list集合先转成set集合；
 * 2. 判断list不为空，且尾单词均出现在set中；
 * 3. bfs逻辑：
 * 1. 先加入首单词，使用map标记该单词的最远路径；
 * 2.
 * @Version: 1.0
 */
public class L10_127_ladderLength {
    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            HashSet<String> wordSet = new HashSet<>(wordList); //转换为hashset 加快速度
            if (wordSet.size() == 0 || !wordSet.contains(endWord)) {  //特殊情况判断
                return 0;
            }

            Deque<String> queue = new LinkedList<>();
            queue.add(beginWord);

            Map<String, Integer> map = new HashMap<>(); //记录单词对应路径长度
            map.put(beginWord, 1);

            while (!queue.isEmpty()) {
                String word = queue.remove(); //取出队头单词
                int path = map.get(word); //获取到该单词的路径长度
                for (int i = 0; i < word.length(); i++) { // 遍历转换该单词的每一个字符，找最短；
                    char[] chars = word.toCharArray(); //string 转换为char[]，方便替换 // 重要：放在for循环下面，每次都是从原始的 word 进行替换，
                    for (char k = 'a'; k <= 'z'; k++) { //从'a' 到 'z' 遍历替换
                        chars[i] = k; //替换第i个字符
                        String newWord = String.valueOf(chars); // char[]转字符串
                        if (newWord.equals(endWord)) {  //如果新的字符串值与endWord一致，返回当前长度+1
                            System.out.println(map);
                            return path + 1;
                        }
                        // 如果新单词在set集合中，但是没有访问过，访问过会存在map中；
                        if (wordSet.contains(newWord) && !map.containsKey(newWord)) {
                            map.put(newWord, path + 1); //记录单词对应的路径长度
                            queue.add(newWord); // 加入新单词；
                        }
                    }
                }
            }
            return 0; //未找到
        }
    }

    @Test
    public void testLadderLength() {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        System.out.println(new Solution().ladderLength(beginWord, endWord, wordList));
    }
}
