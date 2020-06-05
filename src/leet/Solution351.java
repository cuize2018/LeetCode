package leet;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Solution351 {
    public static void main(String[] args) {

    }

    boolean[] used = new boolean[9];

    public int numberOfPatterns(int m, int n) {
        int res = 0;
        for (int i = m; i <= n; i++) {
            res += calPatternNums(-1, i);
            Arrays.fill(used, false);
        }
        return res;
    }

    private int calPatternNums(int last, int len) {
        if (len == 0) return 1;
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            if (isValid(last, i)) {
                used[i] = true;
                sum += calPatternNums(i, len - 1);
                used[i] = false;
            }
        }
        return sum;
    }

    private boolean isValid(int last, int index) {
        if (used[index]) return false;
        if (last == -1) return true;
        //从 last 到 i 之间是国际象棋中马的移动，或者 last 和 i 是同一行或列的相邻元素。这种情况下，两个数字之和应当为奇数。
        if ((last + index) % 2 == 1) return true;

        //连接 last 和 i 的中间元素 mid已经被访问过，比方说 last 和 i 选择的是对角线上的两点，那么中间点 mid = 5 应当已经选过
        int mid = (index + last) / 2;
        if (mid == 4) return used[mid];

        // last 和 i 是对角线上的相邻元素。
        // 这个只是表示对角线，（不在同列）&&（不在同行），
        // 刚好因为前面mid=4的时候，已经把对角线里有间隔元素的计算了，所以这里就是对角线相邻元素的
        if ((last % 3 != index % 3) && (last / 3 != index / 3)) return true;

        return used[mid];
    }
}
