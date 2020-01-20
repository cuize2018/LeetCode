package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution429 {
    public List<List<Integer>> out = new ArrayList<>();
    public List<Integer> this_level = new ArrayList<>();
    public List<Node_n> next_level = new ArrayList<>();

    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrder(Node_n root) {
        if (root==null)return out;

        this_level.add(root.val);
        if (root.children != null){
            next_level.addAll(root.children);
        }

        out.add(new ArrayList<>(this_level));
        this_level.clear();

        while (!next_level.isEmpty()) {
            List<Node_n> temp = new ArrayList<>();
            for (Node_n node : next_level) {
                temp.addAll(help(node));
            }
            out.add(new ArrayList<>(this_level));
            this_level.clear();

            next_level = temp;
        }
        return out;
    }

    public List<Node_n> help(Node_n root){
        List<Node_n> tmp = new ArrayList<>();
        if (root == null)return tmp;

        this_level.add(root.val);

        if (root.children != null){
            tmp.addAll(root.children);
        }
        return tmp;
    }

}
