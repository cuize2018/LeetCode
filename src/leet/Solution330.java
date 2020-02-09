package leet;

public class Solution330 {
    public static void main(String[] args) {

    }

    /**
     * 贪心法
     *     尽量利用数组中的元素去覆盖更多的范围，如果不能覆盖，就增加范围之外的一个数字，扩大覆盖范围
     * 设置一个变量overlapped表示当前的数字能够表示的范围，我们希望能够尽量利用数组中的数字，使得覆盖范围大于等于n
     *
     * 初始值：overlapped = 0    覆盖0
     * 然后开始判断，数组中提供的元素是否小于等于 overlapped +1？
     *   如果是，表示覆盖范围可以向前扩展
     *   如果不是，那么数字 overlapped+1 不能被覆盖，这时候就需要新增这个数字，同时向后扩展覆盖范围，直到范围大于等于n截止
     *
     * @param nums
     * @param n
     * @return
     */
    public static int minPatches(int[] nums, int n) {
        int length = nums.length;
        long overlapped = 0;
        int pos = 0;
        int count = 0;

        while (overlapped < n){
            if (pos < length){
                if (nums[pos] <= overlapped+1){
                    overlapped += nums[pos];
                    pos++;
                }
                else {
                    count++;
                    overlapped += overlapped+1;
                }
            }
            else {
                count++;
                overlapped += overlapped+1;
            }
        }
        return count;
    }
}
