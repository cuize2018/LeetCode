package leet;

import java.util.List;

class Node_n {
    public int val;
    public List<Node_n> children;

    public Node_n() {}

    public Node_n(int _val) {
        val = _val;
    }

    public Node_n(int _val, List<Node_n> _children) {
        val = _val;
        children = _children;
    }
};
