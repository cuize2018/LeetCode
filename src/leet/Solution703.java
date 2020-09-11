package leet;

import java.util.PriorityQueue;

public class Solution703 {
    public static void main(String[] args) {

    }

    class KthLargest {
        PriorityQueue<Integer> heap;
        int k;
        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.heap = new PriorityQueue<>();

            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            if (heap.isEmpty() || heap.size() < k){
                heap.add(val);
            }
            else {
                if (val >= heap.peek()){
                    heap.remove();
                    heap.add(val);
                }
            }
            if (heap.isEmpty())return -1;
            return heap.peek();
        }
    }
}
