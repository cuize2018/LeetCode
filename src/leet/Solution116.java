package leet;

public class Solution116 {
    public static void main(String[] args){
        Node_ root = new Node_(1,
                new Node_(2,new Node_(4,null,null,null), new Node_(5,null,null,null), null),
                new Node_(3,null, new Node_(7,null,null,null), null), null);

        Node_ out = connect(root);
        int ff = 0;

    }

    public static Node_ connect(Node_ root) {
        if (root == null){
            return root;
        }
        if (root.left != null){
            if (root.right != null){
                root.left.next = root.right;
            }
            else if (root.next != null){
                if (root.next.left != null){
                    root.left.next = root.next.left;
                }
                else if (root.next.right != null){
                    root.left.next = root.next.right;
                }
            }
        }
        if (root.right != null){
            if (root.next != null){
                if (root.next.left != null){
                    root.right.next = root.next.left;
                }
                else if (root.next.right != null){
                    root.right.next = root.next.right;
                }
            }
        }

        connect(root.left);
        connect(root.right);
        return root;
    }
}
