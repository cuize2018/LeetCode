package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution914 {
    public static void main(String[] args) {
        int[] a = {1,1,2,2,2,2};
        System.out.println(hasGroupsSizeX(a));
    }

    public static boolean hasGroupsSizeX(int[] deck) {
        if (deck.length == 1)return false;

        int[] nums = new int[10000];
        int min_num = 100000;
        int min_count = 100000;

        for (int num : deck) {
            nums[num]++;
            if (num == min_num){
                min_count = nums[num];
            }
            if (nums[num] < min_count) {
                min_num = num;
                min_count = nums[num];
            }
        }

        for (int groupNum = min_count; groupNum > 1; groupNum--) {
            boolean flag = true;
            if (min_count % groupNum == 0){
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i]!=0 && i != min_num){
                        if (nums[i] % groupNum != 0){
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag)return true;
            }
        }
        return false;

    }

    /**
     * 由于方法一已经提及 X 一定为 \textit{count}_i的约数，这个条件是对所有牌中存在的数字 i 成立的，所以我们可以推出，只有当 X 为所有 {count}_i
     * 的最大公约数的约数时，才存在可能的分组。
     *
     * 公式化来说，我们假设牌中存在的数字集合为 a, b, c, d, e，那么只有当 X 为
     * gcd({count}_a,{count}_b,{count}_c,{count}_d, {count}_e)的约数时才能满足要求。
     *
     * 因此我们只要求出所有{count}_i最大公约数 g，判断 g 是否大于等于 2 即可，如果大于等于 2，则满足条件，否则不满足。
     * @param deck
     * @return
     */
    public static boolean hasGroupsSizeX2(int[] deck) {
        if (deck.length == 1) return false;
        int[] nums = new int[10000];
        for (int num : deck) {
            nums[num]++;
        }

        int g = -1;
        for (int i = 0; i < 10000; i++) {
            if (nums[i] > 0) {
                if (g == -1) {
                    g = nums[i];
                } else {
                    g = gcd(g, nums[i]);
                }
            }
        }

        return g >= 2;
    }

    private static int gcd(int x, int y) {
        return x == 0 ? y : gcd(y%x, x);
    }


}
