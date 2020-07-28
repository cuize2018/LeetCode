package leet;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {9, 9, 5, 8, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] nums) {
        //1.构建大顶堆
        for (int i = (nums.length - 1) / 2; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(nums, i, nums.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int i = nums.length - 1; i >= 0; i--) {
            swap(nums, 0, i);//将堆顶元素与末尾元素进行交换
            adjustHeap(nums, 0, i);//重新对堆进行调整
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void adjustHeap(int[] nums, int i, int length) {
        int temp = nums[i];//先取出当前元素i
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && nums[k] < nums[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (nums[k] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                nums[i] = nums[k];
                i = k;
            } else break;
        }
        nums[i] = temp;//将temp值放到最终的位置
    }
}
