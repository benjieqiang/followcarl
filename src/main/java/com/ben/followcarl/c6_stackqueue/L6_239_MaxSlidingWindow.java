package com.ben.followcarl.c6_stackqueue;

import org.junit.Test;

import java.util.*;

class L6_239_MaxSlidingWindowTest {
    // 接收终端挨个输入的数字，放到数组里；
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1,3,-1,-3,5,3,6,7
        int[] res = new int[8];
        int k = 3;
        for (int i = 0; i < res.length; i++) {
            int i1 = scanner.nextInt();
            res[i] = i1;
        }
        System.out.println(Arrays.toString(res));

    }
}

class MyOwnQueue {
    Deque<Integer> deque = new LinkedList<>();

    //弹出元素时，比较当前要弹出的数值是否等于队列出口的数值，如果相等则弹出
    //同时判断队列当前是否为空
    void poll(int val) {
        if (!deque.isEmpty() && val == deque.peek()) {
            deque.poll();
        }
    }

    //添加元素时，如果要添加的元素大于入口处的元素，就将入口元素弹出
    //保证队列元素单调递减
    //比如此时队列元素3,1，2将要入队，比1大，所以1弹出，此时队列：3,2
    void add(int val) {
        while (!deque.isEmpty() && val > deque.getLast()) {
            deque.removeLast();
        }
        deque.add(val);
    }

    //队列队顶元素始终为最大值
    int peek() {
        return deque.peek();
    }
}

public class L6_239_MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }
        int len = nums.length - k + 1;
        //存放结果元素的数组
        int[] res = new int[len];
        int num = 0;
        //自定义队列
        MyOwnQueue myQueue = new MyOwnQueue();
        //先将前k的元素放入队列
        for (int i = 0; i < k; i++) {
            myQueue.add(nums[i]);
        }
        res[num++] = myQueue.peek();
        for (int i = k; i < nums.length; i++) {
            //滑动窗口移除最前面的元素，移除是判断该元素是否放入队列
            myQueue.poll(nums[i - k]);
            //滑动窗口加入最后面的元素
            myQueue.add(nums[i]);
            //记录对应的最大值
            res[num++] = myQueue.peek();
        }
        return res;
    }

    /**
     * @description 思路：
     * 1. 单调队列：所有队列的元素按照递增或递减顺序的队列，队列头是最小或最大的元素。
     * 窗口向右滑动时，把最左边的元素删除，在右边再填一个新元素进来，这种是双端队列，
     * 得到的窗口要找到队列里的最大值；
     * 思路：维护一个单调递减的队列，单调队列存放的值是下标，下标是单调自增的，但是对应的值是单调递减的。队列头是最大值的下标；
     * deque： isEmpty; add
     * @author benjieqiang
     * @date 2023/7/26 11:10 PM
     * 2023.10.23
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        // 原数组为空或者只有一组元素，直接返回
        if (nums == null || nums.length == 1) return nums;

        Deque<Integer> deque = new LinkedList<>();
        // 最终存放结果的数组：长度举例说明：比如[1], k = 1, 结果是[1]; 所以要加1；
        int[] res = new int[nums.length - k + 1];


        for (int right = 0; right < nums.length; right++) {
            // 如果队列不为空，且当前元素大于等于队尾元素，则移除队尾元素，小弟都撤走，直到遇到新的老大停下来或队列都空了停止；
            // nums[right]: 当前元素，nums[deque.getLast()]: 队尾元素
            while (!deque.isEmpty() && nums[right] > nums[deque.getLast()]) {
                deque.removeLast();
            }
            deque.addLast(right); // 队列存入当前元素（最大元素）的下标；

            // 判断队首元素是否在窗口内；队首元素下标小于窗口的left时，队首元素不在窗口内，删除队首元素；
            int left = right - k + 1;
            if (deque.getFirst() < left) {
                deque.removeFirst();
            }

            // 窗口形成，收获结果：当窗口右边界right + 1大于等于窗口长度（k）时，此时队首元素就是该窗口最大值，加入结果集；
            if (right + 1 >= k) {
                res[left] = nums[deque.getFirst()];
            }
        }
        return res;
    }

    @Test
    public void testMaxSliding() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] ints = maxSlidingWindow2(nums, k);
        System.out.println(Arrays.toString(ints));
    }

    /*
    {1, 3, -1, -3, 5, 3, 6, 7}
    right => [0, nums.length - 1];
    双端队列，单调递减，代码里面存放下标, 解释过程存放实际的值；
    1. right = 0, nums[0] = 1, 队列为空，入队，得到{1},
    2. right = 1, nums[1] = 3, 队列不为空，队尾元素1 < 3,弹出队尾值1，3入队, {3}
    3. right = 2, nums[2] = -1, 队尾值3 > -1， 入队得到 {3,-1}, 窗口l = 0， r = 2，res = [3];
    4. right = 3, nums[3] = -3, 队尾-1 > -3, 入队{3,-1,-3}, 窗口l = 1, r = 3, res = [3,3]
     */
}