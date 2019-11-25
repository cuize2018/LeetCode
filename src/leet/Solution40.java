package leet;

import java.util.*;

public class Solution40 {
    public static void main(String[] args){
        int[] candidates = {2,5,2,1,2};
        int target = 5;
        System.out.println(combinationSum2(candidates,target));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> out = new ArrayList<>();
        HashSet<List<Integer>> set = new HashSet<>();
        if (candidates.length == 0)return out;
        if (candidates.length == 1){
            if (candidates[0] == target){
                List<Integer> t = new ArrayList<>();
                t.add(candidates[0]);
                out.add(t);
                return out;
            }
        }

        Arrays.sort(candidates);
        for (int i = 0;i<candidates.length;i++){
            int[] t = Arrays.copyOfRange(candidates, i+1, candidates.length);
            Stack<Integer> route = new Stack<>();
            route.push(candidates[i]);

            List<List<Integer>> tmp = oneSearch(set, t, route, candidates[i], target);
            out.addAll(tmp);
        }
        return out;
    }

    private static List<List<Integer>> oneSearch(Set<List<Integer>> set, int[] candidates,
                                                 Stack<Integer> num, int sum, int target){
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
            int temp = candidates[i];
            sum += temp;
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
                int[] t = Arrays.copyOfRange(candidates, i+1, candidates.length);
                List<List<Integer>> sub_out = oneSearch(set, t, num, sum, target);
                out.addAll(sub_out);
                sum -= num.peek();
                num.pop();
            }
        }
        return out;
    }
}
