package leet;

import java.util.*;

public class Solution90 {
    public static void main(String[] args){
        int[] a = {4,4,4,1,4};
        System.out.println(subsets(a));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
        out.add(new ArrayList<>());
        int len = nums.length;

        List<Integer> t = new ArrayList<>();
        for (int n:nums)t.add(n);

        for (int i = 0;i<len;i++){
            out.addAll(getSub(i+1, t));
        }
        return out;
    }

    private static List<List<Integer>> getSub(int k, List<Integer> t){
        Set<List<Integer>> l = new HashSet<>();
        if (t.size() == 0)return new ArrayList<>(l);

        Stack<Integer> tmp = new Stack<>();
        for (int i = 0;i < t.size()-(k-1);i++){
            List<Integer> sub_rest = t.subList(i+1,t.size());
            part(t.get(i), sub_rest, k-1, tmp, l);
            tmp.pop();
        }
        return new ArrayList<>(l);
    }
    private static void part(int start_num, List<Integer> rest, int k, Stack<Integer> list,
                             Set<List<Integer>> all){
        list.push(start_num);

        if (k == 0){
            List<Integer> tmp = new ArrayList<>(list);
            Collections.sort(tmp);
            all.add(tmp);
            return;
        }
        for (int i = 0;i<rest.size();i++){
            List<Integer> sub_rest = rest.subList(i+1, rest.size());
            part(rest.get(i), sub_rest, k-1, list, all);
            list.pop();
        }
    }
}
