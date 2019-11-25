package leet;

public class Solution147 {
    public static void main(String[] args){
        ListNode head = new ListNode(-1);ListNode headp = head;
//        head.next = new ListNode(5);head = head.next;
//        head.next = new ListNode(3);head = head.next;
//        head.next = new ListNode(4);head = head.next;
//        head.next = new ListNode(0);head = head.next;

        ListNode out = insertionSortList(headp);
        out.PrintListNode();
    }

    public static ListNode insertionSortList(ListNode head) {
        if (head == null)return null;
        ListNode sortedList = new ListNode(head.val);
        ListNode sortMov = sortedList;
        ListNode orgMov = head;

        orgMov = orgMov.next;
        while (orgMov != null){
           while (sortMov != null){
               if (sortMov.val < orgMov.val){
                   if (sortMov.next != null){
                       if (sortMov.next.val >= orgMov.val){
                           ListNode nextTmp = sortMov.next;
                           sortMov.next = new ListNode(orgMov.val);
                           sortMov.next.next = nextTmp;
                           break;
                       }
                       else {
                           sortMov = sortMov.next;
                       }
                   }
                   else {
                       sortMov.next = new ListNode(orgMov.val);
                       break;
                   }
               }
               else {
                   ListNode sortNewHead = new ListNode(orgMov.val);
                   sortNewHead.next = sortMov;
                   sortedList = sortNewHead;
                   break;
               }
           }
           sortMov = sortedList;
           orgMov = orgMov.next;
        }
        return sortedList;
    }
}
