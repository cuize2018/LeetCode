package leet;

public class Solution117 {
    public static void main(String[] args){
//        Node_ root = new Node_(1,
//                new Node_(2,new Node_(4,null,null,null), new Node_(5,null,null,null), null),
//                new Node_(3,null, new Node_(7,null,null,null), null), null);
        Node_ root = new Node_(1,
                new Node_(2, new Node_(4,new Node_(7,null,null,null),null,null),new Node_(5, null,null,null),null),
                new Node_(3, null, new Node_(6, null, new Node_(8, null,null,null),null),null),null);

        Node_ out = connect(root);
        int ff = 0;

    }

    public static Node_ connect(Node_ root) {
        Node_ cur = root;
        while (cur != null) {
            Node_ dummy = new Node_();
            Node_ tail = dummy;
            //遍历 cur 的当前层
            while (cur != null) {
                if (cur.left != null) {
                    tail.next = cur.left;
                    tail = tail.next;
                }
                if (cur.right != null) {
                    tail.next = cur.right;
                    tail = tail.next;
                }
                cur = cur.next;
            }
            //更新 cur 到下一层
            cur = dummy.next;
        }
        return root;
    }
}
