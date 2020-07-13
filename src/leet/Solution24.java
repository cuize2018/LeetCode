package leet;
import leet.ListNode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class Solution24 {
    public static void main(String[] args){
        ListNode a = new ListNode(1);
        ListNode b = a;

        a.next = new ListNode(2);a=a.next;
        a.next = new ListNode(3);a=a.next;
//        a.next = new ListNode(4);

        ListNode out = swapPairs(b);
        while (out != null){
            System.out.println(out.val);
            out = out.next;
        }

    }

    public static ListNode swapPairs(ListNode head) {
        ListNode headCopy = head;
        ListNode mov = head;
        if (head == null)return null;
        if (head.next == null)return head;

        ListNode first = mov;
        ListNode second = mov.next;

        first.next = second.next;
        second.next = first;

        mov = second;
        headCopy = second;

        ListNode last = mov.next;
        mov = mov.next.next;

        while (mov != null && mov.next != null){
            ListNode now1 = mov;
            ListNode now_next1 = now1.next;

            now1.next = now_next1.next;
            now_next1.next = now1;
            last.next = now_next1;

            mov = now_next1;
            last = mov.next;
            mov = mov.next.next;
        }
        return headCopy;
    }

    public static ListNode swapPairs2(ListNode head) {
        if (head == null)return null;
        ListNode fast = head;
        ListNode slow = head;
        int i = 0;
        while (i++ < 2 && fast != null){
            fast = fast.next;
        }
        if (fast == null){
            return reverse(slow, fast);
        }

        boolean first = true;
        ListNode subTail = slow;
        while (fast != null){
            ListNode t = reverse(slow, fast);
            if (first){
                head = t;
                first = false;
            }
            else {
                subTail.next = t;
            }

            subTail = slow;
            slow = fast;
            i = 0;
            while (i++ < 2 && fast != null) {
                fast = fast.next;
            }
            if (fast == null){
                subTail.next = reverse(slow, fast);
            }
        }
        return head;
    }

    private static ListNode reverse(ListNode start, ListNode end) {
        ListNode pre = end;
        ListNode mov = start;

        while (mov != end){
            ListNode nextNode = mov.next;
            mov.next = pre;
            pre = mov;
            mov = nextNode;
        }
        return pre;
    }
}
