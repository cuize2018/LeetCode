package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution230 {
    private List<Integer> out = new ArrayList<>();
    private int out_num  = 0;
    private int kt = 0;
    public static void main(String[] args){
        TreeNode root = new TreeNode(3);TreeNode mov = root;
        mov.left = new TreeNode(1);mov.right = new TreeNode(4);
        mov=mov.left;
        mov.right = new TreeNode(2);

        System.out.println(new Solution230().kthSmallest2(root, 2));
    }

    public  int kthSmallest(TreeNode root, int k) {
        if (root == null){
            return 0;
        }
        inorder(root);
        return out.get(k-1);
    }

    public  void inorder(TreeNode root){
        if (root == null){
            return;
        }

        inorder(root.left);
        out.add(root.val);
        inorder(root.right);
    }

    public  int kthSmallest2(TreeNode root, int k) {
        if (root == null){
            return 0;
        }
        kt = k;
        inorder2(root);
        return out_num;
    }

    public  void inorder2(TreeNode root) {
        if (root == null) {
            return;
        }

        inorder2(root.left);
        if (kt > 0) {
            out_num = root.val;
            kt--;
            if (kt <= 0) return;
            inorder2(root.right);
        }
    }
}
