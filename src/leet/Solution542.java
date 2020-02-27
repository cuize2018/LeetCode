package leet;
import java.util.Arrays;

public class Solution542 {
    private int[][] mat;
    private enum Dir{up, down, left, right};
    private int min_one = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] a = {{1,0,0},{0,1,0},{1,1,1}};
        Solution542 sol = new Solution542();
        int[][] out = updateMatrix3(a);
        for (int[] temp : out){
            System.out.println(Arrays.toString(temp));
        }
    }

    /**
     * 深度优先（超时）
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        this.mat = matrix;
        int[][] out = new int[matrix.length][matrix[0].length];

        for (int i = 0;i<mat.length;i++){
            for (int j = 0;j<mat[0].length;j++){
                if (mat[i][j] == 0){
                    out[i][j] = 0;
                }
                else {
                    helper(i, j,0, null);
                    out[i][j] = min_one;
                    min_one = Integer.MAX_VALUE;
                }
            }
        }
        return out;
    }

    public int helper(int row, int col, int count, Dir last_dir){
        if (mat[row][col] == 0){
            return count;
        }

        for (Dir dir : Dir.values()){
            switch (dir){
                case up:
                    if (row-1 >= 0 && last_dir != Dir.down){
                        if (count+1 < min_one) {
                            int val = helper(row - 1, col, count + 1, Dir.up);
                            min_one = Math.min(min_one, val);
                            if(min_one == 1){
                                return min_one;
                            }
                        }
                    }
                    break;
                case down:
                    if (row + 1 <= mat.length-1 && last_dir != Dir.up){
                        if (count+1 < min_one) {
                            int val = helper(row + 1, col, count + 1, Dir.down);
                            min_one = Math.min(min_one, val);
                            if(min_one == 1){
                                return min_one;
                            }
                        }
                    }
                    break;
                case left:
                    if (col-1 >= 0 && last_dir != Dir.right){
                        if (count+1 < min_one) {
                            int val = helper(row, col - 1, count + 1, Dir.left);
                            min_one = Math.min(min_one, val);
                            if(min_one == 1){
                                return min_one;
                            }
                        }
                    }
                    break;
                case right:
                    if (col+1 <= mat[0].length-1 && last_dir != Dir.left){
                        if (count+1 < min_one) {
                            int val = helper(row, col + 1, count + 1, Dir.right);
                            min_one = Math.min(min_one, val);
                            if(min_one == 1){
                                return min_one;
                            }
                        }
                    }
                    break;
            }
        }
        return min_one;
    }

    /**
     * 对于每个 1，到 0 的最短路径可能从任意方向。所以我们需要检查所有 4 个方向。
     * 在从上到下的迭代中，我们需要检查左边和上方的最短路径；我们还需要另一个从下往上的循环，检查右边和下方的方向。
     *
     * 从上至下、从左至右迭代整个矩阵：
     * 更新
     * dist[i][j]=min(dist[i][j],min(dist[i][j−1],dist[i−1][j])+1)
     * 最近距离考虑上方邻居和左侧邻居距离 + 1，这在前面的迭代中已经计算完成。
     *
     * 从下到上、从右至左迭代整个矩阵：
     * 更新
     * dist[i][j]=min(dist[i][j],min(dist[i][j+1],dist[i+1][j])+1)
     * 最近距离考虑下方邻居和右侧邻居距离 + 1，这在前面的迭代中已经计算完成。
     *
     * @param matrix
     * @return
     */
    public static int[][] updateMatrix2(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0)
            return matrix;
        int cols = matrix[0].length;
        // 第一次遍历，正向遍历，根据相邻左元素和上元素得出当前元素的对应结果
        for (int i = 0;i<matrix.length;i++){
            for (int j = 0;j<matrix[0].length;j++){
                if (matrix[i][j] != 0){
                    if (matrix[i][j] == 1) {
                        matrix[i][j] = rows + cols;
                    }
                    if (i > 0){
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i-1][j]+1);
                    }
                    if (j > 0){
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][j-1]+1);
                    }
                }
            }
        }

        // 第二次遍历，反向遍历，根据相邻右元素和下元素及当前元素的结果得出最终结果
        for (int i = rows-1;i>=0;i--){
            for (int j = cols-1;j>=0;j--){
                if (matrix[i][j] != 0){
                    if (i < rows-1){
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i+1][j]+1);
                    }
                    if (j < cols-1){
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][j+1]+1);
                    }
                }
            }
        }

        return matrix;
    }


    public static int[][] updateMatrix3(int[][] matrix) {
        int[][] out = new int[matrix.length][matrix[0].length];
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int[] temp : out){
           Arrays.fill(temp, rows+cols);//初始化为一个较大的值
        }

        for (int i = 0;i<matrix.length;i++){
            for (int j = 0;j<matrix[0].length;j++){
                if (matrix[i][j] != 0){
                    if (i > 0){
                        out[i][j] = Math.min(out[i][j], out[i-1][j]+1);
                    }
                    if (j > 0){
                        out[i][j] = Math.min(out[i][j], out[i][j-1]+1);
                    }
                }
                else {
                    out[i][j] = 0;
                }
            }
        }

        for (int i = rows-1;i>=0;i--){
            for (int j = cols-1;j>=0;j--){
                if (matrix[i][j] != 0){
                    if (i < rows-1){
                        out[i][j] = Math.min(out[i][j], out[i+1][j]+1);
                    }
                    if (j < cols-1){
                        out[i][j] = Math.min(out[i][j], out[i][j+1]+1);
                    }
                }
            }
        }

        return out;
    }
}
