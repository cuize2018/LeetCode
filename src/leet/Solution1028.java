package leet;

import java.util.Stack;

public class Solution1028 {
    public static void main(String[] args) {
        String s = "1-5--9---1----4--5---3-6";
        TreeNode node = recoverFromPreorder(s);
    }

    public static TreeNode recoverFromPreorder(String S) {
        if (!S.contains("-")) return new TreeNode(Integer.parseInt(S));

        int i = 0;
        char[] str = S.toCharArray();
        Stack<TreeNode> history = new Stack<>();

        while (i < S.length() && str[i] != '-') {
            i++;
        }

        TreeNode root = new TreeNode(Integer.parseInt(S.substring(0, i)));
        TreeNode mov = root;
        history.add(mov);

        int preDepth = 0;
        while (i < S.length() && str[i] == '-') {
            preDepth++;
            i++;
        }

        int t = i;
        while (i < S.length() && str[i] != '-') {
            i++;
        }
        mov.left = new TreeNode(Integer.parseInt(S.substring(t, i)));

        while (i < S.length()) {
            int currDepth = 0;
            while (str[i] == '-') {
                currDepth++;
                i++;
            }
            int startIdx = i;
            while (i < S.length() && str[i] != '-') {
                i++;
            }

            int val = Integer.parseInt(S.substring(startIdx, i));
            if (currDepth > preDepth) {
                mov = mov.right == null ? mov.left : mov.right;
                mov.left = new TreeNode(val);
                history.add(mov);
            } else if (currDepth == preDepth) {
                mov.right = new TreeNode(val);
            } else { //currDepth < preDepth
                int dis = preDepth - currDepth;
                for (int j = 0; j < dis; j++) {
                    history.pop();
                }
                mov = history.peek();
                mov.right = new TreeNode(val);
            }
            preDepth = currDepth;
        }

        return root;
    }
}
