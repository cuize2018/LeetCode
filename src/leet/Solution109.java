package leet;

public class Solution109 {
    public static void main(String[] args) {
        ListNode l = new ListNode(-10);
        ListNode h = l;
        l.next = new ListNode(-3);
        l = l.next;
        l.next = new ListNode(0);
        l = l.next;
        l.next = new ListNode(5);
        l = l.next;
        l.next = new ListNode(9);
        l = l.next;

        TreeNode out = sortedListToBST(h);
    }

    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);

        ListNode slow = head;
        ListNode slowPre = null;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slowPre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        slowPre.next = null;
        TreeNode left = sortedListToBST(head);
        TreeNode right = sortedListToBST(slow.next);

        root.left = left;
        root.right = right;
        return root;
    }


}
