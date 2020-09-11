package leet;

import java.util.TreeMap;

public class Solution846 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int w = 3;
        System.out.println(isNStraightHand(nums, w));
    }

    public static boolean isNStraightHand(int[] hand, int W) {
        if (hand.length % W != 0) return false;

        TreeMap<Long, Long> treeMap = new TreeMap<>();
        for (int num : hand) {
            treeMap.put((long) num, treeMap.getOrDefault((long) num, 0L) + 1);
        }

        while (!treeMap.isEmpty()) {
            Long start = treeMap.firstKey();
            if (treeMap.get(start) == 1) treeMap.remove(start);
            else treeMap.put(start, treeMap.get(start) - 1);

            long last = start;
            long currVal = 0;
            for (int i = 1; i < W; i++) {
                currVal = last + 1;
                if (!treeMap.containsKey(currVal)) return false;

                if (treeMap.get(currVal) == 1) treeMap.remove(currVal);
                else treeMap.put(currVal, treeMap.get(currVal) - 1);

                last = currVal;
            }
        }
        return true;
    }
}
