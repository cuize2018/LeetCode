package leet;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution239 {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] out = maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(out));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        int[] out = new int[nums.length - k + 1];

        int[] maxInfo = findMax(nums, 0, k);
        int max = maxInfo[0];
        int maxIdx = maxInfo[1];
        out[0] = max;

        for (int i = 1; i <= nums.length - k; i++) {
            if (maxIdx != i - 1) {  //当最大值不为被挤掉的元素时，仅需比较上一个窗口内的最大值和新加入的值即可
                if (max < nums[i + k - 1]) {
                    max = nums[i + k - 1];
                    maxIdx = i + k - 1;
                }
            } else {
                //当最大值为被挤掉的元素时，需在新窗口内重新寻找最大值
                int[] info = findMax(nums, i, k);
                max = info[0];
                maxIdx = info[1];
            }
            out[i] = max;
        }
        return out;
    }

    /**
     * 找到滑动窗口内的最大值
     *
     * @param nums
     * @param start start表示滑动窗口开始的下标
     * @param k
     * @return
     */
    private static int[] findMax(int[] nums, int start, int k) {
        int max = nums[start];
        int maxIdx = start;
        for (int i = start + 1; i < start + k; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIdx = i;
            }
        }
        return new int[]{max, maxIdx};
    }

    /**
     * 我们可以用一个单调递减队列。单调递减队列添加元素的算法如下。
     *
     * 如果当前元素比队列的最后一个元素大，那么就将最后一个元素出队，重复这步直到当前元素小于队列的最后一个元素或者队列为空。进行下一步。
     *
     * 如果当前元素小于等于队列的最后一个元素或者队列为空，那么就直接将当前元素入队。
     *
     * 按照上边的方法添加元素，队列中的元素就刚好是一个单调递减的序列，而最大值就刚好是队头的元素。
     *
     * 当队列的元素等于窗口的大小的时候，由于添加元素的时候我们进行了出队操作，所以我们不能像解法二那样每次都删除第一个元素，需要先判断一下队头元素是否是我们要删除的元素。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 0) return new int[0];

        Deque<Integer> deque = new ArrayDeque<>();
        int[] out = new int[nums.length - k + 1];
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i >= k) {
                if (deque.getFirst() == nums[i - k]) {
                    deque.removeFirst();
                }
            }

            while (!deque.isEmpty() && deque.getLast() < nums[i]) {
                deque.removeLast();
            }
            deque.add(nums[i]);

            if (i >= k - 1) {
                out[idx] = deque.getFirst();
                idx++;
            }
        }
        return out;

    }
}
