package leet;

import com.sun.corba.se.spi.activation.ActivatorOperations;

import java.util.*;

public class Solution95 {
    Map<Integer, List<TreeNode>> mem = new HashMap<>();

    public static void main(String[] args) {
        int n = 3;
        Solution95 s = new Solution95();
        List<TreeNode> fin = s.generateTrees2(n);
        System.out.println(fin);
        int ff = 0;
    }

    public List<TreeNode> generateTrees2(int n) {
        if (n <= 0) return new ArrayList<>();
        return getTrees(1, n);
    }

    private List<TreeNode> getTrees(int left, int right) {
        int key = left * 8 + right;
        if (mem.containsKey(key)) {
            return mem.get(key);
        }

        List<TreeNode> res = new ArrayList<>();
        if (left > right) {
            res.add(null);
            return res;
        }

        for (int rootVal = left; rootVal <= right; rootVal++) {
            List<TreeNode> lefts = getTrees(left, rootVal - 1);
            List<TreeNode> rights = getTrees(rootVal + 1, right);

            for (TreeNode l : lefts) {
                for (TreeNode r : rights) {
                    TreeNode root = new TreeNode(rootVal);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }
        mem.put(key, res);
        return res;
    }

}
