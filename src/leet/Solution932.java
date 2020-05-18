package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * <p>
     * 对{1-m}中所有的数乘以2, 再-1，构成一个奇数漂亮数组A。如{1,3,2,4},可以得到{1,5,3,7}
     * 对{1-m}中所有的数乘以2,构成一个偶数漂亮数组B,如{1,3,2,4}, 可以得到{2,6,4,8}
     * A+B构成了{1-2m}的漂亮数组。{1,5,3,7}+{2,6,4,8}={1,5,3,7,2,6,4,8}
     * 从中删除不要的数字即可。
     *
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

    /**
     * 那么我们就有了一个想法：将数组分成两部分 left 和 right，分别求出一个漂亮的数组，然后将它们进行仿射变换，使得不存在满足下面条件的三元组：
     * A[k] * 2 = A[i] + A[j], i < k < j；
     * A[i] 来自 left 部分，A[j] 来自 right 部分。
     *
     * 可以发现，等式 A[k] * 2 = A[i] + A[j] 的左侧是一个偶数，右侧的两个元素分别来自两个部分。
     * 要想等式恒不成立，一个简单的办法就是让 left 部分的数都是奇数，right 部分的数都是偶数。
     *
     * 因此我们将所有的奇数放在 left 部分，所有的偶数放在 right 部分，这样可以保证等式恒不成立。
     * 对于 [1..N] 的排列，left 部分包括 (N + 1) / 2 个奇数，right 部分包括 N / 2 个偶数。
     * 对于 left 部分，我们进行 k = 1/2, b = 1/2 的仿射变换，把这些奇数一一映射到不超过 (N + 1) / 2 的整数。
     * 对于 right 部分，我们进行 k = 1/2, b = 0 的仿射变换，把这些偶数一一映射到不超过 N / 2 的整数。
     * 经过映射，left 和 right 部分变成了和原问题一样，但规模减少一半的子问题，这样就可以使用分治算法解决了。
     *
     * 在 [1..N] 中有 (N + 1) / 2 个奇数和 N / 2 个偶数。
     * 我们将其分治成两个子问题，其中一个为不超过 (N + 1) / 2 的整数，并映射到所有的奇数；
     * 另一个为不超过 N / 2 的整数，并映射到所有的偶数。
     *
     * @param N
     * @return
     */
    Map<Integer, int[]> memory;
    public int[] beautifulArray2(int N) {
        memory = new HashMap<>();
        return help(N);
    }

    private int[] help(int N) {
        if (memory.containsKey(N)) {
            return memory.get(N);
        }

        int[] ans = new int[N];
        if (N == 1) {
            ans[0] = 1;
        } else {
            int i = 0;
            int[] jiLeft = help((N + 1) / 2); //odds
            for (int num : jiLeft) {
                ans[i] = 2 * num - 1;
                i++;
            }
            int[] ouRight = help(N / 2); //even
            for (int num : ouRight) {
                ans[i] = 2 * num;
                i++;
            }
        }
        memory.put(N, ans);
        return ans;
    }
}
