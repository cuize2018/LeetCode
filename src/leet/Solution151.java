package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution151 {
    public static void main(String[] args){
        String s = "the sky is blue";
        System.out.println(reverseWords(s));

    }

    public static String reverseWords(String s) {
        String[] words = s.split(" ");
        List<String> words_new = new ArrayList<>();
        StringBuilder out = new StringBuilder();

        for (int i = words.length-1;i>=0;i--){
            if (!words[i].equals("")) {
                words_new.add(words[i]);
            }
        }

        for (int i = 0;i<words_new.size();i++){
            out.append(words_new.get(i));
            if (i != words_new.size()-1){
                out.append(" ");
            }
        }

        return out.toString();
    }
}
