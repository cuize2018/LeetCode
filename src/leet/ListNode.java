package leet;

public class ListNode {
    public int val;
    public leet.ListNode next;
    ListNode(int x) { val = x; }

    public void PrintListNode(){
        ListNode t = this;
        String s = "";
        while (t.next != null){
            s += Integer.toString(t.val);
            s += "->";
            t = t.next;
        }
        s += Integer.toString(t.val);
        System.out.println(s);
    }
}
