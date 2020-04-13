package leet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution160 {
    public static void main(String[] args) {

    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)return null;

        ListNode movA = headA;
        ListNode movB = headB;
        Set<ListNode> set = new HashSet<>();
        while (movB != null){
            set.add(movB);
            movB = movB.next;
        }

        while (movA != null){
            if (set.contains(movA))return movA;
            movA = movA.next;
        }
        return null;
    }

    /**
     * 指针 pA 指向 A 链表，指针 pB 指向 B 链表，依次往后遍历
     * 如果 pA 到了末尾，则 pA = headB 继续遍历
     * 如果 pB 到了末尾，则 pB = headA 继续遍历
     * 比较长的链表指针指向较短链表head时，长度差就消除了
     * 如此，只需要将最短链表遍历两次即可找到位置
     *
     *
     * 可以理解成两个人速度一致， 走过的路程一致。那么肯定会同一个时间点到达终点。
     * 如果到达终点的最后一段路两人都走的话，那么这段路上俩人肯定是肩并肩手牵手的。 nb
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)return null;
        ListNode movA = headA;
        ListNode movB = headB;

        while (movA != movB){
            movA = movA==null?headB:movA.next;
            movB = movB==null?headA:movB.next;
        }
        return movA;
    }
}
