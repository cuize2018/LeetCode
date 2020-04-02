package leet;

import java.util.*;

public class Solution384 {
    private int[] nums;
    public Solution384(int[] nums) {
        this.nums = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    /** Returns a random shuffling of the array. */
    /** 暴力法 */
    public int[] shuffle() {
        int[] out = new int[nums.length];
        //set中存储数组所有的索引
        ArrayList<Integer> set = new ArrayList<>();
        for (int i = 0; i < out.length; i++) {
            set.add(i);
        }

        for (int i = out.length-1; i >= 0; i--) {
            Random random = new Random();
            //idx为索引数组set中的第idx个元素，即set的索引
            int idx = random.nextInt(i+1);
            //选择索引数组的第idx个元素，作为nums的索引
            out[i] = nums[set.get(idx)];
            set.remove(idx);
        }
        return out;
    }

    public int[] shuffle2() {
        int[] out = new int[nums.length];
        //set中存储数组所有的索引
        ArrayList<Integer> set = new ArrayList<>();
        for (int i = 0; i < out.length; i++) {
            set.add(nums[i]);
        }
        Collections.shuffle(set);
        for (int i = 0; i < out.length; i++) {
            out[i] = set.get(i);
        }
        return out;
    }
    /** 暴力法 */
    public int[] shuffle3() {
        int[] out = new int[nums.length];
        //拷贝一个数组
        ArrayList<Integer> copy = new ArrayList<>();
        for (int i = 0; i < out.length; i++) {
            copy.add(nums[i]);
        }

        for (int i = 0; i < out.length; i++) {
            //随机选择一个数
            int idx = new Random().nextInt(copy.size());
            out[i] = copy.get(idx);
            copy.remove(idx);
        }
        return out;
    }

    public int[] shuffle4() {
        int[] out = nums.clone();
        for (int i = 0; i < out.length; i++) {
            //随机选择一个数
            int idx = randRange(i, out.length);
            swap(out, i, idx);
        }
        return out;
    }

    public int[] shuffle5() {
        int[] out = nums.clone();
        for (int i = out.length; i > 1; i--) {
            swap(out, i-1, new Random().nextInt(i));
        }
        return out;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int randRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max-min)+min;
    }

}
