package leet;

public class Solution557 {
    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println(reverseWords(s));
    }

    public static String reverseWords(String s) {
        StringBuilder out = new StringBuilder();
        StringBuilder word = new StringBuilder();
        for (int i = s.length()-1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c != ' '){
                word.append(c);
            }
            else {
                if (out.length() == 0) out.insert(0, word.toString());
                else out.insert(0, word.toString() + " ");
                word = new StringBuilder();
            }
        }
        if (out.length() == 0) out.insert(0, word.toString());
        else out.insert(0, word.toString() + " ");
        return out.toString();
    }
}
