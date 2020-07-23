package leet;


import java.util.Arrays;
import java.util.Map;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class Solution4 {

    public static void main(String[] args) {
        int[] num1 = {};
        int[] num2 = {3, 4};
        System.out.println(findMedianSortedArrays3(num1,num2));
    }

    // 求第k小数的特殊情况,假设我们要找第 k 小数，我们可以每次循环排除掉 k/2 个数
    // exp: A : [1,3,4,9] | B: [1,2,3,4,5,6,7,8,9,10], k = 7
    // 我们比较两个数组的第 k/2 个数字，如果 k 是奇数，向下取整。
    // 也就是比较第 3 个数字，上边数组中的 4 和下边数组中的 3，如果哪个小，就表明该数组的前 k/2 个数字都不是第 k 小数字，
    // 所以可以排除。也就是 1，2，3 这三个数字不可能是第 7 小的数字，我们可以把它排除掉。
    // 将 [1,3,4,9] 和 [4,5,6,7,8,9,10] 两个数组作为新的数组进行比较。
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int left = (len1 + len2 + 1) / 2;
        int right = (len1 + len2 + 2) / 2;

        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k
        return (getKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, left) +
                getKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, right)) / 2D;
    }

    private static double getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;

        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] < nums2[j]) {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        } else {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
    }

    public static double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int k = (len1 + len2 + 1) / 2;

        double ans = getK(nums1, 0, len1 - 1, nums2, 0, len2 - 1, k);
        if ((len1 + len2) % 2 == 0) {
            double temp = getK(nums1, 0, len1 - 1, nums2, 0, len2 - 1, k + 1);
            return (temp + ans) / 2D;
        } else {
            return ans;
        }
    }

    private static double getK(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;

        if (len1 > len2) return getK(nums2, start2, end2, nums1, start1, end1, k);

        if (len1 == 0) return nums2[start2 + k - 1];
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int i = start1 + Math.min(k / 2, len1) - 1;
        int j = start2 + Math.min(k / 2, len2) - 1;

        int a = nums1[i];
        int b = nums2[j];

        if (a < b) {
            return getK(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        } else {
            return getK(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
    }

}
