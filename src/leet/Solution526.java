package leet;

import java.util.*;

public class Solution526 {
//    List<Integer> list = new ArrayList<>();
    int count = 0;
    LinkedList<Integer> set = new LinkedList<>();

    public static void main(String[] args) {
        int N = 14;
        Solution526 sol = new Solution526();
        System.out.println(sol.countArrangement(N));
    }

    public int countArrangement(int N) {
        for (int i = 1;i<=N;i++){
            set.add(i);
        }

        helper(1);
        return count;
    }


    public void helper(int index){
        if (set.isEmpty()){
            count++;
            return;
        }

        int k = 0;
        while (k < set.size()){
            int val = set.get(k);
            if (index % val == 0 || val % index == 0){
//                list.add(val);
                set.remove(k);
                helper(index+1);

                set.add(k, val);
//                list.remove(list.size()-1);
            }
            k++;
        }
    }
}
