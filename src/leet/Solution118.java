package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution118 {
    public static void main(String[] args) {
        int num = 5;
        System.out.println(generate(num));
    }

    public static List<List<Integer>> generate(int numRows) {
        if (numRows == 0)return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> one = new ArrayList<>();
            for (int j = 0; j < i+1; j++) {
                if (j == 0 || j == i)one.add(1);
                else {
                    int val = res.get(i-1).get(j-1) + res.get(i-1).get(j);
                    one.add(val);
                }
            }
            res.add(one);
        }
        return res;
    }
}
