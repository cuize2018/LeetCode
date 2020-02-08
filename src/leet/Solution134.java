package leet;

import javafx.util.Pair;

import java.util.*;

public class Solution134 {
    public static void main(String[] args) {
//        int[] gas  = {1,2,3,4,5};
//        int[] cost = {3,4,5,1,2};

        int[] gas  = {2,3,4};
        int[] cost = {3,4,3};
        System.out.println(canCompleteCircuit(gas,cost));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int length = gas.length;
        int[] rest = new int[length];
        List<Pair<Integer,Integer>> pairs = new ArrayList<>();

        for (int i = 0;i < length;i++){
            rest[i] = gas[i] - cost[i];
            pairs.add(new Pair<>(i,rest[i]));
        }

        int index = 0;
        int start_index;

        pairs.sort(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return Integer.compare(o2.getValue(), o1.getValue());
            }
        });

        for (Pair<Integer,Integer> pair : pairs) {
            index = pair.getKey();
            start_index = index;
            if (pair.getValue() < 0) break;

            int curr_gas = gas[start_index];
            while (curr_gas > 0) {
                if (index + 1 < length) {
                    if (curr_gas >= cost[index]) {
                        curr_gas = curr_gas - cost[index] + gas[index + 1];
                        index++;
                    } else {
                        break;
                    }
                } else {
                    if (curr_gas >= cost[index]) {
                        curr_gas = curr_gas - cost[index] + gas[0];
                        index = 0;
                    } else {
                        break;
                    }
                }

                if (curr_gas >= 0 && index == start_index) return start_index;
            }
        }

        return -1;
    }


    public static int canCompleteCircuit2(int[] gas, int[] cost){
        int length = gas.length;

        int total_gas = 0;
        int curr_gas = 0;
        int start = 0;
        for (int i = 0;i<length;i++){
            total_gas += gas[i]-cost[i];
            curr_gas += gas[i] - cost[i];

            if (curr_gas < 0){
                curr_gas = 0;
                start = i + 1;
            }
        }
        return total_gas >= 0 ? start:-1;
    }

}
