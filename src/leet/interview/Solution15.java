package leet.interview;

public class Solution15 {
    public static void main(String[] args) {
        int n = 8;
        System.out.println(hammingWeight(n));
    }

    public static int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            int a = n & 1;
            count += a;
            n = n >>> 1;
        }
        return count;
    }
}
