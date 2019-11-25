package leet;

import java.util.*;

public class Solution98 {
    TreeNode pre = null;
    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        TreeNode root_c = root;
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);
        root = root.left;

        root.left = new TreeNode(0);
        root.right = new TreeNode(2);
        root = root.right;
        root.right = new TreeNode(3);
        root = root_c;
        root = root.right;
        root.left = new TreeNode(4);
        root.right = new TreeNode(6);

        Solution98 s = new Solution98();

        System.out.println(s.isValidBST2(root_c));
    }


    public  boolean isValidBST(TreeNode root) {
        if (root == null)return true;
        if (root.left == null && root.right == null){
            return true;
        }

        List<TreeNode> left = getAllLeft(root);
        List<TreeNode> right = getAllRight(root);

        for (TreeNode node : left){
            if (node.val >= root.val){
                return false;
            }
        }

        for (TreeNode node : right){
            if (node.val <= root.val){
                return false;
            }
        }

        return isValidBST(root.left) && isValidBST(root.right);
    }

    private List<TreeNode> getAllLeft(TreeNode root){
        List<TreeNode> out = new ArrayList<>();
        if (root == null)return out;
        if (root.left == null && root.right == null){
            return out;
        }

        if (root.left != null){
            out.add(root.left);
        }

        out.addAll(getAllLeft(root.left));
        out.addAll(getAllRight(root.left));
        return out;
    }

    private List<TreeNode> getAllRight(TreeNode root){
        List<TreeNode> out = new ArrayList<>();
        if (root == null)return out;
        if (root.left == null && root.right == null){
            return out;
        }

        if (root.right != null){
            out.add(root.right);
        }

        out.addAll(getAllLeft(root.right));
        out.addAll(getAllRight(root.right));
        return out;
    }

    /**
     * 因为二叉搜索树中序遍历是递增的,所以我们可以中序遍历判断前一数是否小于后一个数.
     * @param root
     * @return
     */
    public  boolean isValidBST2(TreeNode root) {
        if (root == null) return true;

        if (!isValidBST2(root.left))return false;
        if (pre != null && pre.val >= root.val)return false;
        pre = root;
        return isValidBST2(root.right);
    }
}
