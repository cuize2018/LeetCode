package leet;

public class Solution397 {
    public static void main(String[] args) {
        int n = 2147483647;
        int a = 12222;
        System.out.println(integerReplacement(n));
    }

    public static int integerReplacement(int n) {
        if (n == 1)return 0;
        //if (n == Integer.MAX_VALUE) return 32;

        int out;
        if (n%2 == 0){
            out = integerReplacement(n/2) + 1;
        }
        else {
            if (n == Integer.MAX_VALUE){
                out = Math.min(integerReplacement(n - 1), integerReplacement(n>>1 + 1)+1)+1;
            }
            else {
                out = Math.min(integerReplacement(n - 1), integerReplacement(n + 1)) + 1;
            }
        }

        return out;
    }

    /**
     * 1、偶数没有任何疑问，无符号右移即可。
     * 2、奇数时的两个选择，其实有迹可循：
     * 例子：101111 - - > (110000 or 101110)?
     * 首先明白，使用n + 1或n - 1 替代 n 都会计一步，可以理解为“转化1代价”，那么最舒适的情况当然是1越少越好(1000000[注：为偶数])，一路右移通畅无阻。
     * 如例子中所示，此时选择 n + 1 一次可以处理掉 (4 - 1 = )3 个 1，而选择n - 1稍后仍然要在其他的3 个 1 上消耗时间。显而易见前者是更高效的。
     * 但这里要注意两个特殊的情况：
     * 3的特殊性：按照上面偶数的处理方式 n + 1 和 n - 1都会消耗掉一个 1 ，
         * 但 n + 1 方式下路线为：3 - > 4 - > 2 - > 1，
         * n - 1 方式下路线为3 - > 2 - > 1;所以将此时的累计值直接加二处理掉即可。
     * Integer.MAX_VALUE(2147483647)溢出兼容
         * Integer.MAX_VALUE会被算法使用奇数处理逻辑 +1 导致溢出为Integer.MIN_VALUE(0x80000000),
         * 此时做31次无符号右移即可得到1，这也是选用无符号右移的原因。
     * @param n
     * @return
     */
    public static int integerReplacement2(int n) {
        if (n == 1)return 0;
        //if (n == Integer.MAX_VALUE) return 32;
        int count = 0;
        while (n != 1){
            //与运算判断最后一位来区分奇偶
            if ((n & 1) == 0){
                //偶数直接无符号右移，
                //2147483647 会被奇数处理算法加一溢出为负数，
                //若选用带符号右移将无法回到1
                n = n >>> 1;
                count++;
            }
            else {
                //识别奇数的上一位是否为1，即 以 01 结尾(xxxx01)还是以11结尾(xxxx11)
                if ((n & 2) == 0){ //01结尾
                    n--;
                    count++;
                }
                else { //11结尾
                    if (n == 3){
                        count += 2;
                        break;
                    }
                    else {
                        n++;
                        count++;
                    }
                }
            }
        }
        return count;
    }

}
