package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution696 {
    public static void main(String[] args) {

    }

    public static int countBinarySubstrings(String s) {
        char last = s.charAt(0);
        int lastCnt = -1;
        int cnt = 1;
        int sum = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == last){
                cnt++;
            }
            else {
                if (lastCnt != -1) {
                    sum += Math.min(cnt, lastCnt);
                }
                lastCnt = cnt;
                cnt = 1;
                last = s.charAt(i);
            }
        }
        if (lastCnt != -1){
            sum += Math.min(cnt, lastCnt);
        }
        return sum;
    }
}
