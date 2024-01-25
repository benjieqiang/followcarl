package com.ben.followcarl.c3_hashmap;

import org.junit.Test;

import java.util.*;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-11  21:03
 * @Description: 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * <p>
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Version: 1.0
 */
public class L2_49_groupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hashMap = new HashMap<>();
        for (String str : strs) {
            char[] ch = str.toCharArray();
            Arrays.sort(ch); // 先排序之后按照string类型存入key，
            String temp = new String(ch);
            // key存在就加上，不存在就new一个新的list加上；
            List<String> list = hashMap.getOrDefault(temp, new ArrayList<String>());
            list.add(str);
            // 最后放入到map中；
            hashMap.put(temp, list);
        }
        // 最终的结果都在map的value中
        return new ArrayList(hashMap.values());
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null) return res;
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String key = new String(ch);
            List<String> list = map.get(key) == null ? new ArrayList<>() : map.get(key);
            list.add(str);
            map.put(key, list);
        }
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }

        return res;
    }

    @Test
    public void testAnagrams() {
        String[] res = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams2(res));
    }
}
