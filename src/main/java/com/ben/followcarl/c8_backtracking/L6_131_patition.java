package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-20  21:49
 * @Description:
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例: 输入: "aab" 输出: [ ["aa","b"], ["a","a","b"] ]
 *
 * 例如对于字符串abcdef：
 *
 * 组合问题：选取一个a之后，在bcdef中再去选取第二个，选取b之后在cdef中再选取第三个.....。
 * 切割问题：切割一个a之后，在bcdef中再去切割第二段，切割b之后在cdef中再切割第三段.....。
 *
 * 切割问题就是组合问题:
 * 1. 树形结构的宽度就是字符串长度;
 * 2. 递归深度不知道,
 *
 * 回溯三部曲:
 * 1. void, String s, int index;// index用来决定下一层回溯的for循环从哪里开始,因为第一个元素取了就不能再去取了;
 * 2. index 只要达到字符串结尾的地方s.length就是收获结果的时候, 根据题意, 得有List<List<String>> res,LinkedList<String> path
 * 3. 单层回溯逻辑:
 *    1. 判断index到i的字符串是否是回文,是回文,就利用s.substring(index， i+1)的方法加入结果集中;不是回文就跳过;
 *    2. 执行下一层回溯逻辑; i+1; 首先是一个集合遍历, 其次不需要再拿出来a, 所以i+1;
 *    3. 删除本次加入的字符串;
 * @Version: 1.0
 */
public class L6_131_patition {

    List<List<String>> res = new ArrayList<>();
    LinkedList<String> path = new LinkedList<>();

    private boolean isPalindrome(String s, int left, int right) {
        for (int i = left, j = right; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
    private void backtracking(String s, int startIndex) {
        if (startIndex >= s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isPalindrome(s, startIndex, i)) {
                path.add(s.substring(startIndex, i + 1));
            } else {
                continue;
            }
            backtracking(s, i + 1);
            path.removeLast();
        }
    }
    public List<List<String>> partition(String s) {
        backtracking(s, 0);
        return res;
    }

    @Test
    public void testPartition(){
        String s = "aab";
        List<List<String>> res = partition(s);
        System.out.println(res);
    }
}
