package leet;

public class Solution680 {
    public static void main(String[] args) {
        String s = "aba";
        System.out.println(validPalindrome(s));
    }

    public static boolean validPalindrome(String s) {
        return help(s, 0, s.length()-1, false);
    }

    private static boolean help(String s, int left, int right, boolean hasDelete) {
        while (left < right){
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);
            if (leftChar != rightChar){
                if (hasDelete)return false;

                boolean leftMov = help(s, left + 1, right, true);
                boolean rightMov = help(s, left, right - 1, true);
                return leftMov || rightMov;
            }
            else {
                left++;
                right--;
            }
        }
        return true;
    }
}
