package leet.interview;

import java.util.Arrays;

public class Solution33 {
    public static void main(String[] args) {
        int[] nums = {1,6,3,2,5};
        System.out.println(verifyPostorder(nums));
    }

    public static boolean verifyPostorder(int[] postorder) {
        if (postorder.length <= 2)return true;
        int len = postorder.length;
        int root = postorder[len-1];

        int i = 0;
        for (; i < postorder.length-1; i++) {
            if (postorder[i] > root)break;
        }
        for (int j = i+1; j < postorder.length-1; j++) {
            if (postorder[j] <= root)return false;
        }

        boolean left = verifyPostorder(Arrays.copyOfRange(postorder, 0, i));
        boolean right = verifyPostorder(Arrays.copyOfRange(postorder, i , len - 1));
        return left && right;
    }

}
