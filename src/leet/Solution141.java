package leet;

import java.util.HashSet;
import java.util.Set;

public class Solution141 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode mov = head;
        mov.next = new ListNode(2);
        mov = mov.next;
        mov.next = new ListNode(3);
        mov.next = head;

        System.out.println(hasCycle(head));
    }

    public static boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();

        ListNode mov = head;
        while (mov != null && !set.contains(mov)) {
            set.add(mov);
            mov = mov.next;
        }

        return mov != null;
    }

    /**
     * 快慢指针， 若有环， 快的一定会追上慢的
     * @param head
     * @return
     */
    public static boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode quick = head.next;

        while (slow != quick) {
            if (quick == null || quick.next == null) return false;

            slow = slow.next;
            quick = quick.next.next;
        }
        return true;
    }

    public static boolean hasCycle3(ListNode head) {
        if (head == null)return false;

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow)return true;
        }
        return false;
    }
}
