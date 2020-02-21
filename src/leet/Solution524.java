package leet;

import java.util.*;

public class Solution524 {
    public static void main(String[] args) {

        String s = "abpcplea";
//        String[] temp = {"ale","apple","monkey","plea"};
        String[] temp = {"b","a","c"};
        List<String> d = new ArrayList<>(Arrays.asList(temp));


        System.out.println(findLongestWord(s,d));
    }

    public static String findLongestWord(String s, List<String> d) {
        String out_str = "";
        for (String str : d){
            if (isSubSeq(str, s)){
                if (str.length() > out_str.length()){
                    out_str = str;
                }
                else if (str.length() == out_str.length()){
                    if (str.compareTo(out_str) < 0){
                        out_str = str;
                    }
                }
            }
        }
        return out_str;
    }

    //a是否是b的子序列
    public static boolean isSubSeq(String a, String b){
        int j = 0;
        for (int i = 0;i < b.length() && j < a.length();i++){
            if (a.charAt(j) == b.charAt(i)){
                j++;
            }
        }

        return j==a.length();
    }
}
