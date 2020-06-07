package leet;

public class ListNode {
    public int val;
    public leet.ListNode next;
    public ListNode(int x) { val = x; }

    public void PrintListNode(){
        ListNode t = this;
        StringBuilder s = new StringBuilder();
        while (t.next != null){
            s.append("(").append(t.val).append(")");
            s.append("->");
            t = t.next;
        }
        s.append(Integer.toString(t.val));
        System.out.println(s);
    }
}
