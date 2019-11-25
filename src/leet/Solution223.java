package leet;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;

public class Solution223 {
    public static void main(String[] args){
        System.out.println(computeArea(-2 ,-2 ,2 ,2 ,-1 ,4 ,1 ,6));
    }

    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int s1 = Math.abs(A-C)*Math.abs(B-D);
        int s2 = Math.abs(E-G)*Math.abs(F-H);

        int slap = 0;

        int[] x = new int[4];
        int[] y = new int[4];

        x[0] = A;x[1] = C;x[2] = E;x[3] = G;
        y[0] = B;y[1] = D;y[2] = F;y[3] = H;

        Arrays.sort(x);
        Arrays.sort(y);

        if ((x[1] >= A && x[1] <= C) && (x[2] >= A && x[2] <= C)){
            if ((y[1] >= B && y[1] <= D) && (y[2] >= B && y[2] <= D)){
                int x_t = Math.abs(x[1]-x[2]);
                int y_t = Math.abs(y[1]-y[2]);

                slap =  x_t*y_t;
            }
        }
        return s1+s2-slap;
    }

    /**
     * 首先，我们调整两个矩形，让第一个矩形是靠最左边的；
     * 其次，先考虑没有重叠的情况，有三种情况，如图所示：
     * rectangle1的下边都大于（等于）rectangle2的上边，即 B >= H
     * rectangle1的右边都小于（等于）rectangle2的左边，即 C >= E
     * rectangle1的上边都小于（等于）rectangle2的下边，即 F >= D
     * 最后， 要考虑重叠的情况，因为一定有重叠，所以可以找到上下左右边界
     * 上边界，取两个矩形的上边界的最小值
     * 下边界，取两个矩形的下边界的最大值
     * 左边界，取两个矩形的左边界的最大值
     * 右边界，取两个矩形的右边界的最小值
     */
    public static int computeArea2(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (A > E){
            return computeArea2(E,F,G,H,A,B,C,D);
        }
        int s1 = Math.abs(A-C)*Math.abs(B-D);
        int s2 = Math.abs(E-G)*Math.abs(F-H);

        if(B >= H || C <= E || D <= F){
            return s1+s2;
        }
        else {
            int a = Math.max(A,E);
            int b = Math.max(B,F);
            int c = Math.min(C,G);
            int d = Math.min(D,H);
            return s1+s2-Math.abs(a-c)*Math.abs(b-d);
        }
    }
}
