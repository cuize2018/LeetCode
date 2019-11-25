package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution36 {
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
        System.out.println(isValidSudoku(a));
    }

    public static boolean isValidSudoku(char[][] board) {
        HashMap<Integer, List<Character>> row_index = new HashMap<>();
        HashMap<Integer, List<Character>> col_index = new HashMap<>();
        HashMap<Integer, List<Character>> cube_index = new HashMap<>();


        for (int i = 0;i<9;i++){
            row_index.put(i, new ArrayList<>());
            col_index.put(i, new ArrayList<>());
            cube_index.put(i, new ArrayList<>());
        }
        int t = 0;
        for (int i = 0;i < 9;i++){
            t = (i/3)*2;

            for (int j = 0;j < 9;j++){
                char num = board[i][j];
                if (num != '.') {
                    if (!row_index.get(i).contains(num)) {
                        row_index.get(i).add(num);
                    } else {
                        return false;
                    }

                    if (!col_index.get(j).contains(num)) {
                        col_index.get(j).add(num);
                    } else {
                        return false;
                    }

                    int cube_idx = t + (i/3+j/3);
                    if (!cube_index.get(cube_idx).contains(num)) {
                        cube_index.get(cube_idx).add(num);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
