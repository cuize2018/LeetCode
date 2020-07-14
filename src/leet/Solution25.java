package leet;
public class Solution25 {
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = a;
        a.next = new ListNode(2);
        a = a.next;
        a.next = new ListNode(3);
        a = a.next;
        a.next = new ListNode(4);
//        a = a.next;
//        a.next = new ListNode(5);
//        a = a.next;
//        a.next = new ListNode(6);

        ListNode out = reverseKGroup(b, 2);
        out.PrintListNode();
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null)return null;

        ListNode fast = head;
        ListNode slow = head;
        int i = 0;
        while (i < k && fast != null){
            fast = fast.next;
            i++;
        }
        if (fast == null){
            return reverse(head, fast);
        }

        boolean first = true;
        ListNode lastTail = null;
        while (fast != null){
            ListNode r = reverse(slow, fast);
            if (first){
                head = r;
                first = false;
            }
            else {
                lastTail.next = r;
            }

            lastTail = slow;
            slow = fast;
            i = 0;
            while (i < k && fast != null){
                fast = fast.next;
                i++;
            }
            if (fast == null && i == k){
                lastTail.next = reverse(slow, fast);
            }
        }
        return head;
    }

    private static ListNode reverse(ListNode head, ListNode end) {
        ListNode pre = end;
        ListNode mov = head;

        while (mov != end){
            ListNode next = mov.next;
            mov.next = pre;

            pre = mov;
            mov = next;
        }
        return pre;
    }

}
