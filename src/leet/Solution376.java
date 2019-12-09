package leet;

public class Solution376 {
    public static void main(String[] args) {
        int[] a = {1,17,5,10,13,15,10,5,16,8};
        int[] b = {0,0,0,0,0,0};
        int[] c = {1,1,7,4,9,2,5};
        int[] d = {56,148,74,69,95,84,172,49,182,164,20,171,20,5,102,176,125,103,95,60,52,186,181,191,193,87,134,129,162,8,90,76,119,92,61,148,144,112,119,197,25,149,32,80,15,148,25,30,172,29,91,38,98,9,0,103,0,12,36,197,127,158,139,27,157,95,61,39,45,51,30,105,163,5,27,196,148,137,114,198,94,161,44,185,11,131,96,117,44,102,5,61,1,149,52,70,131,4,173,189,124,96,8,191,161,75,59,104,111,72,100,78,156,184,55,146,130,63,118,113,175,128,138,188,88,173,174,115,177,78,50,111,131,138,107,173,152,198,195,9,23,65,57,24,102,35,91,141,90,186,104,97,134,139,99,175,93,159,101,128,48,144,194,176,60,25,103,177,52,6,22,183,76,129,3,6,81,162,108,111,49,96,190,195,28,74,160,48,91,36,160,174,190,192,155,96,151,123,128,17};
        System.out.println(wiggleMaxLength(d));
    }

    public static int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)return nums.length;

        int n = nums.length;
        int[] dp_len = new int[n];
        int[] dp_last_differnece = new int[n];

        dp_len[0] = 1;
        dp_last_differnece[0] = nums[0];

        if (nums[1] == nums[0]){
            dp_len[1] = 1;
            dp_last_differnece[1] = nums[1];
        }
        else {
            dp_len[1] = 2;
            dp_last_differnece[1] = nums[1] - nums[0];
        }

        int max = dp_len[1];
        for (int i = 2;i<nums.length;i++){
            int max_len = 1;
            int max_difference = 0;
            for (int j = i-1;j>=0;j--){
                int tmp = nums[i] - nums[j];
                if (dp_last_differnece[j]==nums[j] && dp_len[j] == 1){
                    if (tmp != 0) {
                        max_len = dp_len[j] + 1;
                        max_difference = tmp;
                    }
                }
                else {
                    if (tmp*dp_last_differnece[j] < 0) {
                        max_len = dp_len[j] + 1;
                        max_difference = tmp;
                        break;
                    }
                }
            }
            dp_len[i] = max_len;
            dp_last_differnece[i] = max_difference;
            if (dp_len[i] > max)max = dp_len[i];
        }

        return max;
    }

    public static int wiggleMaxLength2(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i],down[j] + 1);
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i],up[j] + 1);
                }
            }
        }
        return 1 + Math.max(down[nums.length - 1], up[nums.length - 1]);
    }
}
