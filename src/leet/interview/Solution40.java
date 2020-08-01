package leet.interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution40 {
    public static void main(String[] args) {
        int[] arr = {0,0,1,3,4,5,0,7,6,7};
        int k = 9;
//        System.out.println(Arrays.toString(getLeastNumbers3(arr, k)));
        swap(arr, 1, 2);
        System.out.println(Arrays.toString(arr));
    }
    /**
     * 使用一个大小为k的大顶堆，每次当元素个数大于k时排出最大的元素，则最终剩下的就是最小的k个元素
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) return new int[0];

        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for (int val : arr) {
            heap.add(val);
            if (heap.size() > k) {
                heap.remove();
            }
        }
        int[] out = new int[k];
        int i = 0;
        for (int val : heap) {
            out[i] = val;
            i++;
        }
        return out;
    }

    public static int[] getLeastNumbers3(int[] arr, int k){
        if (arr.length < k)return arr;

        quickSort(arr, k, 0, arr.length-1);
        return Arrays.copyOfRange(arr, 0, k);
    }

    private static void quickSort(int[] arr, int k, int low, int high) {
        if (low >= high)return;
        int m = partition(arr,low,high);
        if (m == k)return;

        if (m < k){
            quickSort(arr, k, m+1, high);
        }
        else {
            quickSort(arr, k, low, m - 1);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int i = low;
        int j = high + 1;
        int v = arr[low];
        while (true){
            while (arr[++i] < v){
                if (i == high)break;
            }
            while (arr[--j] > v){
                if (j == low)break;
            }
            if (i >= j)break;
            swap(arr, i, j);
        }
        swap(arr, low, j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
