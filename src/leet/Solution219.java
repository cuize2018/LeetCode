package leet;

import org.omg.CORBA.MARSHAL;

import java.time.OffsetDateTime;
import java.util.*;

public class Solution219 {
    public static void main(String[] args){
        int[] a = {1,0,1,1};
        int k = 1;
        System.out.println(containsNearbyDuplicate(a,k));
    }

    /**
     * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，
     * 并且 i 和 j 的差的绝对值 <= k。
     *
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0;i<nums.length;i++){
            if (!map.containsKey(nums[i])){
                List<Integer> one = new ArrayList<>();
                one.add(i);
                map.put(nums[i], one);
            }
            else {
                List<Integer> tmp = map.get(nums[i]);
                for (int j = 0;j<tmp.size();j++){
                    if (Math.abs(i - tmp.get(j)) <= k){
                        return true;
                    }
                }
                map.get(nums[i]).add(i);
            }
        }
        return false;

    }

    public static boolean containsNearbyDuplicate2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0;i<nums.length;i++){
            if (!map.containsKey(nums[i])){
                map.put(nums[i], i);
            }
            else {
                if (Math.abs(i - map.get(nums[i])) <= k){
                    return true;
                }
                map.put(nums[i], i);
            }
        }
        return false;

    }
}
