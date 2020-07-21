package leet.interview;

import java.util.Arrays;

public class Solution17_14 {
    public static void main(String[] args) {
        int[] arr = {4,9,8,7,6,5,1};
//        int[] arr = {1,2,3};
        int k = 4;

        int[] res = smallestK(arr, 7);
        System.out.println(Arrays.toString(res));
    }

    public static int[] smallestK(int[] arr, int k) {
        int len = arr.length;
        if (len == 0) return new int[0];
        if (len < k) return arr;

        quickSearch(arr, 0, len - 1, k);
        return Arrays.copyOfRange(arr, 0, k);
    }

    private static void quickSearch(int[] arr, int left, int right, int k) {
        int m = partition(arr, left, right);
        if (m == k) return;

        if (m > k) {
            quickSearch(arr, left, m - 1, k);
        } else {
            quickSearch(arr, m + 1, right, k);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        if (low >= high)return low;
        int left = low;
        int right = high + 1;
        int v = arr[low];

        while (true) {
            while (arr[++left] < v) {
                if (left == high) break;
            }
            while (arr[--right] > v) {
                if (right == low) break;
            }
            if (left >= right) break;
            swap(arr, left, right);
        }
        swap(arr, low, right);
        return right;
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[y];
        arr[y] = arr[x];
        arr[x] = temp;
    }
}
