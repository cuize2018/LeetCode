package leet;

public class Solution319 {
    public static void main(String[] args) {
        System.out.println(bulbSwitch(9));
    }


    /**
     *当共有n位，且进行到第n轮时，n位数字的前n-1位与共有n-1位，且进行到第n-1轮时的情况一致，即有status[n] = status[n-1] + (0/1)
     * 又因为只有当n能被整开根号时，因数数目才会少一个，如 4 = 1，2，4，其中2*2=4，但是数目只有一个
     * 所以，因为除去因数1，只有当n能被整开根号时，因为因数数目少一个，所以为偶数，所以最后一位变为初始状态1，故数目+1
     * exp：num = 4 factor = {2,4}
     * 1111->1010->1000->1001，最后一位1变了两次，回到1
     * num = 6 factor = {2,3,6}
     * 111,111->101010->100011->100111->100101->100100,最后一位1变了三次，变为0
     */
    private static int bulbSwitch(int n) {
        if (n == 0)return 0;
        if (n == 1)return n;

        int count = 1;
        for (int i = 2;i <= n;i++){
            int a = (int)Math.sqrt(i);
            if (a*a == i){
                count+=1;
            }
        }
        return count;
    }

}
