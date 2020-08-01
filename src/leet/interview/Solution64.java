package leet.interview;

public class Solution64 {
    public static void main(String[] args) {
        int n = 9;
        System.out.println(sumNums(n));
    }

    public static int sumNums(int n) {
        int sum = n;
        boolean f = n > 0 && (sum += sumNums(n - 1)) > 0;
        return sum;
    }
}
