package leet;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution473 {
    public List<Integer> nums_list;
    public int possibleSquareSide;
    public int[] sum = new int[4];

    public static void main(String[] args) {

    }

    public boolean makesquare(int[] nums) {
        if (nums.length < 4)return false;

        int sum = 0;
        for (int val : nums)sum += val;

        if (sum % 4 != 0)return false;

        possibleSquareSide = sum / 4;

        nums_list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        nums_list.sort(Collections.reverseOrder());

        return dfs(0);

    }

    public boolean dfs(int index){
        if (index == nums_list.size()){
            return sum[0] == sum[1] && sum[1] == sum[2] && sum[2] == sum[3];
        }

        int val = nums_list.get(index);
        for (int i = 0;i < 4;i++){
            if (sum[i] + val <= possibleSquareSide){
                sum[i] += val;

                boolean flag = dfs(index+1);
                if (flag)return true;

                sum[i] -= val;
            }
        }
        return false;
    }

}
