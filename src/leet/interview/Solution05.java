package leet.interview;

public class Solution05 {
    public static void main(String[] args) {

    }


    public static String replaceSpace(String s) {
        if (s.length() == 0)return s;
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' '){
                str.append("%20");
            }
            else {
                str.append(s.charAt(i));
            }
        }
        return str.toString();
    }
}
