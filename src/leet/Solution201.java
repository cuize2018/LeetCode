package leet;

public class Solution201 {
    public static void main(String[] args){
        int m = 5;
        int n = 7;
        System.out.println(rangeBitwiseAnd2(m,n));
    }

    public static int rangeBitwiseAnd(int m, int n) {
        int res = 0;
        for (int i = 0;i <= 31;i++){
            int mask = Integer.MAX_VALUE << i;

            if ((mask & m) == (mask & n)){
                res = mask & m;
                break;
            }
        }
        return res;
    }

    /**
     * 其实假若m和n的前x位完全一样的话，那么中间的数即使变化也是在后面剩下那几位变化，
     * 也就是[m, n]区间的所有数前x位都相同，那么我们只要把m和n的类似公共前缀位找出来即为答案。
     */
    public static int rangeBitwiseAnd2(int m, int n) {
        int i = 0;
        while (m != n){
            m = m >> 1;
            n = n >> 1;
            i++;
        }

        return m << i;
    }

}
