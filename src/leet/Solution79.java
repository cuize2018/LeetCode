package leet;

import java.io.StringReader;
import java.util.Arrays;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = 'ABCCED', 返回 true.
 * 给定 word = 'SEE', 返回 true.
 * 给定 word = 'ABCB', 返回 false.
 */
public class Solution79 {
    public static void main(String[] args){
        char[][] b = {{'A','B','C','E'},
        {'S','F','C','S'},
        {'A','D','E','E'}};
        char[][] c = {{'a'}};
        char[][] d = {{'a','a','a','a'},{'a','a','a','a'},{'a','a','a','a'}};
        char[][] f = {{'C','A','A'},{'A','A','A'},{'B','C','D'}};
         System.out.println(exist(f,  "AAB"));
    }

    public  static  boolean exist(char[][] board, String word) {
        int[][] trace = new int[board.length][board[0].length];

        for (int i = 0;i<board.length;i++){
            for (int j = 0;j<board[0].length;j++){
                if (board[i][j] == word.charAt(0)){
                    trace[i][j] = 1;
                    if (1 == word.length()){
                        return true;
                    }
                    if (goOneDir(0,word,1,i,j,board,trace))return true;
                    if (goOneDir(1,word,1,i,j,board,trace))return true;
                    if (goOneDir(2,word,1,i,j,board,trace))return true;
                    if (goOneDir(3,word,1,i,j,board,trace))return true;
                    trace[i][j] = 0;
                }
            }
        }
        return false;
    }

    private static  boolean goOneDir(int dir, String word, int curr_char_idx, int curr_row, int curr_col,
                                     char[][] board, int[][] trace){
        char curr_char;
        curr_char = word.charAt(curr_char_idx);

        switch (dir) {
            case (0):
                if (curr_row != 0) {
                    if (board[curr_row - 1][curr_col] == curr_char && trace[curr_row - 1][curr_col]==0) {
                        curr_row--;
                        curr_char_idx++;
                        trace[curr_row][curr_col]=1;
                        if (curr_char_idx == word.length()){
                            return true;
                        }

                        if (goOneDir(0, word, curr_char_idx, curr_row, curr_col, board,trace))return true;
                        if (goOneDir(2, word, curr_char_idx, curr_row, curr_col, board,trace))return true;
                        if (goOneDir(3, word, curr_char_idx, curr_row, curr_col, board,trace))return true;

                        trace[curr_row][curr_col] = 0;
                    }
                }
                break;
            case (1):
                if (curr_row != board.length-1) {
                    if (board[curr_row + 1][curr_col] == curr_char && trace[curr_row + 1][curr_col]==0) {
                        curr_row++;
                        curr_char_idx++;
                        trace[curr_row][curr_col]=1;
                        if (curr_char_idx == word.length()){
                            return true;
                        }

                        if (goOneDir(1, word, curr_char_idx, curr_row, curr_col, board,trace))return true;
                        if (goOneDir(2, word, curr_char_idx, curr_row, curr_col, board,trace))return true;
                        if (goOneDir(3, word, curr_char_idx, curr_row, curr_col, board,trace))return true;

                        trace[curr_row][curr_col] = 0;

                    }
                }
                break;
            case (2):
                if (curr_col != 0) {
                    if (board[curr_row][curr_col-1] == curr_char && trace[curr_row][curr_col-1] == 0) {
                        curr_col--;
                        curr_char_idx++;
                        trace[curr_row][curr_col]=1;
                        if (curr_char_idx == word.length()){
                            return true;
                        }

                        if (goOneDir(0, word, curr_char_idx, curr_row, curr_col, board, trace))return true;
                        if (goOneDir(1, word, curr_char_idx, curr_row, curr_col, board,trace))return true;
                        if (goOneDir(2, word, curr_char_idx, curr_row, curr_col, board,trace))return true;

                        trace[curr_row][curr_col] = 0;
                    }
                }
                break;
            case (3):
                if (curr_col != board[0].length-1) {
                    if (board[curr_row][curr_col+1] == curr_char && trace[curr_row][curr_col+1] == 0) {
                        curr_col++;
                        curr_char_idx++;
                        trace[curr_row][curr_col]=1;
                        if (curr_char_idx == word.length()){
                            return true;
                        }

                        if (goOneDir(0, word, curr_char_idx, curr_row, curr_col, board,trace)){
                            return true;
                        }
                        if (goOneDir(1, word, curr_char_idx, curr_row, curr_col, board,trace)){
                            return true;
                        }
                        if (goOneDir(3, word, curr_char_idx, curr_row, curr_col, board,trace))return true;

                        trace[curr_row][curr_col] = 0;
                    }
                }
                break;
        }
       return false;
    }
}
