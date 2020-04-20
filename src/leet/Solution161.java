package leet;

import java.util.Arrays;

public class Solution161 {
    public static void main(String[] args) {

    }

    public static boolean isOneEditDistance(String s, String t) {
        char[] s_array = s.toCharArray();
        char[] t_array = t.toCharArray();

        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s_array[i] != t_array[i]){
                if (s.length() == t.length()){
                    return s.substring(i+1).equals(t.substring(i+1));
                }
                else if (s.length() > t.length()){
                    return s.substring(i+1).equals(t.substring(i));
                }
                else {
                    return s.substring(i).equals(t.substring(i+1));
                }
            }
        }
        return Math.abs(s.length()-t.length()) == 1;
    }
}
