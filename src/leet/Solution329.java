package leet;

import java.util.*;

public class Solution329 {
    public static void main(String[] args) {
        int[][] nums =
                {
                        {9, 9, 4},
                        {6, 6, 8},
                        {2, 1, 1}
                };
        int[][] nums2 =
                {
                        {3, 4, 5},
                        {3, 2, 6},
                        {2, 2, 1}
                };
        int[][] nums3 = {{1, 2}};
        System.out.println(longestIncreasingPath(nums3));
    }

    public static int longestIncreasingPath(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return 0;
        int cols = matrix[0].length;
        //存储每个点的入度
        int[][] inDegree = new int[rows][cols];
        HashMap<Integer, List<Integer>> adjacentList = new HashMap<>();

        //构建邻接表，存储每个节点周围较小的点
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                List<Integer> temp = new ArrayList<>();
                if (i - 1 >= 0 && matrix[i - 1][j] < matrix[i][j]) {
                    temp.add((i - 1) * 500 + j);
                    inDegree[i - 1][j]++;
                }

                if (i + 1 < rows && matrix[i + 1][j] < matrix[i][j]) {
                    temp.add((i + 1) * 500 + j);
                    inDegree[i + 1][j]++;
                }

                if (j - 1 >= 0 && matrix[i][j - 1] < matrix[i][j]) {
                    temp.add((i) * 500 + j - 1);
                    inDegree[i][j - 1]++;
                }

                if (j + 1 < cols && matrix[i][j + 1] < matrix[i][j]) {
                    temp.add(i * 500 + j + 1);
                    inDegree[i][j + 1]++;
                }
                adjacentList.put(i * 500 + j, temp);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        //将入度为0的点入队，入度为0的点即为最大的点
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (inDegree[i][j] == 0) {
                    queue.add(i * 500 + j);
                }
            }
        }
        //matrix数组来存储距离，每个点初始为1
        for (int[] ints : matrix) {
            Arrays.fill(ints, 1);
        }
        int x = 0;
        int y = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            //对每个入度为0的点
            for (int i = 0; i < size; i++) {
                int temp = queue.remove();
                x = temp / 500;
                y = temp % 500;

                for (int neighbor : adjacentList.get(temp)) {
                    int nei_x = neighbor / 500;
                    int nei_y = neighbor % 500;
                    inDegree[nei_x][nei_y]--;
                    //到neighbor点截止的距离 = 当前最大点temp的距离 + 1
                    matrix[nei_x][nei_y] = matrix[x][y] + 1;
                    //如果该节点的入度为0，则也入队
                    if (inDegree[nei_x][nei_y] == 0) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        return matrix[x][y];
    }
}
