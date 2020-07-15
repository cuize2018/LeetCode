package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution6 {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int rows = 3;
        System.out.println(convert(s,rows));
    }
    public static String convert(String s, int numRows) {
        int n = s.length();
        StringBuilder[] list = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            list[i] = new StringBuilder();
        }

        int i = 0;
        while (i < n){
            for (int j = 0; j < numRows; j++) {
                if (i >= n)break;
                list[j].append(s.charAt(i++));
            }
            for (int j = numRows-2; j > 0; j--) {
                if (i >= n)break;
                list[j].append(s.charAt(i++));
            }
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder stringBuilder : list) {
            res.append(stringBuilder);
        }
        return res.toString();
    }
}
