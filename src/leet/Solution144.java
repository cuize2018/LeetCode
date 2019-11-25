package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution144 {
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> out = new ArrayList<>();

        if (root == null){
            return out;
        }

        out.add(root.val);
        out.addAll(preorderTraversal(root.left));
        out.addAll(preorderTraversal(root.right));
        return out;
    }
}
