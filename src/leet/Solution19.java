package leet;

public class Solution19 {
    public static void main(String[] args){
        ListNode h = new ListNode(0);
        ListNode t = h;
//        for (int i = 0;i< 5;i++){
//            t.next = new ListNode(i+1);
//            t=t.next;
//        }

        ListNode m = removeNthFromEnd(h,1);
        while (m.next!=null){
            System.out.println(m.val);
            m = m.next;
        }
        System.out.println(m.val);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)return null;
        int i = 0;
        ListNode fast = head;
        ListNode slow = head;
        ListNode slowPre = null;
        while (i < n){
            fast = fast.next;
            i++;
        }

        while (fast != null) {
            slowPre = slow;
            fast = fast.next;
            slow = slow.next;
        }

        if (slowPre != null) {
            slowPre.next = slow.next;
        }
        else {
            head = head.next;
        }
        return head;
    }
}
