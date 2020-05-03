package leet;

/**
 * 1-9有9个数，10-99有20*9个数字，100-999有300*9个数字，1000-9999有4000*9个数字；以此类推；
 * 设置一个标志位i，每一个区间都有固定的标志位，例如1-9是1，10--99是2，以此类推；然后用n减去每个区间的值，直到确定n在哪个区间；
 * 在得到区间中确定的数字，将其变为string型，然后就可以得到确定的数字。
 */
class Solution400 {
    public static int findNthDigit2(int n) {
        if (n < 10) return n;
        int i = 1;
        //exp: n = 19
        while (n > i * Math.pow(10, i - 1) * 9) {
            n -= i * Math.pow(10, i - 1) * 9;//小于区间的值要减去，知道得到确定的区间
            i++; //标志位++；
        }
        //n = 10;(代表从10^1开始的第10个数字)     i = 2;(代表该数字所在区间位2位数)
        //n:        1 2 | 3 4 | 5 6 | 7 8 | 9 10    exp:[1,2]->10 [3,4]->11 [5,6]->12 ....
        //n-1:      0 1 | 2 3 | 4 5 | 6 7 | 8 9
        //(n-1)/2:  0 0 | 1 1 | 2 2 | 3 3 | 4 4
        int num = (n - 1) / i + (int) Math.pow(10, i - 1); //确定区间中数字；
        //num = 14

        String str = String.valueOf(num);
        if (n % i == 0) return str.charAt(str.length() - 1) - '0';//当n可以整除i时，表示是这个数字的最后一位
        return str.charAt(n % i - 1) - '0';//exp: (9%2) - 1 = 0
    }
};

