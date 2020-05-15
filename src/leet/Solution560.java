package leet;

import java.util.*;

public class Solution560 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;
        System.out.println(subarraySum(nums, k));
    }

    public static int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int dp = 0;

        int count = 0;
        for (int i = 0; i < len; i++) {
            dp = nums[i];
            if (dp == k) count++;
            for (int j = i + 1; j < len; j++) {
                dp = dp + nums[j];
                if (dp == k) count++;
            }
        }
        return count;
    }

    /**
     * 我们先来分析一下， 如果区间[left, right]中数字之和等于k,
     * 那么是不是会有sum(right) - sum(left)==k，这样就变成了sum(right) - k == sum(left)
     * <p>
     * 我们用一个字典当做累积和, 对于nums=[1,1,-2,2,4,-2,2], k=2 来说，
     * 累积和[1,2,0,2,6,4,6]，"这里我们用字典dict记录一下每个累积和出现的次数"，考虑到了累积和刚好等于k的情况，将dict初始化为{0:1}
     * <p>
     * 下面我们来遍历一下nums，看看具体情况：
     * SUM=1, SUM-k=-1没有出现在dict中，count=0, dict={0:1, 1:1}
     * SUM=2, SUM-k=0出现在了dict中，count=1, dict={0:1,1:1,2:1}
     * SUM=0, SUM-k=-2没有出现在dict总，count=1, dict={0:2,1:1,2:1}
     * SUM=2, SUM-k=0出现在了dict中，count=3, dict={0:2,1:1,2:2}
     * SUM=6, SUM-k=4没有出现在dict中，count=3, dict={0:2,1:1,2:2,6:1}
     * SUM=4, SUM-k=2出现在了dict中，count=5, dict={0:2,1:1,2:2,6:1,4:1}
     * SUM=6, SUM-k=4出现在了dict中，count=6, dict={0:2,1:1,2:2,6:2,4:1}
     * 所以count最后为6!!
     * 其实分析下来，我们发现dict的作用就是记录sum(left)出现的次数
     * 这种方法秩序要遍历一遍数组就行!!
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum3(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            //sum[j] - sum[i] = k --> sum[j] - k = sum[i]
            if (map.containsKey(sum - k)) {
                //此时含义为sum[i] + k = sum[j], 所以我们需要sum[i]的次数，即sum[j] - k的次数
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;

    }


    public static int subarraySum4(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int res = 0;
        int sumRight = 0;

        for (int i = 0; i < nums.length; i++) {
            sumRight += nums[i];
            int sumLeft = sumRight - k;
            if (map.containsKey(sumLeft)) {
                res += map.get(sumLeft);
            }
            map.put(sumRight, map.getOrDefault(sumRight, 0) + 1);
        }
        return res;
    }
}
