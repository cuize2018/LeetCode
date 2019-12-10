package leet;

import java.util.PriorityQueue;

public class Solution378 {
    public static void main(String[] args) {

    }

    public static int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] ints : matrix) {
            for (int anInt : ints) {
                pq.add(anInt);
            }
        }

        for (int i = 0;i<k-1;i++){
            pq.remove();
        }
        return pq.remove();
    }

    /**
     * 1.找出二维矩阵中最小的数left，最大的数right，那么第k小的数必定在left~right之间
     * 2.mid=(left+right) / 2；在二维矩阵中寻找小于等于mid的元素个数count
     * 3.若这个count小于k，表明第k小的数在右半部分且不包含mid，即left=mid+1, right=right，又保证了第k小的数在left~right之间
     * 4.若这个count大于k，表明第k小的数在左半部分且可能包含mid，即left=left, right=mid，又保证了第k小的数在left~right之间
     * 5.因为每次循环中都保证了第k小的数在left~right之间，当left==right时，第k小的数即被找出，等于right
     *
     * 作者：jacksu1024
     * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/er-fen-chao-ji-jian-dan-by-jacksu1024/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param matrix
     * @param k
     * @return
     */
    public static int kthSmallest2(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;

        int left = matrix[0][0];
        int right = matrix[row-1][col-1];

        while (left < right){
            int mid = (left+right)/2;
            int count = LessThanMidCount(matrix, row, col, mid);
            if (count < k){//第k小的值在mid右边
                left = mid+1;
            }
            else{//第k小的值在mid左边
                right = mid;
            }
        }
        return right;
    }

    private static int LessThanMidCount(int[][] mat, int row, int col, int mid){
        int count = 0;
        int i = row-1;int j = 0;
        while (i >= 0 && j < col) {
            if (mat[i][j] <= mid) {
                // 第j列有i+1个元素<=mid
                count += i + 1;
                j++;
            } else {
                // 第j列目前的数大于mid，需要继续在当前列往上找
                i--;
            }
        }
        return count;
    }
}
