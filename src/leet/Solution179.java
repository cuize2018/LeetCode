package leet;

import java.lang.reflect.Array;
import java.util.*;

public class Solution179 {
    public static void main(String[] args) {
        int[] a = {824, 938, 1399, 5607, 6973, 5703, 9609, 4398, 8247};
        int[] b = {57, 76, 70, 64, 94, 87, 92, 63, 1, 4, 20, 90, 98, 93, 76, 13, 59, 61, 84, 90, 95, 89, 16, 96, 18, 32, 82, 82, 73, 16, 66, 10, 25, 23, 58, 24, 82, 20, 18, 92, 31, 14, 10, 72, 94, 87, 64, 43, 51, 22, 53, 18, 34, 18, 12, 82, 79, 76, 33, 14, 53, 3, 25, 65, 60, 96, 15, 67, 13, 32, 26, 77, 88, 16, 84, 52, 40, 70, 95, 21, 48, 46, 24, 63, 92, 63, 93, 21, 12, 93, 62, 48, 66, 21, 92, 57, 32, 49, 78, 80};
        System.out.println(largestNumber(b));
    }

    public static String largestNumber(int[] nums) {
        String[] arr = new String[nums.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(arr, (x, y) -> {
            if (x.charAt(0) > y.charAt(0)) return -1;
            else if (x.charAt(0) < y.charAt(0)) return 1;
            else {
                String a = x + y;
                String b = y + x;
                int v = a.compareTo(b);
                return -v;
            }
        });

        if (arr[0].equals("0"))return "0";
        return String.join("", arr);
    }
}
