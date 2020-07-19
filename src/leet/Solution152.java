package leet;

import com.sun.jmx.snmp.SnmpNull;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.omg.CORBA.MARSHAL;
import sun.nio.cs.ext.MacThai;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Solution152 {
    public static void main(String[] args) {
        int[] nums = {-2, 0, -1};
        System.out.println(maxProduct3(nums));
    }

    /**
     * 我们先定义一个数组 dpMax，用 dpMax[i] 表示以第 i 个元素的结尾的子数组，乘积最大的值，也就是这个数组必须包含第 i 个元素。
     * <p>
     * 那么 dpMax[i] 的话有几种取值。
     * <p>
     * 当 nums[i] >= 0 并且dpMax[i-1] > 0，dpMax[i] = dpMax[i-1] * nums[i]
     * 当 nums[i] >= 0 并且dpMax[i-1] < 0，此时如果和前边的数累乘的话，会变成负数，所以dpMax[i] = nums[i]
     * 当 nums[i] < 0，此时如果前边累乘结果是一个很大的负数，和当前负数累乘的话就会变成一个更大的数。
     * 所以我们还需要一个数组 dpMin 来记录以第 i 个元素的结尾的子数组，乘积最小的值。
     * <p>
     * 当dpMin[i-1] < 0，dpMax[i] = dpMin[i-1] * nums[i]
     * 当dpMin[i-1] >= 0，dpMax[i] = nums[i]
     * 当然，上边引入了 dpMin 数组，怎么求 dpMin 其实和上边求 dpMax 的过程其实是一样的。
     * <p>
     * 按上边的分析，我们就需要加很多的 if else来判断不同的情况，这里可以用个技巧。
     * <p>
     * 我们注意到上边dpMax[i] 的取值无非就是三种，dpMax[i-1] * nums[i]、dpMin[i-1] * nums[i] 以及 nums[i]。
     * <p>
     * 所以我们更新的时候，无需去区分当前是哪种情况，只需要从三个取值中选一个最大的即可。
     * <p>
     * dpMax[i] = max(dpMax[i-1] * nums[i], dpMin[i-1] * nums[i], nums[i]);
     * 求 dpMin[i] 同理。
     * <p>
     * dpMin[i] = min(dpMax[i-1] * nums[i], dpMin[i-1] * nums[i], nums[i]);
     * 更新过程中，我们可以用一个变量 max 去保存当前得到的最大值。
     * <p>
     * 链接：https://leetcode-cn.com/problems/maximum-product-subarray/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--36/
     *
     * @param nums
     * @return
     */
    public static int maxProduct2(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] dpMax = new int[n];
        dpMax[0] = nums[0];
        int[] dpMin = new int[n];
        dpMin[0] = nums[0];
        int max = nums[0];

        for (int i = 1; i < n; i++) {
            dpMax[i] = Math.max(dpMin[i - 1] * nums[i], Math.max(dpMax[i - 1] * nums[i], nums[i]));
            dpMin[i] = Math.min(dpMin[i - 1] * nums[i], Math.min(dpMax[i - 1] * nums[i], nums[i]));

            max = Math.max(max, dpMax[i]);
        }
        return max;
    }

    public static int maxProduct3(int[] nums) {
        if (nums.length == 0)return 0;
        int n = nums.length;
        int[] dpMax = new int[n];
        int[] dpMin = new int[n];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];

        int max = dpMax[0];
        for (int i = 1; i < n; i++) {
            dpMax[i] = Math.max(dpMax[i-1] * nums[i], Math.max(dpMin[i-1]* nums[i], nums[i]));
            dpMin[i] = Math.min(dpMax[i-1] * nums[i], Math.min(dpMin[i-1]* nums[i], nums[i]));

            max = Math.max(max, dpMax[i]);
        }
        return max;
    }
}
