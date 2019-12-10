package leet;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class Solution390 {
    List<Integer> list = new ArrayList<>();
    public static void main(String[] args) {
        int n = 24;
        System.out.println(lastRemaining3(n));
    }


    public static int lastRemaining(int n) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int out = 0;
        for (int i = n;i>=1;i--){
            stack1.push(i);
        }
        boolean goRight = true;
        while (stack1.size() + stack2.size() > 1){
            if (goRight) {
                boolean del = true;
                while (stack1.size() > 0) {
                    if (del) {
                        stack1.pop();
                        del = false;
                    } else {
                        int val = stack1.pop();
                        stack2.push(val);
                        del = true;
                    }
                }
                goRight = false;
            }
            else {
                boolean del = true;
                while (stack2.size() > 0) {
                    if (del) {
                        stack2.pop();
                        del = false;
                    } else {
                        int val = stack2.pop();
                        stack1.push(val);
                        del = true;
                    }
                }
                goRight = true;
            }
        }
        if (!stack1.isEmpty())out = stack1.pop();
        else if (!stack2.isEmpty())out = stack2.pop();
        return out;
    }

    public static int lastRemaining2(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2;i <= n;i++){
            if (i <= 4){
                dp[i] = 2;
                continue;
            }
            if (i%2 == 0){
                if (i%4 == 0){
                    dp[i] = 2 + (dp[i/4]-1)*4;
                }
                else {
                    dp[i] = 4 + (dp[i/4]-1)*4;
                }
            }
            else {
                dp[i] = dp[i-1];
            }
        }
        return dp[n];
    }

    public static int lastRemaining3(int n) {
        return helper(n);
    }

    /**
     * 可以总结出
     * 当n为奇数时, 第一次从左往右删除元素时, 和n-1的偶数情况是遗址的, 所以, dp[n] = dp[n-1];
     * 当n为偶数时, 在经过一次从左往右和从右往左之后, 根据n是否可以整除4可得到:
     *     -- n可以整除4: 得到：2,6,10,14,...(是一个共有n/4项的等差数列) -->dp[n] = 2+(dp[n/4]-1)*4 (dp[n/4]即为范围为[1-n/4]时的结果为第x项)
     *     -- n不可以整除4: 得到：4,8,12,16,...(是一个共有n/4项的等差数列)-->dp[n] = 4+(dp[n/4]-1)*4
     * @param n
     * @return
     */
    private static int helper(int n){
        if (n == 1)return 1;
        if (n == 2)return 2;
        //在2<=n<=4的时候都是2
        if (n <= 4){
            return helper(2);
        }

        if (n%2 ==0){
            if (n%4 == 0){
                return 2 + (helper(n/4)-1)*4;
            }
            else {
                return 4 + (helper(n/4)-1)*4;
            }
        }
        else {
            return helper(n-1);
        }
    }

}
