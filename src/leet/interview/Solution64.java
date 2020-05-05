package leet.interview;

public class Solution64 {
    public static void main(String[] args) {
        int n = 9;
        System.out.println(sumNums(n));
    }

    public static int sumNums(int n) {
        if (n == 1)return 1;
        return sumNums(n-1) + n;
    }
}
