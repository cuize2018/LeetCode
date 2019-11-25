package leet;

import java.util.*;

public class Solution179 {
    public static void main(String[] args){
        int[] a = {824,938,1399,5607,6973,5703,9609,4398,8247};
        int[] b = {57,76,70,64,94,87,92,63,1,4,20,90,98,93,76,13,59,61,84,90,95,89,16,96,18,32,82,82,73,16,66,10,25,23,58,24,82,20,18,92,31,14,10,72,94,87,64,43,51,22,53,18,34,18,12,82,79,76,33,14,53,3,25,65,60,96,15,67,13,32,26,77,88,16,84,52,40,70,95,21,48,46,24,63,92,63,93,21,12,93,62,48,66,21,92,57,32,49,78,80};
        System.out.println(largestNumber(b));
    }

    public static String largestNumber(int[] nums) {
        String[] numStr = new String[nums.length];
        for (int i = 0;i<nums.length;i++){
            numStr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(numStr, new Comparator<String>() {
            Queue<Character> l1 = new ArrayDeque<>();
            Queue<Character> l2 = new ArrayDeque<>();
            @Override
            public int compare(String o1, String o2) {
                char a = Character.MIN_VALUE;
                char b = Character.MIN_VALUE;
                if (o1.length() > 0) {
                    a = o1.charAt(0);
                }
                if (o2.length() > 0) {
                    b = o2.charAt(0);
                }
                if (a > b){
                    l1.clear();
                    l2.clear();
                    return -1;
                }
                else if (a < b){
                    l1.clear();
                    l2.clear();
                    return 1;
                }
                else {
                    if (o1.length() == 1 && o2.length() == 1){
                        if (l1.size() > l2.size()){
                            if (!l2.isEmpty()) return compare(o1, String.valueOf(l2.remove()));
                            else {
                                l1.clear();
                                l2.clear();
                                return -1;
                            }
                        }
                        else if (l1.size() < l2.size()){
                            if (!l1.isEmpty()) return compare(String.valueOf(l1.remove()),o2);
                            else {
                                l1.clear();
                                l2.clear();
                                return 1;
                            }
                        }
                        else {
                            l1.clear();
                            l2.clear();
                            return -1;
                        }
                    }
                    String m = o1.substring(1);
                    l1.add(a);
                    if (m.isEmpty()){
                        m = String.valueOf(l1.remove());
                    }

                    String n = o2.substring(1);
                    l2.add(b);
                    if (n.isEmpty()){
                        n = String.valueOf(l2.remove());
                    }
                    return compare(m, n);
                }
            }
        });

        if (numStr[0].equals("0"))return "0";

        StringBuilder s = new StringBuilder();
        for (String str : numStr){
            s.append(str);
        }

        return s.toString();
    }



    private class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }

    public String largestNumber2(int[] nums) {
        // Get input integers as strings.
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }

        // Sort strings according to custom comparator.
        Arrays.sort(asStrs, new LargerNumberComparator());

        // If, after being sorted, the largest number is `0`, the entire number
        // is zero.
        if (asStrs[0].equals("0")) {
            return "0";
        }

        // Build largest number from sorted array.
        String largestNumberStr = new String();
        for (String numAsStr : asStrs) {
            largestNumberStr += numAsStr;
        }

        return largestNumberStr;
    }
}
