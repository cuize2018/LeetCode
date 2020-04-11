package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution234 {
    public static void main(String[] args) {

    }

    public static boolean isPalindrome(ListNode head) {
        if (head==null||head.next == null)return true;

        List<Integer> nums = new ArrayList<>();
        while (head != null){
            nums.add(head.val);
            head = head.next;
        }
        int i = 0;
        int j = nums.size()-1;

        while (i < j){
            if (!nums.get(i).equals(nums.get(j)))return false;
            i++;
            j--;
        }
        return true;
    }

    /**
     * 反转链表后半部分
     * @param head
     * @return
     */
    public static boolean isPalindrome2(ListNode head) {
        if (head==null||head.next == null)return true;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode reversedHead = reverseLinkedList(slow);
        while (head != null && reversedHead != null){
            if (head.val != reversedHead.val)return false;

            head = head.next;
            reversedHead = reversedHead.next;
        }
        return true;
    }

    private static ListNode reverseLinkedList(ListNode head) {
        ListNode last = null;
        ListNode mov = head;
        ListNode next = null;

        while (mov != null){
            next = mov.next;

            mov.next = last;
            last = mov;
            mov = next;
        }
        return last;
    }
}
