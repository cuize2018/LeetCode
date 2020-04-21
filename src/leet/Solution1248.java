package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution1248 {
    public static void main(String[] args) {
        int[] nums = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        int k = 2;
        System.out.println(numberOfSubarrays(nums, k));
    }

    //暴力法
    public static int numberOfSubarrays(int[] nums, int k) {
        int[] dp = new int[nums.length];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (i == j) dp[j] = (nums[j] & 1) == 1 ? 1 : 0;
                else {
                    dp[j] = dp[j - 1] + ((nums[j] & 1) == 1 ? 1 : 0);
                }
                if (dp[j] == k) count++;
                else if (dp[j] > k) break;
            }
        }
        return count;
    }


    public static int numberOfSubarrays2(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int count = 0;
        int res = 0;
        int preEven = 0;

        while (right < nums.length) {
            if (count < k) {
                if ((nums[right] & 1) == 1) count++;
                right++;
            }

            if (count == k) {
                preEven = 0;
                while (count == k) {
                    res++;
                    if ((nums[left] & 1) == 1) count--;
                    left++;
                    preEven++;
                }
            } else {
                res += preEven;
            }
        }
        return res;
    }

    /**
     * 第一步：普遍规律
     * 由于只需要关注奇数的个数，故：
     * 找到每种情况下，最短的符合要求子数组（此时子数组的左右边界一定都是奇数）
     * 向左向右延展，直至碰到奇数停止
     * <p>
     * [例子]
     * 假设原始数组如下所示，要求k=3，很容易得出有以下两种最短的子数组(a),(b)：
     * (a)如下所示。向左向右延展，可以延展到红线标定的位置。此时向左延展有2种可能(不延展，延展1位)，向右有3种可能，故总共有2 * 3=6种可能；
     * (b)如下所示。只能向右延展，可以延展到红线标定的位置。此时向左延展有1种可能(不延展)，向右有2种可能，故总共有1×2=2种可能；
     * nums = [0,1,1,0,1,0,0,1,0];
     * a = [0,|1,1,0,1|,0,0,1,0];
     * b = [0,1,|1,0,1,0,0,1,|0];
     * <p>
     * 综上所述，总共有6+2=8种子数组。
     * <p>
     * 将奇数的下标用数组储存，其中橘色部分为奇数出现的位置，蓝色部分标定数组的边界。则：
     * (a)如下所示，有[1-(-1)]*(7-4)=6种可能，即(arr[1] - arr[0]) * (arr[4] - arr[3])
     * (b)如下所示，有(2-1)*(9-7)=2种可能，即(arr[2] - arr[1]) * (arr[5] - arr[4])
     * arr = [-1||,  1,2,4,7,  ||9]
     * a = [-1||,  "1,2,4," 7,  ||9]
     * b = [-1||,  1, "2,4,7,"  ||9]
     * <p>
     * 第二步：得出结论
     * 对于每种子情形，其可能数为：
     * (arr[i]−arr[i−1])∗(arr[i+k]−arr[i+k−1])
     * 其中，左边界为-1，右边界为arr.length
     *
     * @param nums
     * @param k
     * @return
     */
    public static int numberOfSubarrays3(int[] nums, int k) {
        List<Integer> oddIndex = new ArrayList<>();
        oddIndex.add(-1);
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 1) {
                oddIndex.add(i);
            }
        }
        oddIndex.add(nums.length);
        int count = 0;

        for (int i = 1; i + k < oddIndex.size(); i++) {
            int left = oddIndex.get(i) - oddIndex.get(i - 1);
            int right = oddIndex.get(i + k) - oddIndex.get(i + k - 1);
            count += left * right;
        }
        return count;
    }


}
