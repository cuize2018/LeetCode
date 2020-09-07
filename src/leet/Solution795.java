package leet;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution795 {
    public static void main(String[] args) {
        int[] nums = {2,9,2,5,6};
//        int[] nums = {2,1,4,3};
        int L = 2;
        int r = 8;

        System.out.println(numSubarrayBoundedMax(nums,L,r));
    }

    public static int numSubarrayBoundedMax(int[] A, int L, int R) {
        if (A.length == 0)return 0;
        return  count(A, R) - count(A, L-1);
    }

    private static int count(int[] nums, int b) {
        int res = 0;
        int curr = 0;//使用 cur 记录在 B 的左边，小于等于 B 的连续元素数量
        for (int num : nums) {
            curr  = num <= b ? curr+1:0;
            res += curr;
        }
        return res;
    }
}
