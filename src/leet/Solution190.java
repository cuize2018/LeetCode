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
}
