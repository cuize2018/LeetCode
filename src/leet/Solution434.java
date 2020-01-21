package leet;

public class Solution434 {
    public static void main(String[] args) {

    }

    public int countSegments(String s) {
        if (s.length() == 0)return 0;

        String[] a = s.split(" ");
        int count = 0;
        for (String str : a) {
            if (!"".equals(str)) {
                count++;
            }
        }

        return count;
    }
}
