package com.ben.neetcode.arrayshashing;

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
public class N4_49_groupAnagrams {

    // 时间复杂度：O(nklogk)，其中 n 是 strs 中的字符串的数量，k 是 strs 中的字符串的的最大长度。需要遍历 n 个字符串，对于每个字符串，
    // 需要 O(klogk) 的时间进行排序以及 O(1) 的时间更新哈希表，因此总时间复杂度是 O(nklogk)。
    // 空间复杂度：O(nk)，其中 n 是 strs 中的字符串的数量，k 是 strs 中的字符串的的最大长度。需要用哈希表存储全部字符串。
    // char[] O1, key是o(k) k最大长度，map中value是一个List<String> 占用空间是O(n * k) n个字符串，一个字符串最大占用k空间 . => O(nk)
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hashMap = new HashMap<>();
        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sortedS = new String(charArray);
            hashMap.putIfAbsent(sortedS, new ArrayList<>());
            hashMap.get(sortedS).add(s);
        }
        // 最终的结果都在map的value中
        return new ArrayList(hashMap.values());
    }

    //Time complexity:
    //O(m∗n) 遍历m次，里面n是最长字符串长度，遍历n次要统计字符词频，最后o(26) = O(1)生成字符串，O(1) map put
    //Space complexity:
    //O(m)：
    //m is the number of strings and
    //n is the length of the longest string.
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        for (String s : strs) {
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            String key = Arrays.toString(count);
            res.putIfAbsent(key, new ArrayList<>());
            res.get(key).add(s);
        }
        return new ArrayList<>(res.values());
    }

    @Test
    public void testAnagrams() {
        String[] res = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams2(res));
    }
}
