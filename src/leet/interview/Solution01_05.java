package leet.interview;

public class Solution01_05 {
    public static void main(String[] args) {

    }

    public boolean oneEditAway(String first, String second) {
        if (first.length() < second.length()) {
            return oneEditAway(second, first);
        }
        if (first.length() - second.length() > 1) {
            return false;
        }

        int i = 0;
        int j = 0;
        while (j < second.length() && first.charAt(i) == second.charAt(j)) {
            i++;
            j++;
        }
        if (j == second.length()) return true;

        //delete and insert
        int ti = i + 1;
        int tj = j;
        while (ti < first.length() && tj < second.length()
                && first.charAt(ti) == second.charAt(tj)) {
            ti++;
            tj++;
        }
        if (ti == first.length() && tj == second.length()) return true;

        //replace
        ti = i + 1;
        tj = j + 1;
        while (ti < first.length() && tj < second.length()
                && first.charAt(ti) == second.charAt(tj)) {
            ti++;
            tj++;
        }
        if (ti == first.length() && tj == second.length()) return true;

        return false;
    }
}
