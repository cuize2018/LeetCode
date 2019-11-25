package leet;
import leet.ListNode;
/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 */
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
        ListNode head_copy = head;
        ListNode mov = head;
        int len = 0;

        if (head == null)return head;

        while (mov != null){
            len++;
            mov = mov.next;
        }
        mov = head;
        int location = len - n;
        if (location == 0){ //第一个
            if (len > 1){
                head_copy = head_copy.next;
            }
            else{
                head_copy = null;
            }
        }
        else if (location == len-1){ //最后一个
            while (mov.next.next != null){
                mov = mov.next;
            }
            mov.next = null;
        }
        else {
            int count = 0;
            while (count < location-1){
                mov = mov.next;
                count++;
            }
            mov.next = mov.next.next;
        }
        return head_copy;
    }
}
