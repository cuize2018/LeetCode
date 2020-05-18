package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution932 {
    public static void main(String[] args) {

    }

    /**
     * 漂亮数组有以下的性质:
     * （1）A是一个漂亮数组，如果对A中所有元素添加一个常数，那么A还是一个漂亮数组。
     * （2）A是一个漂亮数组，如果对A中所有元素乘以一个常数，那么A还是一个漂亮数组。
     * （3）A是一个漂亮数组，如果删除一些A中所有元素，那么A还是一个漂亮数组。
     * （4) A是一个奇数构成的漂亮数组，B是一个偶数构成的漂亮数组，那么A+B也是一个漂亮数组
     * 比如:{1,5,3,7}+{2,6,4,8}={1,5,3,7,2,6,4,8}也是一个漂亮数组。
     * 所以我们假设一个{1-m}的数组是漂亮数组，可以通过下面的方式构造漂亮数组{1-2m}:
     *
     * 对{1-m}中所有的数乘以2, 再-1，构成一个奇数漂亮数组A。如{1,3,2,4},可以得到{1,5,3,7}
     * 对{1-m}中所有的数乘以2,构成一个偶数漂亮数组B,如{1,3,2,4}, 可以得到{2,6,4,8}
     * A+B构成了{1-2m}的漂亮数组。{1,5,3,7}+{2,6,4,8}={1,5,3,7,2,6,4,8}
     * 从中删除不要的数字即可。
     * @param N
     * @return
     */
    public static int[] beautifulArray(int N) {
        if (N == 1) return new int[]{1};
        if (N == 2) return new int[]{1, 2};
        if (N == 3) return new int[]{1, 3, 2};
        if (N == 4) return new int[]{1, 3, 2, 4};

        List<Integer> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        temp.add(1);
        temp.add(3);
        temp.add(2);
        temp.add(4);
        while (temp.size() < N) {
            for (int i = 0; i < temp.size(); i++) {
                temp.set(i, temp.get(i) * 2 - 1);
            }
            int size = temp.size();
            for (int i = 0; i < size; i++) {
                temp.add(temp.get(i) + 1);
            }
        }
        for (int num : temp) {
            if (num <= N) ans.add(num);
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = ans.get(i);
        }
        return res;
    }
}
