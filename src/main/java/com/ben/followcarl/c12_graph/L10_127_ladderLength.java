package com.ben.followcarl.c12_graph;

import org.junit.Test;

import java.util.*;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-10  21:26
 * @Description: 单词接龙
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 * <p>
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典 wordList 中的单词。
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0。
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
 * <p>
 * 2. 起点和终点的最短路径长度
 * @Version: 1.0
 */
public class L10_127_ladderLength {
    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            HashSet<String> wordSet = new HashSet<>(wordList); //转换为hashset 加快速度
            if (wordSet.size() == 0 || !wordSet.contains(endWord)) {  //特殊情况判断
                return 0;
            }
            Queue<String> queue = new LinkedList<>(); //bfs 队列
            queue.add(beginWord);
            Map<String, Integer> map = new HashMap<>(); //记录单词对应路径长度
            map.put(beginWord, 1);

            while (!queue.isEmpty()) {
                String word = queue.remove(); //取出队头单词
                int path = map.get(word); //获取到该单词的路径长度
                for (int i = 0; i < word.length(); i++) { //遍历单词的每个字符
                    char[] chars = word.toCharArray(); //将单词转换为char array，方便替换
                    for (char k = 'a'; k <= 'z'; k++) { //从'a' 到 'z' 遍历替换
                        chars[i] = k; //替换第i个字符
                        String newWord = String.valueOf(chars); //得到新的字符串
                        if (newWord.equals(endWord)) {  //如果新的字符串值与endWord一致，返回当前长度+1
                            return path + 1;
                        }
                        // 如果新单词在set中，但是没有访问过
                        if (wordSet.contains(newWord) && !map.containsKey(newWord)) {
                            map.put(newWord, path + 1); //记录单词对应的路径长度
                            queue.add(newWord);//加入队尾
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
