package leet;

import java.util.*;

public class Solution373 {
    public static void main(String[] args) {
        int[] n1 = {};
        int[] n2 = {};
        int k = 3;
        System.out.println(kSmallestPairs(n1,n2,k));
    }

    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> heap = new PriorityQueue<>(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                int sum1 = o1.get(0)+o1.get(1);
                int sum2 = o2.get(0)+o2.get(1);
                return Integer.compare(sum1, sum2);
            }
        });

        for (int item : nums1) {
            for (int value : nums2) {
                List<Integer> temp = new ArrayList<>();
                temp.add(item);
                temp.add(value);
                heap.add(temp);
            }
        }

        List<List<Integer>> out = new ArrayList<>();
        for (int i = 0;i<k && !heap.isEmpty();i++){
            out.add(heap.remove());
        }
        return out;
    }
}
