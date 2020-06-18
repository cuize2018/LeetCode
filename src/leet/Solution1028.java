package leet;

import java.util.Stack;

public class Solution1028 {
    public static void main(String[] args) {
        String s = "1-5--9---1----4--5---3-6";
        TreeNode node = recoverFromPreorder(s);
        int a = 0;
    }

    public static TreeNode recoverFromPreorder(String S) {
        return help(S);
    }

    private static TreeNode help(String s) {
        int i = 0;
        char[] str = s.toCharArray();
        Stack<TreeNode> history = new Stack<>();
        while (i < s.length() && s.charAt(i) != '-') {
            i++;
        }

        TreeNode mov = new TreeNode(Integer.parseInt(s.substring(0, i)));
        TreeNode root = mov;
        history.add(mov);

        if (i >= s.length())return root;

        int k = 0;
        while (i<s.length() && str[i] == '-') {
            k++;
            i++;
        }
        int t = i;
        while (i < s.length() && s.charAt(i) != '-') {
            i++;
        }
        mov.left = new TreeNode(Integer.parseInt(s.substring(t, i)));

        while (i < s.length()) {
            int k2 = 0;
            while (str[i] == '-') {
                k2++;
                i++;
            }
            int startIdx = i;
            while (i < s.length() && s.charAt(i) != '-') {
                i++;
            }

            int val = Integer.parseInt(s.substring(startIdx, i));
            if (k2 > k) {
                mov = mov.right == null ? mov.left : mov.right;
                mov.left = new TreeNode(val);
                history.add(mov);
            } else if (k2 == k) {
                mov.right = new TreeNode(val);
            } else { //k2 < k
                int dis = k - k2;
                for (int j = 0; j < dis; j++) {
                    history.pop();
                }
                TreeNode treeNode = history.peek();
                mov = treeNode;
                mov.right = new TreeNode(val);
            }
            k = k2;
        }

        return root;
    }
}
