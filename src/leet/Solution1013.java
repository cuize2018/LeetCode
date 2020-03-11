package leet;

public class Solution1013 {
    public static void main(String[] args) {
        int[] A = {6, 1, 1, 13, -1, 0, -10, 20};
        System.out.println(canThreePartsEqualSum(A));
    }

    public static boolean canThreePartsEqualSum(int[] A) {
        int left = 0;
        int right = A.length - 1;
        int sum = 0;
        for (int val : A) {
            sum += val;
        }
        if (sum % 3 != 0) return false;

        int avg = sum / 3;
        int left_sum = A[left];
        int right_sum = A[right];

        while (left + 1 < right) {
            if (left_sum == avg && right_sum == avg) {
                return true;
            }

            if (left_sum != avg) {
                left++;
                left_sum += A[left];
            }

            if (right_sum != avg) {
                right--;
                right_sum += A[right];
            }
        }
        return false;
    }
}
