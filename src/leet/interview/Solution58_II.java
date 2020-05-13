package leet.interview;

public class Solution58_II {
    public static void main(String[] args) {

    }

    public static String reverseLeftWords(String s, int n) {
        return  s.substring(n) + s.substring(0,n);
    }
}
