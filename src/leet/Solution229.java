package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution229 {
    public static void main(String[] args){
        int[] a = {1,2};
        System.out.println(majorityElement(a));
    }

    public static List<Integer> majorityElement(int[] nums) {
        Arrays.sort(nums);
        List<Integer> out = new ArrayList<>();
        if (nums.length == 0)return out;
        if (nums.length == 1){
            out.add(nums[0]);
            return out;
        }

        int window = nums.length/3;
        int last_num = nums[0];
        int i = 1;
        while (i < nums.length){
            int tmp = window-1;
            while (i <nums.length && tmp > -1) {
                if (nums[i] == last_num){
                    tmp--;
                    last_num = nums[i];
                    i++;
                }
                else {
                    last_num = nums[i];
                    i++;
                    break;
                }
            }
            if (tmp == -1){
                out.add(last_num);
                while (i < nums.length && nums[i] == last_num){
                    i++;
                }
                if (i < nums.length) {
                    last_num = nums[i];
                    if (window != 0) i++;
                }
            }
        }
        return out;
    }
}
