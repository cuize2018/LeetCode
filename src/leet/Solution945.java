package leet;

import java.util.Arrays;

public class Solution945 {
    public static void main(String[] args) {

    }

    /**
     * 计数法
     *思路
     *
     * 由于 A[i] 的范围为 [0, 40000)，我们可以用数组统计出每个数出现的次数，然后对于每个重复出现的数，我们暴力地将它递增，
     * 直到它增加到一个没有重复出现的数为止。但这样的方法的时间复杂度较大，可以达到 O(N^2), 例如数组 A 中所有元素都是 1 的情况。
     *
     * 因此，我们不能对重复出现的数暴力的进行递增，而是用以下的做法：
     * 当我们找到一个没有出现过的数的时候，将之前某个重复出现的数增加成这个没有出现过的数。
     * 注意，这里 「之前某个重复出现的数」 是可以任意选择的，它并不会影响最终的答案，因为将 P 增加到 X 并且将 Q 增加到 Y，与将 P 增加到 Y 并且将 Q 增加到 X 都需要进行 (X + Y) - (P + Q) 次操作。
     *
     * 例如当数组 A 为 [1, 1, 1, 1, 3, 5] 时，我们发现有 3 个重复的 1，且没有出现过 2，4 和 6，因此一共需要进行 (2 + 4 + 6) - (1 + 1 + 1) = 9 次操作。
     *
     * 算法
     *
     * 首先统计出每个数出现的次数，然后从小到大遍历每个数 x：
     *
     * 如果 x 出现了两次以上，就将额外出现的数记录下来（例如保存到一个列表中）；
     *
     * 如果 x 没有出现过，那么在记录下来的数中选取一个 v，将它增加到 x，需要进行的操作次数为 x - v。
     *
     * 我们还可以对该算法进行优化，使得我们不需要将额外出现的数记录下来。还是以 [1, 1, 1, 1, 3, 5] 为例，
     * 当我们发现有 3 个重复的 1 时，我们先将操作次数减去 1 + 1 + 1。接下来，当我们发现 2，4 和 6 都没有出现过时，我们依次将操作次数增加 2，4 和 6。
     *
     * 注意事项
     *
     * 虽然 A[i] 的范围为 [0, 40000)，但我们有可能会将数据递增到 40000 的两倍 80000。
     * 这是因为在最坏情况下，数组 A 中有 40000 个 40000，这样要使得数组值唯一，需要将其递增为 [40000, 40001, ..., 79999]，因此用来统计的数组需要开到 80000。
     *
     * @return
     */
    public static int minIncrementForUnique(int[] A) {
        //可以使用A.length+40000优化
        int[] count = new int[A.length+40000];
        for (int val : A) {
            count[val]++;
        }

        int opCount = 0;
        int taken = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 1) {
                taken += count[i] - 1;
                opCount -= i * (count[i] - 1);
            } else if (taken > 0 && count[i] == 0) {
                taken--;
                opCount += i;
            }
        }
        return opCount;
    }

    public static int minIncrementForUnique2(int[] A) {
        Arrays.sort(A);

        int opCount = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] <= A[i - 1]) {
                opCount += A[i - 1] - A[i] + 1;
                A[i] = A[i - 1] + 1;
            }
        }
        return opCount;
    }
}
