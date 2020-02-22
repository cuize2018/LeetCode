package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution525 {
    public static void main(String[] args) {
        int[] nums = {0, 1};
        System.out.println(findMaxLength(nums));
    }

    /**
     * 这种方法中，我们使用一个变量 count ，用来保存遍历数组过程中到目前为止遇到的 0 和 1 的相对数量。
     * 遇到 1 的时候， count 变量加 1 ，遇到 0 的时候 count 变量减 1 。
     *
     * 我们从头开始遍历数组，在任何时候，如果 count 变成了 0 ，
     * 这表示我们从开头到当前位置遇到的 0 和 1 的数目一样多。
     * 另一个需要注意的是，如果我们在遍历数组的过程汇中遇到了相同的 count 2 次，
     * 这意味着这两个位置之间 0 和 1 的数目一样多。
     *
     * 如果我们记录相同 count最早出现的位置，我们就可以找到有相同数目的 0 和 1 的最长子数组。
     *
     * 使用一个 HashMap map 来保存所有的 (count, index) 对。
     * 对于一个 count ，我们只记录它第一次出现的位置，
     * 后面遇到相同的 count 我们都用这个第一次记录的 index 来计算子数组的长度。
     * 最后取最大值即可
     * @param nums
     * @return
     */
    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);

        int max_len = 0;
        int count = 0;

        for (int i = 0;i<nums.length;i++){
            if (nums[i] == 0)count--;
            else count++;

            if (map.containsKey(count)){
                max_len = Math.max(max_len, i - map.get(count));
            }
            else {
                map.put(count, i);
            }
        }
        return max_len;
    }
}

