package leet;

import java.util.*;

public class Solution39 {
    public static void main(String[] args){
        int[] a = {3,4,7,8};
        int target = 11;

        System.out.println(combinationSum(a,target));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> out = new ArrayList<>();
        HashSet<List<Integer>> set = new HashSet<>();

        Arrays.sort(candidates);
        if (candidates.length == 0)return out;
        if (candidates.length == 1){
            if (candidates[0] == target){
                List<Integer> t = new ArrayList<>();
                t.add(candidates[0]);
                out.add(t);
                return out;
            }
        }
        List<Integer> a = new ArrayList<>();
        a.add(0);

        for (int i = 0;i<candidates.length;i++){
            Stack<Integer> route = new Stack<>();
            route.push(candidates[i]);

            int[] t = Arrays.copyOfRange(candidates,i,candidates.length);
            out.addAll(oneSearch2(set, t, route, target,a));
        }
        return out;
    }

    private static List<List<Integer>> oneSearch2(Set<List<Integer>> set,int[] candidates, Stack<Integer> num, int target,List<Integer> count){
        List<List<Integer>> out = new ArrayList<>();
        if (num.peek() == target){
            List<Integer> tmp = new ArrayList<>(num);
            Collections.sort(tmp);
            if (!set.contains(tmp)) {
                out.add(tmp);
                set.add(tmp);
            }
            num.pop();
            return out;
        }

        for (int i = 0;i<candidates.length;i++){
            count.set(0,count.get(0) + 1);

            int temp = candidates[i];
            int sum = temp;
            for (int ele : num){
                sum+=ele;
            }
            num.push(temp);

            if (sum == target){
                List<Integer> tmp = new ArrayList<>(num);
                Collections.sort(tmp);
                if (!set.contains(tmp)) {
                    out.add(tmp);
                    set.add(tmp);
                }
                num.pop();
                break;
            }
            else if (sum > target){
                num.pop();
                break;
            }else {
                List<List<Integer>> sub_out = oneSearch2(set, candidates, num, target,count);
                out.addAll(sub_out);
                num.pop();
            }
        }
        return out;
    }
}
