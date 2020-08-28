package 笔试.tencent;

import java.util.Scanner;

public class No1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long k = scanner.nextLong();
        StringBuilder stringBuilder = new StringBuilder();
        for (long i = 0; i < n; i++) {
            long a = scanner.nextLong();
            if (i+1 == k){
                continue;
            }
            stringBuilder.append(a);
            if (i < n-1) {
                stringBuilder.append(" ");
            }
        }
        System.out.println(stringBuilder.toString());
    }

    private static ListNode func(ListNode head, int k) {
        if (head == null)return null;
        ListNode slowPre = null;
        ListNode fast = head;

        for(int i = 0;i< k-1;i++){
            slowPre = fast;
            fast = fast.next;
        }

        slowPre.next = fast.next;
        fast.next = null;
        return head;
    }


    static class ListNode{
        int x;
        ListNode next;
        public ListNode(int v){
            x = v;
            next = null;
        }
    }
}


