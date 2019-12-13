package leet;

/**
 * 1-9有9个数，10-99有20X9个数字，100-999有300X9个数字，1000-9999有4000X9个数字；以此类推；
 * 设置一个标志位i，每一个区间都有固定的标志位，例如1-9是1，10--99是2，以此类推；然后用n减去每个区间的值，知道确定n在哪个区间；
 * 在得到区间中确定的数字，将其变为string型，然后就可以得到确定的数字。
 */
class Solution400 {
    public int findNthDigit(int n) {
        int i = 1;
        while(n>i*(Math.pow(10,i-1))*9)
        {
            n-=i*(Math.pow(10,i-1))*9;  //小于区间的值要减去，知道得到确定的区间
            i++;                   //标志位++；
        }
        int am_num = (n-1)/i + (int)Math.pow(10,i-1);  //确定区间中数字；
        String a = String.valueOf(am_num);      //将数字变为string，好取得确定的数字；

        if(n%i==0)return (a.charAt(i-1)-'0');
        return (a.charAt(n%i-1)-'0');
    }
};

