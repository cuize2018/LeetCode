package leet;

public class Solution190 {
    public static void main(String[] args) {

    }

    public static int reverseBits(int n) {
        int v = 0;
        boolean first = true;
        int cnt = 0;
        while (cnt < 32){
            int x = n & 1;
            if (first){
                v = x;
                first = false;
            }
            else {
                v = v << 1;
                v += x;
            }
            n = n >>> 1;
            cnt++;
        }
        return v;
    }

    public static int reverseBits2(int n) {
        int v = 1;
        long k = Integer.MAX_VALUE + 1L;

        for (int i = 0; i < 16; i++) {
            n = swap(n, v, k);
            v = v << 1;
            k = k >>> 1;
        }
        return n;
    }

    private static int swap(long n, int i, long j) {
        long low = n & i;
        long high = n & j;
        if (low == 0 && high == 0)return (int)n;
        if (low != 0 && high != 0)return (int)n;

        if (high == 0){
            n = n ^ i;
            n = n | j;
        }
        if (low == 0){
            n = n ^ j;
            n = n | i;
        }
        return (int)n;
    }
}
