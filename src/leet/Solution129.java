package leet;

import org.omg.CORBA.TRANSACTION_MODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution129 {
    private int sum = 0;

    public static void main(String[] args){
        TreeNode root = new TreeNode(4);root.left = new TreeNode(9);root.right = new TreeNode(0);
        TreeNode root_c = root;

        root = root.left;
        root.left = new TreeNode(5);root.right = new TreeNode(1);

//        TreeNode root = new TreeNode(1);root.right = new TreeNode(5); TreeNode root_c = root;

        Solution129 s = new Solution129();
        System.out.println(s.sumNumbers(root_c));
    }

    public int sumNumbers(TreeNode root) {
        String already = "";
        part(root, already);

        return sum;
    }

    public void part(TreeNode root, String already_num){
        if (root == null){
            return;
        }

        String tmp = new String(already_num);
        tmp += Integer.toString(root.val);

        if (root.left != null) {
            part(root.left, tmp);
        }
        if (root.right != null) {
            part(root.right, tmp);
        }
        if (root.left == null && root.right == null){
           sum += Integer.parseInt(tmp);
        }
    }
}
