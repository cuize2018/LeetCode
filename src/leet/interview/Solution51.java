package leet.interview;

public class Solution51 {
    public static void main(String[] args) {

    }

    public static int reversePairs(int[] nums) {
        if (nums.length == 0) return 0;
        return mergeSort(nums, 0, nums.length - 1);
    }

    private static int mergeSort(int[] nums, int left, int right) {
        if (left >= right) return 0;

        int middle = (left + right) >>> 1;
        int res = mergeSort(nums, left, middle) + mergeSort(nums, middle + 1, right);

        int i = left;
        int j = middle + 1;
        int k = 0;
        int[] temp = new int[right - left + 1];
        //此时数组被分为两部分，前有序数组和后有序数组
        while (i <= middle && j <= right) {
            if (nums[i] <= nums[j]) {//当前有序数组出列时，意味着nums[i] <= nums[j]， 即不构成逆序对，结果res不变
                temp[k] = nums[i];
                k++;
                i++;
            } else {
                //当后有序数组出列时，意味着nums[i] > nums[j]， 即构成逆序对，
                //结果res += middle-i+1, 即为前有序数组[i,middle]的元素个数， 这些元素均与后有序数组的j构成逆序对
                temp[k] = nums[j];
                j++;
                k++;
                res += middle - i + 1;
            }
        }

        //如果后有序数组已经出列完毕，则仅需出列前有序数组，结果无需改变
        while (i <= middle) temp[k++] = nums[i++];
        //如果前有序数组已经出列完毕，则仅需出列后有序数组，结果无需改变
        while (j <= right) temp[k++] = nums[j++];

        //将排序好的数组temp拷贝回nums数组
        for (i = left, j = 0; i <= right; i++, j++) {
            nums[i] = temp[j];
        }

        return res;
    }
}
