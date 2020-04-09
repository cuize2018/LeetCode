package leet;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class Solution22 {
    List<String> out = new ArrayList<>();

    public static void main(String[] args) {
        Solution22 solution22 = new Solution22();
        System.out.println(solution22.generateParenthesis(3));
    }

    /**
     * 深度优先遍历 + 剪枝
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        if (n == 0) return new ArrayList<String>();
        dfs("(", 1, n);
        return out;
    }

    private void dfs(String root, int count, int n) {
        if (root.length() > n * 2 || count < 0) return;

        if (count == 0 && root.length() == n * 2) {
            out.add(root);
            return;
        }

        dfs(root + "(", count + 1, n);
        dfs(root + ")", count - 1, n);
    }

}
