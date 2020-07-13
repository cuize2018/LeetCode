package leet;

import java.util.*;

public class Solution350 {
    public static void main(String[] args) {
        int[] a = {4,9,5};
        int[] b = {9,4,9,8,4};
        System.out.println(Arrays.toString(intersect2(a,b)));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) return new int[0];
        if (nums1.length < nums2.length) return intersect(nums2, nums1);

        Map<Integer, Integer> map = new HashMap<>();
        for (int item : nums2) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        for (int value : nums1) {
            if (map.containsKey(value) && map.get(value) > 0) {
                res.add(value);
                map.put(value, map.get(value) - 1);
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    public static int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) return new int[0];
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0;
        int j = 0;
        List<Integer> res = new ArrayList<>();
        while (i < nums1.length && j <nums2.length){
            if (nums1[i] < nums2[j]){
                i++;
            }
            else if (nums1[i] > nums2[j]){
                j++;
            }
            else {
                res.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] ans = new int[res.size()];
        for (int m = 0; m < ans.length; m++) {
            ans[m] = res.get(m);
        }
        return ans;
    }
}
