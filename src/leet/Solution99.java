package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution99 {
    //list存储中序遍历的节点值
    List<TreeNode> list = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode rc = root;
        root.left = new TreeNode(3);
        root = root.left;
        root.right = new TreeNode(2);

        Solution99 solution99 = new Solution99();
        solution99.recoverTree(rc);
        int a = 0;
    }

    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        recoverTree(root.left);
        checkIfRemove(root);
        recoverTree(root.right);
    }

    private void checkIfRemove(TreeNode root) {
        //判断当前节点值是否不符合递增的顺序
        if (!list.isEmpty() && root.val < list.get(list.size() - 1).val) {
            int i = list.size() - 1;
            TreeNode mov = root;
            //若不符合，则不断交换当前节点mov直到符合递增顺序的位置
            while (i >= 0 && mov.val < list.get(i).val) {
                TreeNode b = list.get(i);
                swap(mov, b);

                list.add(mov);
                mov = b;
                i--;
            }
        } else {
            list.add(root);
        }
    }

    private void swap(TreeNode a, TreeNode b) {
        int val = a.val;
        a.val = b.val;
        b.val = val;
    }
}
