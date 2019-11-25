package leet;

import java.util.*;

public class Solution48 {
    public static void main(String[] args){
        int[][] mat = {
                {5, 1, 9,11},
                { 2, 4, 8,10},
                {13, 3, 6, 7},
                {15,14,12,16}
        };
        int[][] mat2 = {{1}};
        rotate(mat2);

        for(int i=0;i<mat2.length;i++)
            System.out.println(Arrays.toString(mat2[i]));
    }

    public static void rotate(int[][] matrix) {
       Queue<Integer> q1 = new ArrayDeque<>();
       Queue<Integer> q2 = new ArrayDeque<>();
       
       int count = 0;
       while (count < matrix.length/2) {
           int i = count;
           int j = count;
           while (j < matrix[i].length-count-1) {
               q1.add(matrix[i][j]);
               j++;
           }

           while (i < matrix.length-count-1) {
               q2.add(matrix[i][j]);
               int t = q1.remove();
               matrix[i][j] = t;
               i++;
           }


           while (j > count) {
               q1.add(matrix[i][j]);
               int t = q2.remove();
               matrix[i][j] = t;
               j--;
           }


           while (i > count) {
               q2.add(matrix[i][j]);
               int t = q1.remove();
               matrix[i][j] = t;
               i--;
           }

           while (j < matrix[i].length-count-1) {
               int t = q2.remove();
               matrix[i][j] = t;
               j++;
           }

           count++;
       }
    }
}
