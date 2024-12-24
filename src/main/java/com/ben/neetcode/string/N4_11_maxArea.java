package com.ben.neetcode.string;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-24  15:08
 * @Description: 找数组两点之间组成的最大面积
 * @Version: 1.0
 */
public class N4_11_maxArea {
    // brute force, O(n2), 遍历依次找以每个高度为边的最大面积， 面积 = 两点距离 * 短板；
    public int maxArea2(int[] height) {
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return res;
    }
    /**
     * @param height:
     * @return int
     * @description 通过移动较短的板来尝试增加面积，因为移动较长的板不会对面积产生积极影响。
     * 1. 向内移动短板，面积可能会增大，因为短板可能变大。
     * 2. 向内移动长板，长板不管咋变，短板是固定的，两个板之间距离变小，所以面积变小。
     * @author benjieqiang
     * @date 2024/12/24 3:10 PM
     */

    public int maxArea(int[] height) {
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            if (height[left] < height[right]) {
                res = Math.max(res, (right - left) * height[left]);
                left++;
            } else {
                res = Math.max(res, (right - left) * height[right]);
                right--;
            }
        }
        return res;
    }
}
