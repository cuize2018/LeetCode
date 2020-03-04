package leet;

import java.util.*;

public class Solution994 {
    public static void main(String[] args) {
//        int[][] a ={{2,1,1},{1,1,0},{0,1,1}};
        int[][] a = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(orangesRotting(a));
    }

    public static int orangesRotting(int[][] grid) {
        int minutes = 0;
        int changes = 1;
        int fresh = 0;
        for (int[] a : grid) {
            for (int num : a) {
                if (num == 1) {
                    fresh++;
                }
            }
        }

        int[][] temp = new int[grid.length][grid[0].length];
        for (int m = 0; m < grid.length; m++) {
            temp[m] = Arrays.copyOfRange(grid[m], 0, grid[0].length);
        }
        while (changes != 0) {
            changes = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 2) {
                        if (i > 0 && grid[i - 1][j] == 1 && temp[i - 1][j] != 2) {
                            temp[i - 1][j] = 2;
                            changes++;
                        }

                        if (i < grid.length - 1 && grid[i + 1][j] == 1 && temp[i + 1][j] != 2) {
                            temp[i + 1][j] = 2;
                            changes++;
                        }

                        if (j > 0 && grid[i][j - 1] == 1 && temp[i][j - 1] != 2) {
                            temp[i][j - 1] = 2;
                            changes++;
                        }

                        if (j < grid[0].length - 1 && grid[i][j + 1] == 1 && temp[i][j + 1] != 2) {
                            temp[i][j + 1] = 2;
                            changes++;
                        }
                    }
                }
            }
            fresh -= changes;
            for (int m = 0; m < temp.length; m++) {
                grid[m] = Arrays.copyOfRange(temp[m], 0, temp[0].length);
            }
            minutes++;
        }

        if (fresh != 0) return -1;
        return minutes - 1;
    }

    //多源BFS
    public static int orangesRotting2(int[][] grid) {
        int[] dirRow = new int[]{-1, 0, 1, 0}; //四个方向
        int[] dirCol = new int[]{0, -1, 0, 1};

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> depth = new HashMap<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    int code = i * cols + j; //行列值编码
                    queue.add(code);
                    depth.put(code, 0);
                }
            }
        }

        int setups = 0;
        while (!queue.isEmpty()) {
            int code = queue.remove();
            int n_row = code / cols;
            int n_col = code % cols;

            for (int i = 0; i < 4; i++) {
                int temp_row = n_row + dirRow[i];
                int temp_col = n_col + dirCol[i];

                if (temp_row >= 0 && temp_row < rows && temp_col >= 0 && temp_col < cols && grid[temp_row][temp_col] == 1) {
                    grid[temp_row][temp_col] = 2;
                    int temp_code = temp_row * cols + temp_col;
                    queue.add(temp_code);
                    depth.put(temp_code, depth.get(code)+1);
                    setups = depth.get(temp_code);
                }
            }
        }

        for (int[] one : grid){
            for (int val : one){
                if (val == 1){
                    return -1;
                }
            }
        }
        return setups;
    }
}
