package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution974 {
    public static void main(String[] args) {
        int[] A = {4, 5, 0, -2, -3, 1};
        int K = 2;
        System.out.println(subarraysDivByK(A, K));
    }

    public static int subarraysDivByK(int[] A, int K) {
        List<Integer> lastSum = new ArrayList<>();
        lastSum.add(A[0]);
        int count = A[0] % K == 0 ? 1 : 0;

        for (int i = 1; i < A.length; i++) {
            List<Integer> temp = new ArrayList<>();
            for (Integer integer : lastSum) {
                int v = integer + A[i];
                temp.add(v);
                if (v % K == 0) count++;
            }
            temp.add(A[i]);
            if (A[i] % K == 0) count++;
            lastSum = temp;
        }
        return count;
    }

    /**
     * 通常，涉及连续子数组问题的时候，我们使用前缀和来解决。
     * <p>
     * 我们令 P[i] = A[0] + A[1] + ... + A[i]。
     * 那么每个连续子数组的和sum(i,j) 就可以写成 P[j] - P[i-1]（其中 0 < i < j）的形式。
     * 此时，判断子数组的和能否被 K 整除就等价于判断 (P[j] - P[i-1]) mod K == 0，
     * 根据 同余定理，只要 P[j] mod K == P[i-1] mod K，就可以保证上面的等式成立。
     * <p>
     * 因此我们可以考虑对数组进行遍历，在遍历同时统计答案。
     * 当我们遍历到第 j 个元素时，我们统计以 j 结尾的符合条件的子数组个数。
     * 我们可以维护一个以前缀和模 K 的值为键，出现次数为值的哈希表 record，在遍历的同时进行更新。
     * 这样在计算以 j 结尾的符合条件的子数组个数时，根据上面的分析，答案即为 [0..j-1]中前缀和模 K 也为 P[j] mod K的位置个数，
     * 即 record[P[j] mod K]。
     * <p>
     * 最后的答案即为以每一个位置为数尾的符合条件的子数组个数之和。
     * 需要注意的一个边界条件是，我们需要对哈希表初始化，记录record[0]=1，这样就考虑了前缀和本身被 K 整除的情况
     *
     * @param A
     * @param K
     * @return
     */
    public static int subarraysDivByK2(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;

        map.put(0, 1);
        for (int value : A) {
            sum += value;
            int sumModValue = (sum % K + K) % K; // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正
            Integer val = map.getOrDefault(sumModValue, 0);

            count += val;
            map.put(sumModValue, val + 1);
        }
        return count;
    }

    public static int subarraysDivByK3(int[] A, int K) {
        int[] map = new int[K];
        int count = 0;
        int sum = 0;

        map[0] = 1;
        for (int value : A) {
            sum += value;
            int sumModValue = (sum % K + K) % K; // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正
            count += map[sumModValue];
            map[sumModValue]++;
        }
        return count;
    }
}
