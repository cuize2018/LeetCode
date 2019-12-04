package leet;
import java.util.*;

public class Solution347 {
    public static void main(String[] args) {
        int[] a = {1};
        int k = 1;
        System.out.println(topKFrequent(a, k));
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> out = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (map.get(o1) > map.get(o2)){
                    return -1;
                }
                else if (map.get(o1) < map.get(o2)){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        });

        if (nums.length == 0)return null;
        for (int val : nums){
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        queue.addAll(map.keySet());

        while (k > 0){
            out.add(queue.remove());
            k--;
        }
        return out;
    }
}
