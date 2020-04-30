package leet.interview;

public class Solution36 {
    Node2 head;
    Node2 pre;

    public static void main(String[] args) {
//        Node2 root = new Node2(4);root.left = new Node2(2);root.right = new Node2(5);
//        Node2 copy = root;
//        root = root.left;
//        root.left = new Node2(1);root.right = new Node2(3);

        Node2 root = new Node2(-1);root.right = new Node2(1);
        Node2 mov = root;
        mov = mov.right;
        mov.right = new Node2(9);

        Solution36 solution36 = new Solution36();
        Node2 node2 = solution36.treeToDoublyList(root);
        int a = 0;
    }

    public Node2 treeToDoublyList(Node2 root) {
        if (root == null)return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    /**
     * 终止条件： 当节点 cur 为空，代表越过叶节点，直接返回；
     * 递归左子树，即 dfs(cur.left) ；
     * 构建链表：
     * 当 pre 为空时： 代表正在访问链表头节点，记为 head 。
     * 当 pre 不为空时： 修改双向节点引用，即 pre.right = cur; cur.left = pre
     * 保存 cur: 更新 pre = cur ，即节点 cur 是后继节点的 pre ；
     * 递归右子树，即 dfs(cur.left)
     *
     * @param root
     */
    private void dfs(Node2 root){
        if (root == null)return;

        dfs(root.left);

        if (pre != null){
            pre.right = root;
        }
        else head = root;
        root.left = pre;
        pre = root;

        dfs(root.right);
    }
}

class Node2 {
    public int val;
    public Node2 left;
    public Node2 right;

    public Node2() {}

    public Node2(int _val) {
        val = _val;
    }

    public Node2(int _val,Node2 _left,Node2 _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
