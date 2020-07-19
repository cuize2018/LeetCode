package leet.interview;

import java.util.*;

public class Solution11 {
    public static void main(String[] args) {
        int[] nums = {3, 1, 3};
        System.out.println(minArray2(nums));
    }

    public static int minArray(int[] numbers) {
        if (numbers.length == 0) return 0;
        if (numbers.length == 1) return numbers[0];

        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                return numbers[i + 1];
            }
        }
        return numbers[0];
    }

    /**
     * 循环二分： 设置 i, j 指针分别指向 numbers 数组左右两端，m = (i + j) // 2为每次二分的中点（ "//" 代表向下取整除法，因此恒有 i <= m < j），可分为以下三种情况：
     * 当 numbers[m] > numbers[j]时： m 一定在 左排序数组 中，即旋转点 x 一定在 [m + 1, j] 闭区间内，因此执行 i = m + 1
     * 当 numbers[m] < numbers[j] 时： m 一定在 右排序数组 中，即旋转点 x 一定在[i, m]闭区间内，因此执行 j = m；
     * 当 numbers[m] == numbers[j] 时： 无法判断 m 在哪个排序数组中，即无法判断旋转点 x 在 [i, m] 还是 [m + 1, j]区间中。
     * 解决方案： 执行 j = j - 1 缩小判断范围 （分析见以下内容）:
     * 例 [1, 0, 1, 1, 1] ：旋转点 x = 1 ，因此 m = 2 在右排序数组中。
     * 例 [1, 1, 1, 0, 1] ：旋转点 x = 3 ，因此 m = 2 在左排序数组中。
     *
     * 返回值： 当 i = j时跳出二分循环，并返回 numbers[i] 即可。
     *
     * @param numbers
     * @return
     */
    public static int minArray2(int[] numbers) {
        if (numbers.length == 0) return 0;
        if (numbers.length == 1) return numbers[0];

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int middle = (left + right) >>> 1;
            if (numbers[middle] < numbers[right]) {
                right = middle;
            } else if (numbers[middle] > numbers[right]) {
                left = middle + 1;
            } else {
                right--;
            }
        }
        return numbers[left];
    }


    public static int minArray3(int[] numbers) {
        if (numbers.length == 0) return 0;
        if (numbers.length == 1) return numbers[0];

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int mid = (left + right) >>> 1;

            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else if (numbers[mid] < numbers[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return numbers[left];
    }
}
