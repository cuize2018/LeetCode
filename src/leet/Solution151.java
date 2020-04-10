package leet;

import netscape.security.UserTarget;

import java.util.*;

public class Solution151 {
    public static void main(String[] args) {
        String s = "the sky is blue";
        System.out.println(reverseWords2(s));

    }

    public static String reverseWords(String s) {
        String[] words = s.split(" ");
        List<String> words_new = new ArrayList<>();
        StringBuilder out = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            if (!words[i].equals("")) {
                words_new.add(words[i]);
            }
        }

        for (int i = 0; i < words_new.size(); i++) {
            out.append(words_new.get(i));
            if (i != words_new.size() - 1) {
                out.append(" ");
            }
        }

        return out.toString();
    }

    public static String reverseWords2(String s) {
        if (s.length() == 0) return "";
        Stack<String> out = new Stack<>();
        StringBuilder str = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) != ' ') {
                StringBuilder word = new StringBuilder();
                while (i < s.length() && s.charAt(i) != ' ') {
                    word.append(s.charAt(i));
                    i++;
                }
                out.push(word.toString());
                out.push(" ");
            }
            i++;
        }
        if (!out.isEmpty()) out.pop();
        while (!out.isEmpty()) {
            str.append(out.pop());
        }
        return str.toString();
    }
}
