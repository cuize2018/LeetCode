package leet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution217 {
    public static void main(String[] args){

    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num: nums){
            if (set.contains(num)){
                return true;
            }
            set.add(num);
        }
        return false;
    }

    public static boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0;i<nums.length-1;i++){
            if (nums[i] == nums[i+1]){
                return true;
            }
        }
        return false;
    }
}
