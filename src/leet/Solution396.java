package leet;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

public class Solution396 {
    public static void main(String[] args) {
        int[] a = {-2147483648,-2147483648};
        int[] b = {4,3,2,6};
        System.out.println(maxRotateFunction2(a));
    }

    public static int maxRotateFunction(int[] A) {
        if (A.length == 0)return 0;
        Queue<Long> queue = new ArrayDeque<>();
        for (int num : A){
            queue.add((long)num);
        }
        int n = A.length;
        int max = Integer.MIN_VALUE;
        long last_val = Cal(queue);
        max = Math.max((int)last_val, max);

        for (int i = 1;i <= n-1;i++){
            long tmp = queue.remove();
            queue.add(tmp);

            for (int j = 0;j < n-1;j++){
                long t = queue.remove();
                last_val = last_val - t;
                queue.add(t);
            }

            tmp = queue.remove();
            queue.add(tmp);
            last_val += (n-1)*tmp;
            max = Math.max((int)last_val, max);
        }
        return max;
    }

    private static int Cal(Queue<Long> q){
        int nums = q.size();
        long sum = 0;
        int k = 0;
        for (int i = 0;i<nums;i++){
            long val =q.remove();
            sum += k * val;
            k++;
            q.add(val);
        }
        return (int)sum;
    }

    /**
     * 数组模拟队列
     * @param A
     * @return
     */
    public static int maxRotateFunction2(int[] A) {
        if (A.length == 0)return 0;
        int n = A.length;
        int max = Integer.MIN_VALUE;
        long last_val = Cal(A);
        max = Math.max((int)last_val, max);

        for (int i = 1;i <= n-1;i++){
            for (int j = i;j <= n-1;j++){
                last_val = last_val - A[j];
            }
            for (int j = 0;j < i-1;j++){
                last_val = last_val - A[j];
            }

            last_val += (n-1)*A[i-1];
            max = Math.max((int)last_val, max);
        }
        return max;
    }

    private static int Cal(int[] nums){
        long sum = 0;
        int k = 0;
        for (long val : nums) {
            sum += k * val;
            k++;
        }
        return (int)sum;
    }
}
