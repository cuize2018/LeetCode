package leet.interview;

import leet.TreeNode;

import java.util.*;

public class Solution16_11 {
    TreeSet<Integer> res = new TreeSet<>();
    int len = 0;
    public static void main(String[] args) {
        int shorter = 1;
        int longer = 1;
        int k = 30;
        Solution16_11 solution16_11 = new Solution16_11();
        int[] r = solution16_11.divingBoard(shorter, longer, k);
        System.out.println(Arrays.toString(r));
    }

    public int[] divingBoard(int shorter, int longer, int k) {
        len += shorter;
        dfs(shorter, longer, k-1);
        len -= shorter;

        if (longer != shorter) {
            len += longer;
            dfs(shorter, longer, k - 1);
            len -= longer;
        }

        int[] ans = new int[res.size()];
        int i = 0;
        for (int v : res) {
            ans[i++] = v;
        }
        return ans;
    }

    private void dfs(int shorter, int longer, int k) {
        if (k < 0)return;
        if (k == 0){
            res.add(len);
            return;
        }

        len += shorter;
        dfs(shorter, longer, k-1);
        len -= shorter;

        if (longer != shorter) {
            len += longer;
            dfs(shorter, longer, k - 1);
            len -= longer;
        }
    }

    public int[] divingBoard2(int shorter, int longer, int k) {
        if (k == 0)return new int[]{};
        if (longer == shorter)return new int[]{shorter*k};

        int sum = shorter*k;
        int m = 0;
        int[] ans = new int[k+1];
        ans[m++] = sum;

        for (int i = 0; i < k; i++) {
            sum -= shorter;
            sum += longer;
            ans[m++] = sum;
        }
        return ans;
    }
}
