package leet;

import java.util.Arrays;

public class Solution820 {
    public static void main(String[] args) {
        String[] words = {"time", "me", "bell"};
        System.out.println(minimumLengthEncoding(words));
    }

    public static int minimumLengthEncoding(String[] words) {
        TrieTree trie = new TrieTree();
        Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());

        int len = 0;
        for (String word : words) {
            // 单词插入trie，返回该单词增加的编码长度
            len += trie.Insert(word);
        }
        return len;
    }
}

/**
 * 前缀树
 */
class TrieTree {
    private TrieNode root;

    public TrieTree() {
        root = new TrieNode();
    }

    /**
     * 因为本题需要的是判断后缀，所以倒序插入单词
     * @param word
     * @return
     */
    public int Insert(String word) {
        TrieNode curr = root;
        boolean isNew = false;
        // 倒着插入单词
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            if (curr.getChildren()[c - 'a'] == null) {
                curr.getChildren()[c - 'a'] = new TrieNode(c);
                isNew = true;
            }
            curr = curr.getChildren()[c - 'a'];
        }
        // 如果是新单词的话编码长度增加新单词的长度+1，否则不变。
        return isNew ? word.length() + 1 : 0;
    }
}



