package leet;

public class Solution88 {
    public static void main(String[] args) {

    }
    //从后往前比较元素
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        if (nums1.length == 0 || nums2.length == 0) return;
        int i = m-1;
        int j = n-1;
        int k = m+n-1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]){
                nums1[k] = nums1[i];
                i--;
            }
            else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        // add missing elements from nums2
        if (i < 0) System.arraycopy(nums2, 0, nums1, 0, j+1);
    }

    public static void merge3(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int k = nums1.length - 1;
        while (i >= 0 && j >= 0){
            if (nums1[i] > nums2[j]){
                nums1[k--] = nums1[i--];
            }
            else {
                nums1[k--] = nums2[j--];
            }
        }
        while (i >= 0){
            nums1[k--] = nums1[i--];
        }
        while (j >= 0){
            nums1[k--] = nums2[j--];
        }
    }
}
