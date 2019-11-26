package leet;

import java.util.Arrays;

public class Solution324 {
    public static void main(String[] args) {
        int[] a = {1,2,3,5,5,5,6 ,7,8};
        wiggleSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 由摇摆序列的定义:nums[0] < nums[1] > nums[2] < nums[3]...，我们知道了可以分成较大一部分的数和较小一部分数，
     * 然后互相穿插即可。比如一个数组排序后为：A=[a1,a2,...,an] (a1<=a2<=....<=an)，
     * 然后分成较小和较大的两部分[a1,a2,...,a(n/2)]，[a(n/2+1),...,an]（数组长度为奇数时不影响），再进行穿插操作。
     *
     * 那是不是穿插成[a1,a(n/2+1),a2,a(n/2+2),...,an]就行了呢？
     * 其实不对，可以验证特殊情况：n比较小时且为偶数时，穿插后的序列需要满足a(n/2+1)>a2，
     * 如果a1<a2<=a(n/2+1)<an，a(n/2+1)正好是a2的后一项且与a2相等呢？即如果是[4,5,5,6]的情况呢？
     * 那就分成了[4,5]，[5,6]两部分，之后穿插成的是[4,5,5,6]并不是摇摆序列。
     *
     * 应该怎样排列呢？
     * 把较小和较大的两部分[a1,a2,...,a(n/2)]，[a(n/2+1),...,an]翻转成[a(n/2),...,a1]，[an,...,a(n/2+1)]
     * 排列成B=[a(n/2),a(n),a(n/2-1),a(n-1),...,a1,a(n/2+1)]即可。

     * @param nums
     */
    public static void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int mid = nums.length%2==0?nums.length/2:nums.length/2+1;
        int[] left = Arrays.copyOfRange(nums, 0,mid);
        int[] right = Arrays.copyOfRange(nums, mid,nums.length);

        int l = left.length-1;
        int r = right.length-1;
        for (int i = 0;i<nums.length;i++){
            if ((i+1)%2 != 0){
                nums[i] = left[l];
                l--;
            }
            else {
                nums[i] = right[r];
                r--;
            }
        }

    }

}
