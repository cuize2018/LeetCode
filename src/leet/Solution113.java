package leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution113  {
    List<List<Integer>> out = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();
    public static void main(String[] args){
        TreeNode root = new TreeNode(5);root.left = new TreeNode(4);root.right = new TreeNode(8);
        TreeNode rootCopy = root;
        root = root.left;

        root.left = new TreeNode(11);root = root.left;
        root.left = new TreeNode(7);root.right = new TreeNode(2);
        root = rootCopy;root = root.right;

        root.left = new TreeNode(13);root.right = new TreeNode(4);root=root.right;
        root.left = new TreeNode(5);root.right = new TreeNode(1);

        //TreeNode rootCopy = new TreeNode(0);rootCopy.left = new TreeNode(1);rootCopy.right = new TreeNode(1);

        Solution113 s = new Solution113();
        System.out.println(s.pathSum(rootCopy, 22));

    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null)return out;
        stack.push(root.val);
        if (root.left == null && root.right == null){
            if (sum-root.val == 0){
                out.add(new ArrayList<>(stack));
            }
            return out;
        }

        pathSumSub(root.left, sum-root.val);
        pathSumSub(root.right, sum-root.val);

        return out;
    }

    public void pathSumSub(TreeNode root, int sum) {
        if (root == null)return;
        stack.push(root.val);
        if (root.left == null && root.right == null){
            if (sum-root.val == 0){
                out.add(new ArrayList<>(stack));
            }
            if (!stack.isEmpty()) stack.pop();
            return;
        }

        pathSumSub(root.left, sum-root.val);
        pathSumSub(root.right,sum-root.val);
        if (!stack.isEmpty()) stack.pop();
    }
}
