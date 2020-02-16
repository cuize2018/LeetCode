package leet;

import java.util.Stack;

public class Solution503 {
    public static void main(String[] args) {
        int[] a = {1,2,1};
        System.out.println(nextGreaterElements(a));
    }

    /**
     * 我们首先把第一个元素 A[1] 放入栈，随后对于第二个元素 A[2]，
     * 如果 A[2] > A[1]，那么我们就找到了 A[1] 的下一个更大元素 A[2]，此时就可以把 A[1] 出栈并把 A[2] 入栈；
     * 如果 A[2] <= A[1]，我们就仅把 A[2] 入栈。对于第三个元素 A[3]，此时栈中有若干个元素，
     * 那么所有比 A[3] 小的元素都找到了下一个更大元素（即 A[3]），因此可以出栈，在这之后，我们将 A[3] 入栈，以此类推。
     *
     * 可以发现，我们维护了一个单调栈，栈中的元素从栈顶到栈底是单调不降的。
     * 当我们遇到一个新的元素 A[i] 时，我们判断栈顶元素是否小于 A[i]，
     * 如果是，那么栈顶元素的下一个更大元素即为 A[i]，我们将栈顶元素出栈。
     * 重复这一操作，直到栈为空或者栈顶元素大于 A[i]。此时我们将 A[i] 入栈，保持栈的单调性，
     * 并对接下来的 A[i + 1], A[i + 2] ... 执行同样的操作。
     *
     * 由于这道题的数组是循环数组，因此我们需要将每个元素都入栈两次。
     * 这样可能会有元素出栈找过一次，即得到了超过一个“下一个更大元素”，我们只需要保留第一个出栈的结果即可。
     *
     * @param nums
     * @return
     */
    public static int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();

        //当 nums.length <= i < 2*nums.length 时. 寻找的是每个元素右边的第一个较大元素
        //当 0 <= i < nums.length 时，  寻找的是每个元素左边的第一个较大元素
        for (int i = 2*nums.length-1; i>=0; i--){
            int real_index = i%nums.length;

            while (!stack.isEmpty() && nums[stack.peek()] <= nums[real_index] ){
                stack.pop();
            }

            res[real_index] = stack.isEmpty()?-1:nums[stack.peek()];
            stack.push(real_index);
        }
        return res;
    }
}
