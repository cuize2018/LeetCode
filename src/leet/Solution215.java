package leet;

import java.util.PriorityQueue;

public class Solution215 {
    public static void main(String[] args){
        int[] a = {3,2,1,5,6,4};
        System.out.println(findKthLargest(a,2));
    }

    public static int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums){
            heap.add(num);
            if (heap.size() > k){
                heap.poll();
            }
        }
        return heap.peek();
    }
}
