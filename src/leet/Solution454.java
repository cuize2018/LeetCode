package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution454 {
    public static void main(String[] args) {
//        int[] A = {1, 2};
//        int[] B = {-2,-1};
//        int[] C = {-1, 2};
//        int[] D = {0, 2};
        int[]A = {-1,-1};
    int[] B = {-1,1};
    int[] C = {-1,1};
    int[] D = {1,-1};

        System.out.println(fourSumCount(A,B,C,D));
    }

    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> mapAB = new HashMap<>();

        for (int a : A){
            for (int b : B){
                int sum = a + b;
                if (mapAB.containsKey(sum)){
                    mapAB.put(sum, mapAB.get(sum)+1);
                }
                else {
                    mapAB.put(sum, 1);
                }
            }
        }

        int count = 0;
        for (int c : C){
            for (int d : D){
                int sum = -(c + d);
                if (mapAB.containsKey(sum)){
                    count += mapAB.get(sum);
                }
            }
        }
        return count;
    }
}
