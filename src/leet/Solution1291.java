package leet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution1291 {
    List<Integer> res = new ArrayList<>();
    public static void main(String[] args) {
        int low = 1000, high = 13000;
        Solution1291 solution1291 = new Solution1291();
        System.out.println(solution1291.sequentialDigits(low, high));
    }

    public List<Integer> sequentialDigits(int low, int high) {
        for (int start = 1; start < 10; start++) {
            dfs(0, start, low, high);
        }
        Collections.sort(res);
        return res;
    }

    private void dfs(int val, int digit, int low, int high) {
        val = val*10 +digit;
        if (val > high){
            return;
        }

        if (val >= low && val <= high){
            res.add(val);
        }

        if (digit < 9){
            dfs(val, digit+1, low, high);
        }
    }
}
