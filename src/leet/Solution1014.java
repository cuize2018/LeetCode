package leet;

public class Solution1014 {
    public static void main(String[] args) {
        int[] A = {8, 1, 5, 2, 6};
        System.out.println(maxScoreSightseeingPair(A));
    }

    /**
     * dp来做，稍微给这个公式变形成A[i]+i + A[j]-j，这样就可以看成是左A[i]+i和右A[j]-j两部分和的最大值。
     * 随着遍历数组，我们对两部分和取最大值，并且若当前的(A[i]+i)的和比之前更大，我们就更新left部分的值即可。
     *
     * @param A
     * @return
     */
    public static int maxScoreSightseeingPair(int[] A) {
        int n = A.length;
        int left = A[0];
        int max = Integer.MIN_VALUE;

        for (int j = 1; j < n; j++) {
            max = Math.max(max, left + A[j] - j);
            left = Math.max(left, A[j] + j);
        }
        return max;
    }
}
