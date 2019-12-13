package leet;

import java.util.*;

public class Solution398 {
    private int[] nums;
    private Map<Integer, List<Integer>> map;
    public static void main(String[] args) {

    }

    public Solution398(int[] nums) {
        this.nums = nums;
        this.map = new HashMap<>();

        for (int i = 0;i<nums.length;i++){
            if (map.containsKey(nums[i])){
                List<Integer> tmp = map.get(nums[i]);
                tmp.add(i);
                map.put(nums[i], tmp);
            }
            else {
                List<Integer> tmp = new ArrayList<>();tmp.add(i);
                map.put(nums[i], tmp);
            }
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);

        Random random = new Random();
        int rand_idx = random.nextInt(list.size());

        return list.get(rand_idx);
    }
}
