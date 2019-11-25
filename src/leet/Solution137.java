package leet;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Arrays;
import java.util.HashMap;

public class Solution137 {
    public static void main(String[] args){
        int[] a = {2,2,3,2};
        System.out.println(singleNumber2(a));
    }

    public static int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int  out = 0;
        for (int num : nums){
            map.put(num,map.getOrDefault(num, 0)+1);
            //map.merge(num, 1, Integer::sum);
        }

//        map.forEach((k, v)->{
//            if (v == 1){
//             out[0] = k;
//            }
//        });
//        return out[0];

        for (Integer key: map.keySet()){
            if (map.get(key) == 1){
                out = key;
                break;
            }
        }
        return out;
    }

    public static int singleNumber2(int[] nums) {
        Arrays.sort(nums);
        int out = 0;
        boolean getflag = false;
        for (int i = 0;i<nums.length-3;i+=3){
            if (nums[i] != nums[i+1] || nums[i+1] != nums[i+2]){
                if (nums[i]!=nums[i+1]){
                    out = nums[i];
                    getflag = true;
                    break;

                }
                if (nums[i+1] != nums[i+2]){
                    out = nums[i+2]; getflag = true;
                    break;
                }
            }
        }
        if (!getflag){
            out = nums[nums.length-1];
        }

        return out;

    }
}
