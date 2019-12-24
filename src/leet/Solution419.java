package leet;

public class Solution419 {
    public static void main(String[] args) {
        char[][] a = {
                {'X','.','.','X'},
                {'.','.','.','X'},
                {'.','.','.','X'}
        };
        char[][] b = {
                {'.','.'},
                {'X','X'}
        };
        System.out.println(countBattleships(b));
    }

    public static int countBattleships(char[][] board) {
        int rows = board.length;if (rows == 0)return 0;
        int cols = board[0].length;if (cols == 0)return 0;

        int[][] counts = new int[rows][cols];//以i，j结尾的船只数目
        if (board[0][0]=='X')counts[0][0] = 1;

        for (int j = 1;j<cols;j++){
            if (board[0][j] == 'X'){
                if (board[0][j-1] == 'X'){
                    counts[0][j] = counts[0][j-1];
                }
                else {
                    counts[0][j] = counts[0][j-1] + 1;
                }
            }
            else {
                counts[0][j] = counts[0][j-1];
            }
        }

        for (int i = 1;i<rows;i++){
            if (board[i][0] == 'X'){
                if (board[i-1][0] == 'X'){
                    counts[i][0] = counts[i-1][0];
                }
                else {
                    counts[i][0] = counts[i-1][0] + 1;
                }
            }
            else {
                counts[i][0] = counts[i-1][0];
            }
        }

        for (int i = 1;i < rows;i++){
            for (int j = 1;j < cols;j++){
                int last_col_add = counts[i-1][j] - counts[i-1][j-1];
                int last_row_add = counts[i][j-1] - counts[i-1][j-1];
                if (board[i][j] == 'X'){
                    if (board[i-1][j] == 'X'){
                        counts[i][j] = counts[i][j-1] + last_col_add;
                    }
                    else if (board[i][j-1] == 'X'){
                        counts[i][j] = counts[i-1][j] + last_row_add;
                    }
                    else if (board[i-1][j]=='.' && board[i][j-1]=='.'){
                        counts[i][j] = counts[i][j-1] + last_col_add + 1;
                    }
                }
                else {
                    counts[i][j] = counts[i][j-1] + last_col_add;
                }
            }
        }
        return counts[rows-1][cols-1];
    }
}
