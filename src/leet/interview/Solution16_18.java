package leet.interview;

public class Solution16_18 {
    public static void main(String[] args) {
        String pattern = "abba";
        String value = "dogcatcatdog";
        Solution16_18 solution16_18 = new Solution16_18();
        System.out.println(solution16_18.patternMatching(pattern, value));
    }

    public boolean patternMatching(String pattern, String value) {
        String[] s = new String[2];
        return help(s, pattern, 0, value, 0);
    }

    private boolean help(String[] s, String pattern, int idxP, String value, int idxV) {
        if (idxP == pattern.length() && idxV == value.length()) return true;
        if (idxP >= pattern.length() || idxV >= value.length()) return false;

        int num = pattern.charAt(idxP) - 'a';
        if (s[num] == null) {
            for (int i = idxV; i <= value.length(); i++) {
                s[num] = value.substring(idxV, i);
                if (s[num] == null || s[num ^ 1] == null || !s[num].equals(s[num ^ 1]) && help(s, pattern, idxP + 1, value, i)) {
                    return true;
                }
            }
            s[num] = null;
            return false;
        } else {
            int end = idxV + s[num].length();
            if (end > value.length() || !value.substring(idxV, end).equals(s[num])) {
                return false;
            }
            return help(s, pattern, idxP + 1, value, end);
        }
    }
}
