package leet;

import java.util.Arrays;

public class Solution1300 {
    public static void main(String[] args) {
        int[] arr = {4, 9, 3};
        int target = 10;
        System.out.println(findBestValue(arr, target));
    }

    /**
     * value -> [0, arr[len-1]]
     * 由于将数组 arr 中的等于 x 的值变为 x 并没有改变原来的值，因此上述操作可以改为：
     * 当枚举到 value = x 时，我们需要将数组 arr 中所有小于 x 的值保持不变，所有大于等于 x 的值变为 x。
     * 要实现这个操作，我们可以将数组 arr 先进行排序，随后进行二分查找，找出数组 arr 中最小的大于等于 x 的元素 arr[i]。此时数组的和变为
     * arr[0] + ... + arr[i - 1] + x * (n - i)
     *
     * @param arr
     * @param target
     * @return
     */
    public static int findBestValue(int[] arr, int target) {
        int n = arr.length;
        Arrays.sort(arr);
        int[] sum = new int[n + 1];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
        }

        int high = arr[arr.length - 1];
        int res = 0;
        int min = target;
        for (int x = 0; x <= high; x++) {
            int idx = Arrays.binarySearch(arr, x);
            if (idx < 0) {
                idx = -idx - 1;
            }
            int curr = sum[idx] + (n - idx) * x;
            if (Math.abs(curr - target) < min) {
                res = x;
                min = Math.abs(curr - target);
            }
        }
        return res;
    }

    /**
     * 可以发现，在[0,max(arr)]（即方法一中确定的上下界）的范围之内，随着 value 的增大，数组的和是严格单调递增的。
     * 这样一来，一定存在唯一的一个 value 值，使得数组的和最接近且不大于 target。
     * 并且由于严格单调递增的性质，我们可以通过二分查找的方法，找到这个 value 值，记为 value_lower。
     *
     * 同样地，我们考虑题目的另一个简化版本：我们需要找到一个 value，使得数组的和最接近 target 且大于 target。
     * 我们也可以通过二分查找的方法，找到这个 value 值，记为 value_upper。
     *
     * 显然 value 值就是 value_lower 和 value_upper 中的一个，我们只需要比较这两个值对应的数组的和与 target 的差，就能确定最终的答案。
     * 这样一来，我们通过两次二分查找，就可以找出 value 值，在每一次二分查找中，我们使用和方法一中相同的查找方法，快速地求出每个 value 值对应的数组的和。
     * 算法从整体上来看，是外层二分查找中嵌套了一个内层二分查找。
     *
     * 那么这个方法还有进一步优化的余地吗？仔细思考一下 value_lower 与 value_upper 的定义，
     * 前者最接近且不大于 target，后者最接近且大于 target。
     * 由于数组的和随着 value 的增大是严格单调递增的，所以 value_upper 的值一定就是 value_lower + 1。
     * 因此我们只需要进行一次外层二分查找得到 value_lower，并直接通过 value_lower + 1 计算出 value_upper 的值就行了。
     * 这样我们就减少了一次外层二分查找，虽然时间复杂度没有变化，但降低了常数。
     *
     *
     * @param arr
     * @param target
     * @return
     */
    public static int findBestValue2(int[] arr, int target) {
        int n = arr.length;
        Arrays.sort(arr);
        int[] sum = new int[n + 1];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
        }

        int left = 0;
        int right = arr[n - 1];
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            int idx = Arrays.binarySearch(arr, mid);
            if (idx < 0) {
                idx = -idx - 1;
            }
            int curr = sum[idx] + (n - idx) * mid;
            if (curr <= target) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        int chooseSmall = sum(arr, ans);
        int chooseLarge = sum(arr, ans + 1);

        return Math.abs(chooseSmall - target) <= Math.abs(chooseLarge - target) ? ans : ans + 1;
    }

    private static int sum(int[] arr, int x) {
        int sum = 0;
        for (int val : arr) {
            sum += Math.min(val, x);
        }
        return sum;
    }

    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -(left + 1);
    }
}
