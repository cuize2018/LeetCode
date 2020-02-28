package leet;

import java.util.Arrays;

public class Solution556 {
    public static void main(String[] args) {
        int n = 1999999999;
        System.out.println(nextGreaterElement2(n));
    }

    public static int nextGreaterElement(int n) {
        String val = String.valueOf(n);
        char[] nums = val.toCharArray();
        Arrays.sort(nums);
        if (nums[nums.length - 1] == val.charAt(0)) {
            return -1;
        }

        long mov = n + 1;
        while (mov <= Integer.MAX_VALUE) {
            String val_temp = String.valueOf(mov);
            if (val_temp.length() > val.length()) {
                break;
            }

            char[] nums_temp = val_temp.toCharArray();

            Arrays.sort(nums_temp);
            if (Arrays.equals(nums, nums_temp)) {
                return (int) mov;
            }
            mov++;
        }
        return -1;
    }

    /**
     * 首先我们观察到任意降序的序列，不会有更大的排列出现。
     * 比方说，下面数列就没有下一排列：9,6,3,1
     * <p>
     * 我们需要从右往左找到第一对连续的数字 a[i] 和 a[i+1]满足 a[i] < a[i+1]。
     * 到当前位置i，a[i]右边的数字没办法产生一个更大的排列，因为右边的数字是降序的。所以我们需要重新排布a[i]到最右边的数字来得到下一个排列。
     * <p>
     * 那么怎样排布能得到下一个更大的数字呢？
     * 我们想得到恰好大于当前数字的下一个排列，所以我们需要用恰好大于a[i]的数字去替换掉a[i]，比方说我们让这个数字为 a[j]。
     * 我们将 a[i] 和 a[j] 交换，我们现在在下标为i的地方得到了正确的数字，
     * ""此时[i,end]已经是一个降序排列，在字符a[i]开始的序列中，字典序最小的序列是a[i]右边的字符都升序排列。""
     * 所以反转[i+1,end]的数组即可。
     * exp: v4 = [ ‘6’, ‘2’ , ‘3’ , ‘4’ ,’5′] 字典序比 v2=[‘5′,’6′,’4′,’3′,’2’] 刚好大一个位置。
     *
     * @param n
     * @return
     */


    /**
     * 先将正整数n转成字符串s。字符串s其实是’0′-‘9’的字符序列v。
     * 例如：数字100
     * 其对应的字符串s=”100″。字符序列v=[‘1′,’0′,’0’]。
     *
     * 字符序列v中全部字符的全排列，组成一个集合T。 v是T中的一个元素。子典序比v大一个的序列w也是T中的元素。
     * 那么，本题等价于求w。
     *
     * 例如：v=[‘1′,’5′,’6′,’4′,’3′,’2’]

     * 从右向左看，’2’，’3’，’4’，’6’是递增的。单独看v1=[ ‘6’,’4′,’3′,’2′ ]。v1已经是这4个字符组成的字典序最大的序列。
     *
     * 这说明，降序的序列已经是字典序最大的了。
     *
     * 突破口就在降序的部分之前，找到第一个违反降序的数字。
     *
     * ‘5’ < ‘6’ ，数字’5’使序列不再降序。单独看v2=[‘5′,’6′,’4′,’3′,’2’]。
     *
     * 根据上面的讨论，’5’之后的v1= [ ‘6’,’4′,’3′,’2′ ] 不能再增大了。
     *
     * 这就要将’5’的位置换上比’5’大的最小字符。
     *
     * 在v1中，从右向左遍历，比’5’大的 最小 字符是’6’。
     *
     * 交换’5’和’6’，v3= [ ‘6’, ‘5’,’4′,’3′,’2′] 。
     *
     * 在字符’6’开始的序列中，字典序最小的序列是’6’右边的字符都升序排列。
     *
     * 得出v4= [ ‘6’, ‘2’ , ‘3’ , ‘4’ ,’5′]
     *
     * v4 = [ ‘6’, ‘2’ , ‘3’ , ‘4’ ,’5′] 字典序比 v2=[‘5′,’6′,’4′,’3′,’2’] 刚好大一个位置。
     * @param n
     * @return
     */
    public static int nextGreaterElement2(int n) {
        String val = String.valueOf(n);
        char[] nums = val.toCharArray();

        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }

        if (i < 0) return -1;

        for (int j = nums.length - 1; j > i; j--) {
            if (nums[j] > nums[i]) {
                //swap nums[j-1],nums[i]
                char temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                break;
            }
        }
        StringBuilder str = new StringBuilder();
        for (int k = 0; k <= i; k++) {
            str.append(nums[k]);
        }
        int reverse_index = nums.length - 1;
        for (int k = i + 1; k < nums.length; k++) {
            str.append(nums[reverse_index]);
            reverse_index--;
        }

        try {
            return Integer.parseInt(str.toString());
        } catch (Exception e) {
            return -1;
        }
    }
}
