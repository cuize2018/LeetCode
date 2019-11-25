package leet;

import java.util.Arrays;

public class Solution59 {
    enum Dir{
        Right, Down, Left, Up
    }

    public static void main(String[] args){
        int[][] a = generateMatrix(5);
        for (int i = 0;i<a.length;i++){
            System.out.println(Arrays.toString(a[i]));
        }
    }

    public static int[][] generateMatrix(int n) {
        int[][] out = new int[n][n];
        int num = 1;
        Dir dir = Dir.Right;
        int curr_row = 0;
        int curr_col = 0;
        int dis = n;

        while (num <= n*n){
            switch (dir){
                case Right:
                    for (int i = curr_col;i<curr_col+dis;i++){
                        out[curr_row][i] = num;
                        num++;
                    }
                    dis--;
                    curr_col += dis;
                    dir = Dir.Down;
                    break;
                case Up:
                    for (int i = curr_row-1;i>=curr_row-dis;i--){
                        out[i][curr_col] = num;
                        num++;
                    }
                    curr_row-=dis;
                    curr_col++;
                    dir = Dir.Right;
                    break;
                case Down:
                    for (int i = curr_row+1;i <= curr_row+dis;i++){
                        out[i][curr_col] = num;
                        num++;
                    }
                    curr_row += dis;
                    dir = Dir.Left;
                    break;
                case Left:
                    for (int i = curr_col-1;i>=curr_col-dis;i--){
                        out[curr_row][i] = num;
                        num++;
                    }
                    curr_col-=dis;
                    dis--;
                    dir = Dir.Up;
                    break;
            }

        }
        return out;
    }
}
