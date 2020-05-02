package leet.interview;

import java.util.Map;

public class Solution43 {
    int[] mem;

    public static void main(String[] args) {

    }

    public static int countDigitOne(int n) {
        return f(n);
    }

    /**
     * f(n))函数的意思是1～n这n个整数的十进制表示中1出现的次数，
     * 将n拆分为两部分，最高一位的数字high和其他位的数字last，分别判断情况后将结果相加，看例子更加简单。
     * exp-1:
     * 例子如n=1234，high=1, pow=1000, last=234
     * 可以将数字范围分成两部分1~999和1000~1234
     *      (1)1~999这个范围1的个数是f(pow-1)
     *      (2)1000~1234这个范围1的个数需要分为两部分:
     *          1.千分位是1的个数：千分位为1的个数刚好就是234+1(last+1)，注意，这儿只看千分位，不看其他位
     *          2.其他位是1的个数：即是234中出现1的个数，为f(last)
     *
     * ans = f(pow-1) + last + 1 + f(last);
     *
     * exp-2:
     * 例子如3234，high=3, pow=1000, last=234
     * 可以将数字范围分成两部分1~999，1000~1999，2000~2999和3000~3234
     *      (1)1~999这个范围1的个数是f(pow-1)
     *      (2)1000~1999这个范围1的个数是：
     *           1.千分位是1的个数：千分位为1的个数刚好就是pow，注意，这儿只看千分位，不看其他位
     *           2.其他位是1的个数：即是999中出现1的个数，为f(pow-1)
     *      (3)2000~2999这个范围1的个数是f(pow-1)
     *      (4)3000~3234这个范围1的个数是f(last)
     *
     * ans = high*f(pow-1) + pow + f(last)
     *
     * @param n
     * @return
     */
    private static int f(int n) {
        if (n <= 0) return 0;

        String num = String.valueOf(n);
        int high =  num.charAt(0) - '0';
        int pow = (int) Math.pow(10, num.length() - 1);
        int last = n - high * pow;

        if (high != 1) {
            return high * f(pow - 1) + pow + f(last);
        } else {
            return f(pow - 1) + last + 1 + f(last);
        }
    }


    public int countDigitOne2(int n) {
        mem = new int[11];
        int pow = 1;
        //mem[i] 代表pow = Math.pow(10, i)的[0,pow-1]的1的个数
        for (int i = 1; i < mem.length; i++) {
            mem[i] = 10 * mem[i - 1] + pow;
            pow *= 10;
        }
        return f2(n);
    }

    private int f2(int n) {
        if (n <= 0) return 0;

        String num = String.valueOf(n);
        int high = num.charAt(0) - '0';
        int pow = (int) Math.pow(10, num.length() - 1);
        int last = n - high * pow;

        if (high != 1) {
            return high * mem[num.length() - 1] + pow + f2(last);
        } else {
            return mem[num.length() - 1] + last + 1 + f2(last);
        }
    }
}
