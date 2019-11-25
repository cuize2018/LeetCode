package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution228 {

    public static void main(String[] args) {
        int[] a = {0,2,3,4,6,8,9,100};
        System.out.println(summaryRanges(a));
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> out = new ArrayList<>();
        int start =  0;
        int end = 0;

        while (start <= end && end < nums.length){
            if (end < nums.length-1 && nums[end]+1 == nums[end+1]){
                end++;
            }
            else {
                String tmp;
                if (end != start) {
                    tmp = nums[start] + "->" + nums[end];
                }
                else {
                    tmp = String.valueOf(nums[start]);
                }
                out.add(tmp);

                end++;
                start = end;
            }
        }
        return out;
    }
}
