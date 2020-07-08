package leet;

public class Solution108 {
    public static void main(String[] args) {
        int[] nums = new int[]{-10, -3, 0, 5, 9};
        System.out.println(sortedArrayToBST(nums));
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBSTHelper(int[] nums, int low, int high) {
        if (low > high) return null;

        if (low == high) {
            return new TreeNode(nums[low]);
        }

        int mid = low + (high - low) / 2;
        int midVal = nums[mid];
        TreeNode root = new TreeNode(midVal);

        TreeNode left = sortedArrayToBSTHelper(nums, low, mid - 1);
        TreeNode right = sortedArrayToBSTHelper(nums, mid + 1, high);

        root.left = left;
        root.right = right;

        return root;
    }
}
