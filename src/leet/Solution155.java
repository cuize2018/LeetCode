package leet;

import java.util.Stack;

public class Solution155 {
    public static void main(String[] args) {

    }
}

/**
 * 存最小值的栈的具体操作流程如下：
 *     将第一个元素入栈。
 *
 *     新加入的元素如果大于栈顶元素，那么新加入的元素就不处理。
 *     新加入的元素如果小于等于栈顶元素，那么就将新元素入栈。
 *
 *     出栈元素不等于栈顶元素，不操作。
 *     出栈元素等于栈顶元素，那么就将栈顶元素出栈。
 *
 */
//class MinStack {
//    private Stack<Integer> stack;
//    private Stack<Integer> minStack; //辅助栈，存最小值
//    /** initialize your data structure here. */
//    public MinStack() {
//        stack = new Stack<>();
//        minStack = new Stack<>();
//    }
//
//    public void push(int x) {
//        stack.push(x);
//        if (minStack.isEmpty() || minStack.peek() >= x){
//            minStack.push(x);
//        }
//    }
//
//    public void pop() {
//        int val = stack.pop();
//        if (!minStack.isEmpty() && val == minStack.peek()){
//            minStack.pop();
//        }
//    }
//
//    public int top() {
//        return stack.peek();
//    }
//
//    public int getMin() {
//        return minStack.peek();
//    }
//}

/**
 * 如果只用一个变量就会遇到一个问题，如果把 min 更新为 2，那么之前的最小值 3 就丢失了。
 * 怎么把 3 保存起来呢？把它在 2 之前压入栈中即可。
 *
 * 上边的最后一个状态，当出栈元素是最小元素我们该如何处理呢？
 * 我们只需要把 min 出栈，然后再出栈一次，把 出栈值 赋值给 min 即可。
 *
 * 入栈 2 ，同时将之前的 min 值 3 入栈，再把 2 入栈，同时更新 min = 2
 * | 2 |   min = 2
 * | 3 |
 * | 5 |
 * |_3_|
 * stack
 *
 * 入栈 6
 * | 6 |  min = 2
 * | 2 |
 * | 3 |
 * | 5 |
 * |_3_|
 * stack
 *
 * 出栈 6
 * | 2 |   min = 2
 * | 3 |
 * | 5 |
 * |_3_|
 * stack
 *
 * 出栈 2, 我们只需要把 2 出栈，然后再出栈一次，把 3 赋值给 min 即可。
 * |   |
 * | 5 |   min = 3
 * |_3_|
 * stack
 */
class MinStack {
    private Stack<Integer> stack;
    private int min;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (x <= min){
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        int val = stack.pop();
        if (val == min){
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
