package leet;

public class Solution148 {
    public static void main(String[] args) {
        ListNode head = new ListNode(-1);
        ListNode headp = head;
        head.next = new ListNode(5);
        head = head.next;
        head.next = new ListNode(3);
        head = head.next;
        head.next = new ListNode(4);
        head = head.next;
        head.next = new ListNode(0);
        head = head.next;

        ListNode out = sortList2(headp);
        out.PrintListNode();

    }

    public static ListNode sortList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head.next;

        //双指针法找到中间节点, slow节点为中间节点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //从中间节点处分割, 得到两个子链表
        ListNode nextHead = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(nextHead);

        ListNode tmp = new ListNode(0);
        ListNode tmp_copy = tmp;
        while (left != null && right != null) {
            if (left.val < right.val) {
                tmp.next = left;
                left = left.next;
            } else {
                tmp.next = right;
                right = right.next;
            }
            tmp = tmp.next;
        }

        if (left != null) tmp.next = left;
        if (right != null) tmp.next = right;

        return tmp_copy.next;
    }

    public static ListNode sortList2(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode part2Head = slow.next;
        slow.next = null;

        ListNode left = sortList2(head);
        ListNode right = sortList2(part2Head);

        return merge(left, right);
    }

    private static ListNode merge(ListNode left, ListNode right) {
        ListNode temp = new ListNode(0);
        ListNode head = temp;
        while (left != null && right != null) {
            if (left.val < right.val) {
                temp.next = left;
                left = left.next;
            } else {
                temp.next = right;
                right = right.next;
            }
            temp = temp.next;
        }
        temp.next = left != null ? left : right;
        return head.next;
    }
}
