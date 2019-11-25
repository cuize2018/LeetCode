package leet;


/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class Solution4 {

   public static void  main(String[] args){

        int[] num1 = {1,2};
        int[] num2 = {3,4};

        Ans4_2 ans = new Ans4_2();
        double middle = ans.findMedianSortedArrays(num1, num2);
        System.out.println(middle);
    }
}

class Ans4_2{
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        if (m > n){
            return findMedianSortedArrays(nums2, nums1);
        }
        int min_i = 0;
        int max_i = m;

        while (min_i <= max_i){
            int i = (min_i + max_i)/2;
            int j = (m + n + 1)/2 - i;

            if (j != 0 && i != m && nums2[j-1] > nums1[i]){
                min_i = i + 1;
            }
            else if (i != 0 && j != n && nums1[i-1] > nums2[j]){
                max_i = i - 1;
            }
            else {
                int max_left = 0;
                if (i == 0){
                    max_left = nums2[j-1];
                }
                else if (j == 0){
                    max_left = nums1[i-1];
                }
                else{
                    max_left = Math.max(nums1[i-1], nums2[j-1]);
                }

                if ((m + n) % 2 == 1){
                    return max_left;
                }

                int min_right = 0;
                if (i == m){
                    min_right = nums2[j];
                }
                else if (j == n){
                    min_right = nums1[i];
                }
                else{
                    min_right = Math.min(nums1[i], nums2[j]);
                }
                return (max_left + min_right)/2.0;
            }
        }
        return 0;
    }
}
