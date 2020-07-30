package leet;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] arr = {9,9,1,58,73,6,2};
        HeapSort(arr);
//        QuickSort(arr);
//        MergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    /*-----------------------------------------------------*/
    private static void QuickSort(int[] nums) {
        if (nums.length <= 1)return;
        quickSort(nums, 0, nums.length-1);
    }

    private static void quickSort(int[] nums, int low, int high) {
        if (low >= high)return;
        int m = partition(nums, low, high);
        quickSort(nums, 0, m-1);
        quickSort(nums, m+1, high);
    }

    private static int partition(int[] nums, int low, int high) {

        int i = low;
        int j = high+1;
        int v = nums[low];

        while (true){
            while (nums[++i] < v){
                if (i == high)break;
            }
            while (nums[--j] > v){
                if (j == low)break;
            }
            if (i >= j)break;
            swap(nums, i, j);
        }
        swap(nums, low, j);
        return j;
    }
    /*-------------------------------------------------------*/
    private static void HeapSort(int[] nums) {
        int len = nums.length;
        buildMaxHeap(nums);

        for (int i = len-1; i > 0; i--) {
            swap(nums, 0 , i);
            len--;
            adjustHeap(nums, 0, len);
        }
    }

    private static void buildMaxHeap(int[] nums) {
        for (int i = nums.length/2-1; i >= 0; i--) {
            adjustHeap(nums, i, nums.length);
        }
    }

    private static void adjustHeap(int[] nums, int root, int end) {
        int left = 2*root+1;
        int right = left+1;
        int max = root;

        if (left < end && nums[left] > nums[max])max = left;
        if (right < end && nums[right] > nums[max])max = right;
        if (max != root){
            swap(nums, root, max);
            adjustHeap(nums, max, end);
        }
    }

    /*-------------------------------------------------------*/

    private static void MergeSort(int[] nums){
        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length-1, temp);
    }

    private static void mergeSort(int[] nums, int low, int high, int[] temp) {
        if (low >= high)return;
        int mid = (low + high) >>>1;
        mergeSort(nums, low, mid, temp);
        mergeSort(nums, mid+1, high, temp);

        mergeArray(nums, low, mid, high, temp);
    }

    private static void mergeArray(int[] nums, int low, int mid, int high, int[] temp) {
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high){
            if (nums[i] < nums[j]){
                temp[k++] = nums[i++];
            }
            else {
                temp[k++] = nums[j++];
            }
        }
        while (i <= mid){
            temp[k++] = nums[i++];
        }
        while (j <= high){
            temp[k++] = nums[j++];
        }
        k = 0;
        for (int m = low; m <= high; m++) {
            nums[m] = temp[k++];
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
