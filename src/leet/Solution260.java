package leet;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.Arrays;

public class Solution260 {
    public static void main(String[] args){
        int[] a = {0,1,1,2};
        System.out.println( Arrays.toString(singleNumber(a)));
    }

    public static int[] singleNumber(int[] nums) {
        int[] out = new int[2];

        Arrays.sort(nums);
        int idx = 0;
        int k = 0;

        while (idx < nums.length){
            if (idx == nums.length-1){
                out[k] = nums[idx];
                return out;
            }

            if (nums[idx] != nums[idx+1]){
                if (idx == nums.length-2){
                    out[0] = nums[idx];
                    out[1] = nums[idx+1];
                    return out;
                }

                if (idx < nums.length-2 && nums[idx+1]==nums[idx+2]){
                    out[k] = nums[idx];k++;
                    idx++;
                }
                else if (idx < nums.length-2 && nums[idx+1]!=nums[idx+2]){
                    out[0] = nums[idx];
                    out[1] = nums[idx+1];
                    return out;
                }
            }
            else {
                idx += 2;
            }
        }

        return out;
    }
}
