package leet;

import com.sun.org.apache.regexp.internal.RE;

public class Solution657 {
    public static void main(String[] args) {

    }

    public static boolean judgeCircle(String moves) {
        int i = 0;
        int j = 0;
        char[] chars = moves.toCharArray();
        for (int k = 0; k < moves.length(); k++) {
            if (chars[k] == 'U') i--;
            if (chars[k] == 'D') i++;
            if (chars[k] == 'L') j--;
            if (chars[k] == 'R') j++;
        }
        return i == 0 && j == 0;
    }
}
