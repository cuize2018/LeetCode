package leet.interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution40 {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 1};
        int k = 1;
        System.out.println(Arrays.toString(getLeastNumbers2(arr, k)));
    }

    public static int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOfRange(arr, 0, k);
    }

    /**
     * 使用一个大小为k的大顶堆，每次当元素个数大于k时排出最大的元素，则最终剩下的就是最小的k个元素
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] getLeastNumbers2(int[] arr, int k) {
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

    public static int[] getLeastNumbers3(int[] arr, int k) {
        if (k == 0) return new int[0];
        else if (arr.length <= k) {
            return arr;
        }

        //原地不断划分数组
        quickSearch(arr, 0, arr.length - 1, k);
        return Arrays.copyOfRange(arr, 0, k);
    }

    private static void quickSearch(int[] arr, int low, int high, int k) {
        //第一次划分操作
        int m = partition(arr, low, high);
        // 此时数组前 m 个数，就是最小的 m 个数
        if (m == k) {
            //此时数组前m个数就是最小的k个数
            return;
        } else if (k < m) {
            //此时前k个最小的数被包含在了最小的前m个数中，在左侧数组中寻找前k个数
            quickSearch(arr, low, m - 1,k);
        } else {
            //此时前m个数既是前k个最小数中的前m个，在右侧数组寻找前k-m个数
            quickSearch(arr, m + 1, high,k);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int left = low;
        int right = high +1;
        int v = arr[low];

        while (true){
            while (arr[++left] < v){
                if (left == high){
                    break;
                }
            }

            while (arr[--right] > v){
                if (right == low){
                    break;
                }
            }

            if (left >= right)break;

            swap(arr, left, right);
        }
        swap(arr, low, right);
        // arr[lo .. j-1] <= arr[j] <= arr[j+1 .. hi]
        return right;
    }

    private static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
