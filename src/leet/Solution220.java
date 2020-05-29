package leet;

import java.util.*;

public class Solution220 {
    public static void main(String[] args) {
        int[] nums = {1, 2};
        int k = 0;
        int t = 1;

        System.out.println(containsNearbyAlmostDuplicate(nums, k, t));
    }

    /**
     * 滑动窗口大小为k
     * 初始化一颗空的二叉搜索树 set
     * 对于每个元素x，遍历整个数组
     * 在 set 上查找大于等于x的最小的数，如果s - x ≤ t则返回 true
     * 在 set 上查找小于等于x的最大的数，如果x - g ≤ t则返回 true
     * 在 set 中插入x
     * 如果树的大小超过了k, 则移除最早加入树的那个数。
     * 返回 false
     * 我们把大于等于 x 的最小的数 s 当做 x 在 BST 中的后继节点。
     * 同样的，我们能把小于等于 x 最大的数 g 当做 x 在 BST 中的前继节点。
     * s 和 g 这两个数是距离 x 最近的数。因此只需要检查它们和 x 的距离就能知道条件二是否满足了。
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer smallest_great = set.ceiling(nums[i]);
            if (smallest_great != null && (long) smallest_great <= (long) nums[i] + t) {
                return true;
            }

            Integer greatest_small = set.floor(nums[i]);
            if (greatest_small != null && (long) greatest_small >= (long) nums[i] - t) {
                return true;
            }

            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 首先还是滑动窗口的思想，一个窗口一个窗口考虑。
     * 不同之处在于，我们把窗口内的数字存在不同编号的桶中。
     * 每个桶内存的数字范围是 t + 1 个数，这样做的好处是，桶内任意两个数之间的差一定是小于等于 t 的。
     *
     * t = 2, 每个桶内的数字范围如下
     * 编号          ...     -2            -1             0           1      ...
     *                   -------        -------       -------     -------
     * 桶内数字范围      | -6 ~ -4  |    | -3 ~ -1 |   | 0 ~ 2 |   | 3 ~ 5 |
     *                   -------        -------       -------     -------
     *
     * 有了上边的桶，再结合滑动窗口就简单多了，同样的举个例子。
     * k = 3,  t = 2, 窗口内 3 个数用上边的桶存储, 当前考虑 x
     * 2 6 3 x 5
     * ^   ^
     * 桶中的情况
     *      0             1              2
     *   -------        -------       -------
     * |     2  |      |  3    |     |   6   |
     *   -------        -------       -------
     *
     * 接下来我们只需要算出来 x 在哪个桶中。
     *
     * 如果 x 所在桶已经有数字了,那就说明存在和 x 相差小于等于 t 的数。
     * 如果 x 所在桶没有数字，因为与 x 所在桶不相邻的桶中的数字与 x 的差一定大于 t，
     * 所以只需要考虑与 x 所在桶相邻的两个桶中的数字与x的差是否小于等于 t。
     *
     * 如果没有找到和 x 相差小于等于 t 的数, 那么窗口右移。从桶中将窗口中第一个数删除, 并且将 x 加入桶中
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public static boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> map = new HashMap<>();
        long w = t + 1;
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                map.remove(getId(nums[i - k - 1], w));
            }
            long mapId = getId(nums[i], w);
            if (map.containsKey(mapId)) return true;

            if (map.containsKey(mapId + 1) && map.get(mapId + 1) - nums[i] < w) return true;
            if (map.containsKey(mapId - 1) && nums[i] - map.get(mapId - 1) < w) return true;

            map.put(mapId, (long) nums[i]);
        }
        return false;
    }
    //w 表示桶中的存储数字范围的个数
    private static long getId(long num, long w) {
        if (num >= 0) {
            return num / w;
        } else {
            //num 加 1, 把负数移动到从 0 开始, 这样算出来标号最小是 0, 已经用过了, 所以要再减 1
            return (num + 1) / w - 1;
        }
    }
}
