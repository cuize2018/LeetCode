package leet;

public class Solution876 {
    public static void main(String[] args) {

    }

    /**
     * 快慢指针
     * @param head
     * @return
     */
    public static ListNode middleNode(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode quick = head;

        while (quick.next != null && quick.next.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }

        if (quick.next != null) return slow.next;
        else return slow;
    }
}
