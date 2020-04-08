package leet;

public class Solution461 {
    public static void main(String[] args) {
        int x = 1;
        int y = 4;
        System.out.println(hammingDistance(x,y));
    }

    public static int hammingDistance(int x, int y) {
        int m = x ^ y;//先按位异或，相同位为0，不同位为1，即结果的每个为1的位都是不同的位
        int len = 0;
        while (m != 0) {
            double temp = (double) m / 2D;
            m = m >>> 1;
            if (temp != m) len++;//若m/2 == m>>>1,则最右位为0；否则最右位为1
                                 //exp: 101: 101/2 = 2.5; 101>>>1 = 10 = 2; 2.5 != 2
                                 //exp: 100: 100/2 = 2.0; 100>>>1 = 10 = 2; 2.0 == 2
        }
        return len;
    }
}
