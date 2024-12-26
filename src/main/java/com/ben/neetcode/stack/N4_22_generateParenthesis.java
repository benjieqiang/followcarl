package com.ben.neetcode.stack;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-26  18:20
 * @Description: TODO
 * @Version: 1.0
 */
public class N4_22_generateParenthesis {
    List<String> res = new LinkedList<>();

    public List<String> generateParenthesis(int n) {
        if (n <= 0) return null;
        dfs("", 0, 0, n);
        return res;
    }
    private void dfs(String path, int left, int right, int n) {
        if (left > n || right > left) return;
        if (path.length() == 2 * n) {
            res.add(path);
            return;
        }
        dfs(path + "(", left + 1, right, n);
        dfs(path + ")", left, right + 1, n);
    }
}
