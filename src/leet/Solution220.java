package leet;

import java.util.*;

public class Solution220 {
    public static void main(String[] args){
        int[] nums = {1,2};
        int k = 0;
        int t = 1;

        System.out.println(containsNearbyAlmostDuplicate(nums,k,t));
    }

    /**
     * 初始化一颗空的二叉搜索树 set
     * 对于每个元素xx，遍历整个数组
     * 在 set 上查找大于等于x的最小的数，如果s - x ≤ t则返回 true
     * 在 set 上查找小于等于x的最大的数，如果x - g ≤ t则返回 true
     * 在 set 中插入x
     * 如果树的大小超过了k, 则移除最早加入树的那个数。
     * 返回 false
     * 我们把大于等于 x 的最小的数 s 当做 x 在 BST 中的后继节点。
     * 同样的，我们能把小于等于 x 最大的数 g 当做 x 在 BST 中的前继节点。
     * s 和 g 这两个数是距离 x 最近的数。因此只需要检查它们和 x 的距离就能知道条件二是否满足了。
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0;i<nums.length;i++) {
            Integer smallest_great = set.ceiling(nums[i]);
            if (smallest_great != null && (long)smallest_great <= (long)nums[i] + t) {
                return true;
            }

            Integer greatest_small = set.floor(nums[i]);
            if (greatest_small != null && (long)greatest_small >= (long)nums[i] - t) {
                return true;
            }

            set.add(nums[i]);
            if (set.size() > k){
                set.remove(nums[i-k]);
            }
        }
        return false;
    }
}
