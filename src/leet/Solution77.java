package leet;

import java.util.*;

public class Solution77 {
    private  List<List<Integer>> l = new ArrayList<>();
    Stack<Integer> tmp = new Stack<>();

    public static void main(String[] args){
        Solution77 s = new Solution77();
        List<List<Integer>> l = s.combine(4,3);
        System.out.println(l);
    }

    public  List<List<Integer>> combine(int n, int k) {
        List<Integer> t = new ArrayList<>();
        if (n == 0)return l;
        for (int i = 0;i<n;i++){
            t.add(i+1);
        }

        for (int i = 0;i < n-(k-1);i++){
            List<Integer> sub_rest = t.subList(i+1,t.size());
            part(t.get(i), sub_rest, k-1);
            tmp.pop();
        }
        return l;
    }

    private void part(int start_num, List<Integer> rest, int k){
        tmp.push(start_num);

        if (k == 0){
            l.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0;i<rest.size();i++){
            List<Integer> sub_rest = rest.subList(i+1, rest.size());
            part(rest.get(i), sub_rest, k-1);
            tmp.pop();
        }
    }
}
