package leet;

import org.omg.CORBA.INTERNAL;

import java.util.LinkedList;
import java.util.Queue;

public class Solution111 {
    public static void main(String[] args) {

    }

    public static int minDepth(TreeNode root) {
        if (root == null)return 0;

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        if (left != 0 && right != 0)return  Math.min(left,right) + 1;
        return  Math.max(left,right) + 1;
    }

    public static int minDept2(TreeNode root) {
        if (root == null)return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int height = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left == null && node.right == null){
                    return height;
                }

                if (node.left != null)queue.add(node.left);
                if (node.right != null)queue.add(node.right);
            }
            height++;
        }
        return height;
    }
}
