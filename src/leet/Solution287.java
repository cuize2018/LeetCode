package leet;

public class Solution287 {
    public static void main(String[] args) {
        int[] a = {8,7,1,10,17,15,18,11,16,9,19,12,5,14,3,4,2,13,18,18};
        System.out.println(findDuplicate3(a));
    }

    /**
     * 思路：这道题要求我们查找的数是一个整数，并且给出了这个整数的范围（在 1 和 n 之间，包括 1 和 n），
     * 并且给出了一些限制，于是可以使用二分查找法定位在一个区间里的整数。
     * <p>
     * 这个问题应用二分法与绝大多数其它问题应用二分法的不同点是：正着思考是容易的，即思考哪边区间存在重复数是容易的，
     * 因为依然是有抽屉原理做保证。我们依然通过一个具体的例子来分析应该如何编写代码。
     * <p>
     * 以 [1, 2, 2, 3, 4, 5, 6, 7] 为例，一共 8 个数，n + 1 = 8，n = 7，根据题目意思，每个数都在 1 和 7 之间。
     * <p>
     * 例如：区间 [1, 7] 的中位数是 4，遍历整个数组，统计小于等于 4 的整数的个数，至多应该为 4 个。
     * 换句话说，整个数组里小于等于 4 的整数的个数如果严格大于 4 个，就说明重复的数存在于区间 [1, 4]，
     * 它的反面是：重复的数存在于区间 [5, 7]。
     * <p>
     * 于是，二分法的思路是先猜一个数（有效范围 [left, right]里的中间数 mid），
     * 然后统计原始数组中小于等于这个中间数的元素的个数 cnt，如果 cnt 严格大于 mid，
     * （注意我加了着重号的部分“小于等于”、“严格大于”）依然根据抽屉原理，重复元素就应该在区间 [left, mid] 里。
     *
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        int n = nums.length - 1;

        int low = 1;
        int high = n;

        while (low < high) {
            int mid = (low + high) >>> 1;
            int count = 0;
            for (int val : nums) {
                if (val <= mid) {
                    count++;
                }
            }
            if (count > mid) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public static int findDuplicate2(int[] nums) {
        int n = nums.length - 1;
        int left = 1;
        int right = n;

        while (left < right) {
            int middle = (left + right) >>> 1;
            int count = 0;
            for (int num : nums) {
                if (num <= middle) {
                    count++;
                }
            }

            if (count > middle) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    public static int findDuplicate3(int[] nums) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < nums.length; i++) {
                int realIdx = nums[i] - 1;
                if (realIdx != i) {
                    flag = true;
                    if (nums[realIdx] == nums[i]) {
                        return nums[i];
                    }
                    swap(nums, i, realIdx);
                }
            }
        }
        return -1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
