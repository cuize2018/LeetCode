package leet;

import java.util.Arrays;

public class Solution66 {
    public static void main(String[] args) {
        int[] a = {9, 9, 9};
        System.out.println(Arrays.toString(plusOne(a)));
    }

    public static int[] plusOne(int[] digits) {
        int len = digits.length;
        if (len == 0) return new int[0];
        int[] res = new int[len];

        int add = 0;
        for (int i = len - 1; i >= 0; i--) {
            int val = digits[i] + add;
            if (i == len - 1) val++;

            if (val >= 10) {
                val -= 10;
                add = 1;
            } else {
                add = 0;
            }
            res[i] = val;
        }
        if (add != 0) {
            int[] temp = new int[len + 1];
            System.arraycopy(res, 0, temp, 1, len);
            temp[0] = add;

            return temp;
        }
        return res;
    }
}
