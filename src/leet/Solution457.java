package leet;

import java.util.HashSet;
import java.util.Set;

public class Solution457 {
    public static void main(String[] args) {
        int[] a = {2,-1,1,2,2};
        int[] b = {-1,2};
        int[] c = {-2,1,-1,-2,-2};
        int[] d = {-1,-2,-3,-4,-5};
        int[] e = {1,1,2};
        int[] g = {2,-1,1,2,2};
        int[] h = {2,-3,-9};
        int[] i = {-8,-1,1,8,2};


        System.out.println(circularArrayLoop(i));
    }

    public static boolean circularArrayLoop(int[] nums) {
        int start_index = 0;

        while (start_index < nums.length){
            int mov_index = start_index;
            int dir = nums[start_index] > 0 ?1:-1;
            int cycle_len = 0;

            Set<Integer> set = new HashSet<>();
            while (nums[mov_index] * dir > 0){
                int mov_steps = nums[mov_index];
                int next_mov_index = mov_index + mov_steps;

                if (next_mov_index < 0){
                    if (next_mov_index < -nums.length) {
                        int temp = next_mov_index % nums.length;
                        next_mov_index = nums.length + temp;
                    }
                    else {
                        next_mov_index = nums.length + next_mov_index;
                    }
                }
                else if (next_mov_index >= nums.length){
                    if (next_mov_index >= nums.length*2) {
                        int temp = next_mov_index % nums.length;
                        next_mov_index = mov_index + temp;
                    }
                    else{
                        next_mov_index = next_mov_index - nums.length;
                    }
                }

                cycle_len++;
                if (set.contains(next_mov_index))break;
                set.add(next_mov_index);

                mov_index = next_mov_index;
                if (mov_index == start_index ){
                    if (cycle_len > 1) return true;
                    else break;
                }
            }
            start_index++;
        }
        return false;
    }
}
