package leet.interview;

import java.util.Arrays;
import java.util.Comparator;

public class Solution45 {
    public static void main(String[] args) {

    }

    public String minNumber(int[] nums) {
        String[] a = new String[nums.length];
        for (int i = 0; i < nums.length;i++) {
            a[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(a, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()){
                    for (int i = 0; i < o1.length(); i++) {
                        if (o1.charAt(i) < o2.charAt(i))return -1;
                        else if (o1.charAt(i) > o2.charAt(i))return 1;
                    }
                }
                else {
                    int len = Math.min(o1.length(), o2.length());
                    for (int i = 0; i < len; i++) {
                        if (o1.charAt(i) < o2.charAt(i))return -1;
                        else if (o1.charAt(i) > o2.charAt(i))return 1;
                    }

                    String a = o1 + o2;
                    String b = o2 + o1;
                    for (int i = len; i < a.length(); i++) {
                        if (a.charAt(i) == b.charAt(i))continue;;

                        if (a.charAt(i) < b.charAt(i))return -1;
                        else return 1;
                    }
                }
                return 0;
            }
        });
        return String.join("", a);
    }
}
