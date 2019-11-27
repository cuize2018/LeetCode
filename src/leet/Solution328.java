package leet;

public class Solution328 {
    public static void main(String[] args) {
        ListNode l = new ListNode(1);ListNode h = l;
        l.next = new ListNode(2);l=l.next;
//        l.next = new ListNode(3);l=l.next;
//        l.next = new ListNode(4);l=l.next;
//        l.next = new ListNode(5);l=l.next;
        ListNode o = oddEvenList(h);
        o.PrintListNode();
    }

    private static ListNode oddEvenList(ListNode head) {
        if (head == null)return null;
        if (head.next == null)return head;

        ListNode last_odd = head;
        ListNode last_even = head.next;
        ListNode first_even = head.next;
        ListNode mov = first_even.next;

        int count = 3;
        while (mov != null){
            if (count % 2 == 0){
                last_even.next = mov;
                last_even = last_even.next;
            }
            else {
                last_odd.next = mov;
                last_odd = last_odd.next;
            }
            mov = mov.next;
            count++;
        }
        last_odd.next = first_even;
        last_even.next = null;
        return head;
    }
}
