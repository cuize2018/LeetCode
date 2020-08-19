package leet;

public class Solution1055 {
    public static void main(String[] args) {
        String a  = "adbsc";
        String b = "addddddddddddsbc";

        System.out.println(shortestWay(a,b));
    }

    public static int shortestWay(String source, String target) {
        int j = 0;
        int cnt = 0;
        while (j < target.length()){
            cnt++;
            int t = helper(target, j, source);
            if (t == j)return -1;
            j = t;
        }
        return cnt;
    }

    private static int helper(String target, int j, String source) {
        int i = 0;
        while (j < target.length() && i < source.length()){
            if (source.charAt(i) == target.charAt(j)){
                i++;
                j++;
            }
            else {
                i++;
            }
        }
        return j;
    }
}
