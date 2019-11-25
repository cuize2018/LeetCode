package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution199 {
    List<Integer> out = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();
    int high = 0;
    public static void main(String[] args){
        TreeNode root = new TreeNode(1);TreeNode mov = root;
        root.left = new TreeNode(2);root.right = new TreeNode(3);
        mov = root.left;
        mov.right = new TreeNode(5);
        mov = root.right;
//        mov.right = new TreeNode(4);

//        TreeNode root = new TreeNode(1);TreeNode mov = root;
//        root.left = new TreeNode(2);

        Solution199 s = new Solution199();
        System.out.println(s.rightSideView(root));
    }

    public  List<Integer> rightSideView(TreeNode root) {
        if (root == null)return out;
        backTrack(root, high);
        for (int i = 0;i<=high;i++){
            out.add(map.get(i));
        }
        return out;
    }

    public void backTrack(TreeNode root, int level){
        if (root.left == null && root.right == null){
            if (!map.containsKey(level)) map.put(level, root.val);
            return;
        }

        if (level >= high) {
            if (!map.containsKey(level)) map.put(level, root.val);
        }

        if (root.right != null){
            if (high <= level)high++;
            level++;
            backTrack(root.right, level);
            level--;
        }

        if (root.left != null){
            if (high <= level)high++;
            level++;
            backTrack(root.left, level);
        }

    }
}
