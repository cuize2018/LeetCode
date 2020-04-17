package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution212 {
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        String[] words = {"oath", "pea", "eat", "rain"};
        char[][] board =
                {
                        {'o', 'a', 'a', 'n'},
                        {'e', 't', 'a', 'e'},
                        {'i', 'h', 'k', 'r'},
                        {'i', 'f', 'l', 'v'}
                };
        Solution212 solution212 = new Solution212();
        System.out.println(solution212.findWords(board, words));
    }

    /**
     * 解法一中的想法是，从 words 中依次选定一个单词 -> 从图中的每个位置出发，看能否找到这个单词
     *
     * 我们其实可以倒过来。从图中的每个位置出发 -> 看遍历过程中是否遇到了 words 中的某个单词
     *
     * 遍历过程中判断是否遇到了某个单词，我们可以事先把所有单词存到前缀树中。
     * 这样的话，如果当前走的路径不是前缀树的前缀，我们就可以提前结束了。如果是前缀树的中的单词，我们就将其存到结果中。
     *
     * 至于实现的话，我们可以在遍历过程中，将当前路径的单词传进函数，然后判断当前路径构成的单词是否是在前缀树中出现即可。
     *
     * 这个想法可行，但不够好，因为每次都从前缀树中判断当前路径的单词，会带来重复的判断。
     * 比如先判断了 an 存在于前缀树中，接下来假如路径变成 ang ，判断它在不在前缀中，又需要判断一遍 an 。
     *
     * 因此，我们可以将前缀树融合到我们的算法中，递归中去传递前缀树的节点，判断当前节点的孩子是否为 null，如果是 null 说明当前前缀不存在，可以提前结束。
     * 如果不是 null，再判断当前节点是否是单词的结尾，如果是结尾直接将当前单词加入。
     *
     * 由于递归过程中没有加路径，所以我们改造一下前缀树的节点，将单词直接存入节点，这样的话就可以直接取到了。
     * 结合代码就很好懂了，就是从每个位置对图做深度优先搜索，
     * 然后路径生成的字符串如果没有在前缀树中出现就提前结束，如果到了前缀树中某个单词的结束，就将当前单词加入即可。
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        TrieWithWord trieWithWord = new TrieWithWord();
        for (String word : words) {
            trieWithWord.insert(word);
        }

        List<String> res = new ArrayList<>();
        int rows = board.length;
        if (rows == 0) return new ArrayList<>();
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                existRecursive(board, i, j, trieWithWord.root, res);
            }
        }
        return res;
    }

    private void existRecursive(char[][] board, int row, int col, TrieNodeWithWord node, List<String> res) {
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) return;

        char c = board[row][col];
        //当前节点遍历过或者将要遍历的字母在前缀树中不存在
        if (c == '#' || node.children[c - 'a'] == null) return;

        node = node.children[c - 'a'];
        //判断当前节点是否是一个单词的结束
        if (node.word != null) {
            //加入到结果中
            res.add(node.word);
            //将单词设置为null，防止重复添加
            node.word = null;
        }

        char temp = board[row][col];
        board[row][col] = '#';

        //上下左右去遍历
        for (int[] dir : dirs) {
            existRecursive(board, row + dir[0], col + dir[1], node, res);
        }
        board[row][col] = temp;
    }

}

class TrieNodeWithWord {
    public TrieNodeWithWord[] children;
    public String word; //节点直接存当前的单词

    public TrieNodeWithWord() {
        children = new TrieNodeWithWord[26];
        word = null;
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
    }
}

class TrieWithWord {
    public TrieNodeWithWord root;

    /**
     * Initialize your data structure here.
     */
    public TrieWithWord() {
        root = new TrieNodeWithWord();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNodeWithWord curr = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 当前孩子是否存在
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNodeWithWord();
            }
            curr = curr.children[c - 'a'];
        }
        // 当前节点结束，存入当前单词
        curr.word = word;
    }
}


