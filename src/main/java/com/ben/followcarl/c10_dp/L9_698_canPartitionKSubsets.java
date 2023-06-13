package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-13  09:34
 * @Description: 使用dp做测试用例过不了
 * @Version: 1.0
 */

/*
class Solution {
public:
    bool canPartitionKSubsets(vector<int>& nums, int k) {
        int sum = accumulate(nums.begin(), nums.end(), 0);
        if (sum % k) return false;
        int target = sum / k;
        sort(nums.begin(), nums.end(), greater<int>());
        if (nums[0] > target) return false;
        vector<int> buckect(k, 0);
        return dfs(nums, 0, target, buckect);
    }

    bool dfs(vector<int>& nums, int idx, int target, vector<int>& buckect) {
        if (idx == nums.size()) {
            return true;
        }

        for (int i = 0; i < buckect.size(); ++i) {
            if (i > 0 && buckect[i] == buckect[i - 1]) continue;  // 神级剪枝
            if (buckect[i] + nums[idx] > target) continue;
            buckect[i] += nums[idx];
            if (dfs(nums, idx + 1, target, buckect)) return true;
            buckect[i] -= nums[idx];
        }
        return false;
    }
};

*/
public class L9_698_canPartitionKSubsets {
    public boolean canPartitionKSubsets1(int[] nums, int k) {
        // int target = sum / k;
        // 背包满：如果能分成子集的话，说明dp[target] = target;
        // 1. dp[j] j大小的背包所能装的最大容量(价值/重量)为 sum / k， nwe dp[sum/k + 1]
        // 2. dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i])
        // 3. 初始化：dp[0] = 0, dp[*] = 0;
        // 4. 遍历顺序：先遍历物品，再倒序遍历背包
        if (nums.length < k) return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k != 0) return false; // 不能被k整除
        int target = sum / k;
        int[] dp = new int[target + 1];

        for(int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                if (dp[target] != target) return false;
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[target] == target;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums.length < k) return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k != 0) return false; // 不能整除
        int target = sum / k;
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        int res = 0; //用来和target比较大小
        return backtracking(nums, used, target, res,0);
    }

    private boolean backtracking(int[] nums, boolean[] used, int target, int sum, int startIndex) {
        if (sum > target) return false;
        if (sum == target) {
            return true;
        }
        for (int i = startIndex; i < nums.length; i++) {
            // 树枝不去重：重复元素可以接着取，二叉树的树枝上；
            // 树层去重 used[i - 1] = false说明上一个元素已经取了，所以避免重复去取，就跳过；
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) continue;
            if (sum > target)
            sum += nums[i];
            used[i] = true;
            backtracking(nums, used, target, sum, i + 1);
            sum -= nums[i];
            used[i] = false;
        }
        return false;
    }

    @Test
    public void testCanPartition() {
//        int[] nums = {2,2,2,2,3,4,5};
        int k = 4;
        int[] nums = {4,3,2,3,5,2,1};
        System.out.println(canPartitionKSubsets(nums, k));
    }
}
