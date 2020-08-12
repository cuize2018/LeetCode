package leet;

public class Solution540 {
    public static void main(String[] args) {

    }

    /**
     * 因为是有序数组，所以可以用二分法。
     * 思路：取数组中间的数，
     * 当中间数的下标为奇数，说明前后元素的个数为奇数，偶数则剩余个数为偶数。
     * 奇数时：当nums[h]等于[h+1],唯一数处于前h-1,反之处于后h+1。
     * 偶数时：当nums[h]等于[h+1]，唯一数处于后h+2,反之处于前h.
     * <p>
     * 目的是要保持剩余查找元素个数为奇数，因为要查找的是唯一仅出现一次的元素，含有唯一数的数组长度必为奇数
     * (就是要保证剩余查找元素个数奇数)
     *
     * @param nums
     * @return
     */
    public static int singleNonDuplicate(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int middle = (low + high) >>> 1;
            //middle为奇数，则前后都是奇数个元素
            if ((middle) % 2 != 0) {
                //若nums[middle]==nums[middle+1]， 则[middle,end]个数为偶数，则[middle+2,end]个数为偶数，所以[middle,end]不存在唯一数
                if (nums[middle] == nums[middle + 1])
                    high = middle - 1;
                else
                    low = middle + 1;
            }
            //middle为偶数，则前后都是偶数个元素
            else {
                //若nums[middle]==nums[middle+1]， 则[middle,end]个数为奇数，则[middle+2,end]个数为奇数，所以[middle,end]存在唯一数
                if (nums[middle] == nums[middle + 1])
                    low = middle;
                    //若nums[middle]==nums[middle-1]， 则[low,middle]个数为奇数，则[low,middle-2]个数为奇数，所以[low,middle]存在唯一数
                else
                    high = middle;
            }
        }
        return nums[low];
    }

    /**
     * 滑动窗口
     *
     * @param nums
     * @return
     */
    public static int singleNonDuplicate2(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];

        int i = 0;
        for (; i <= nums.length - 2; i += 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return nums[i];
    }


    public static int singleNonDuplicate3(int[] nums) {
        if (nums.length == 0) return -1;

        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = (low + high) >>> 1;

            if ((mid % 2) == 1) {
                if (nums[mid] == nums[mid + 1]) {
                    high = mid - 1;
                } else if (nums[mid] == nums[mid - 1]) {
                    low = mid + 1;
                } else return nums[mid];
            } else {
                if (nums[mid] == nums[mid + 1]) {
                    low = mid;
                } else if (nums[mid] == nums[mid - 1]) {
                    high = mid;
                } else return nums[mid];
            }
        }
        return nums[low];
    }
}
