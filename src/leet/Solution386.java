package leet;

import java.util.*;

public class Solution386 {
    public static void main(String[] args) {
        int n = 13;
        System.out.println(lexicalOrder(n));
    }

    public static List<Integer> lexicalOrder(int n) {
        List<Integer> out = new ArrayList<>();
        List<String> nums = new ArrayList<>();
        for (int i = 1;i<=n;i++){
            nums.add(String.valueOf(i));
        }

        nums.sort(new Comparator<String>() {
            @Override
            public int compare(String num1, String num2) {
                for (int i = 0; i < Math.min(num1.length(), num2.length()); i++) {
                    if (num1.charAt(i) > num2.charAt(i)) {
                        return 1;
                    } else if (num1.charAt(i) < num2.charAt(i)) {
                        return -1;
                    }
                }
                if (num1.length() > num2.length()) {
                    return 1;
                } else if (num1.length() < num2.length()) {
                    return -1;
                } else return 0;
            }
        });

        for (String num : nums){
            out.add(Integer.parseInt(num));
        }
        return out;

    }
}
