package leet.lcp;

public class Solution06 {
    public static void main(String[] args) {

    }

    public static int minCount(int[] coins) {
        int n = coins.length;
        if (n == 0)return 0;
        int sum = 0;
        for (int coin : coins) {
            sum += func(coin);
        }
        return sum;
    }

    private static int func(int coin) {
        int sum = 0;
        while (coin > 1){
            coin -= 2;
            sum++;
        }
        return coin == 0?sum:sum+1;
    }
}
