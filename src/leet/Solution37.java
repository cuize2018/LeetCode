package leet;

import java.util.*;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 */
public class Solution37 {
    public static void main(String[] args){
        char[][] a = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(a);
        System.out.println(a);
    }

    public static  void solveSudoku(char[][] board) {
        HashMap<Integer, List<Character>> row_index = new HashMap<>();
        HashMap<Integer, List<Character>> col_index = new HashMap<>();
        HashMap<Integer, List<Character>> cube_index = new HashMap<>();
        for (int i = 0;i<9;i++){
            row_index.put(i, new ArrayList<>());
            col_index.put(i, new ArrayList<>());
            cube_index.put(i, new ArrayList<>());
        }

        for (int i = 0;i < 9;i++){
            int t = (i/3)*2;
            for (int j = 0;j<9;j++){
                char num = board[i][j];

                if (num!='.') {
                    row_index.get(i).add(num);
                    col_index.get(j).add(num);

                    int cube_idx = t + (i / 3 + j / 3);
                    cube_index.get(cube_idx).add(num);
                }

            }
        }

        backtrack(board, row_index, col_index, cube_index, 0,0);
    }

    private static boolean backtrack(char[][] board,
                                     HashMap<Integer, List<Character>> row_index,
                                     HashMap<Integer, List<Character>> col_index,
                                     HashMap<Integer, List<Character>> cube_index,
                                     int row, int col){
        if (col == 9){
            col = 0;
            row++;
            if (row == 9){
                return true;
            }
        }

        char[] all_num = {'1','2','3','4','5','6','7','8','9'};
        char num = board[row][col];
        int cube_idx = (row/3)*2 + (row/3+col/3);

        if (num == '.') {
            Set<Character> set = new HashSet<>(row_index.get(row));
            set.addAll(col_index.get(col));
            set.addAll(cube_index.get(cube_idx));

            for (char one : all_num){
                if (!set.contains(one)){
                    board[row][col] = one;
                    row_index.get(row).add(one);
                    col_index.get(col).add(one);
                    cube_index.get(cube_idx).add(one);

                    if(backtrack(board, row_index, col_index, cube_index, row, col+1)){
                        return true;
                    }

                    board[row][col] = '.';
                    row_index.get(row).remove(row_index.get(row).size()-1);
                    col_index.get(col).remove(col_index.get(col).size()-1);
                    cube_index.get(cube_idx).remove(cube_index.get(cube_idx).size()-1);
                }
            }
        }
        else {
            return backtrack(board, row_index, col_index, cube_index, row, col+1);
        }
        return false;
    }
}
