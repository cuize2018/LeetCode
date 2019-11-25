package leet;
import java.util.Arrays;

public class Solution34 {
    public static void main(String[] args){
        int[] nums = {5,7,7,7,8,8,8,10};
        int target = 10;
        int[] p = searchRange(nums,target);
        System.out.println(Arrays.toString(p));
    }

    /**
     * 升序查找一次，降序查找一次
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] out = new int[2];
        int low = 0;
        int high = nums.length-1;

        int idx1 = binearySearch(nums,target,low,high);
        while (idx1 > 0 && nums[idx1-1] == target){
            idx1 = binearySearch(nums,target,low, idx1-1);
        }

        int idx2 = binearySearch(nums,target,low,high);
        while (idx2 != nums.length-1 && nums[idx2+1] == target) {
            idx2 = binearySearch(nums, target,idx2+1,high);
        }
        out[0]=idx1;
        out[1] = idx2;
        return out;
    }

    private static int binearySearch(int[] nums, int target,int low_, int high_){
        int high = high_;
        int low = low_;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = nums[mid];
            int cmp = midVal - target;

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        return (-1);  // key not found
    }
}
