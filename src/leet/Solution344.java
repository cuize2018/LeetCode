package leet;

public class Solution344 {
    public static void main(String[] args) {

    }

    public static void reverseString(char[] s) {
        if (s.length <= 1) return;

        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = temp;
        }
    }
}
