package leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution216 {
    private List<List<Integer>> out = new ArrayList<>();
    private Stack<Integer> one = new Stack<>();

    public static void main(String[] args){
        int k = 1;
        int n = 6;
        Solution216 s = new Solution216();
        System.out.println(s.combinationSum3(k,n));
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        for (int i = 1;i <= 9;i++){
            if (k > 0 && n > 0) {
                one.add(i);
                helper(k - 1, n - i, i);
            }
        }
        return out;
    }

    public void helper(int k, int n, int start_num){
        if (k == 0 && n == 0){
            out.add(new ArrayList<>(one));
            one.pop();
            return;
        }
        else {
            if (k > 0 && n > 0){
                for (int i = start_num+1;i <= 9;i++){
                    one.add(i);
                    helper(k-1, n-i, i);
                }
                one.pop();
            }
            else {
                one.pop();
                return;
            }
        }
    }
}
