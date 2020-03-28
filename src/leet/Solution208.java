package leet;

import java.util.HashSet;

public class Solution208 {
    public static void main(String[] args) {

    }
}

class Trie {
    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode curr = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.getChildren()[c - 'a'] == null) {
                curr.getChildren()[c - 'a'] = new TrieNode(c);
            }
            curr = curr.getChildren()[c - 'a'];
        }
        curr.setEnd();
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode curr = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.getChildren()[c - 'a'] == null) {
                return false;
            }
            curr = curr.getChildren()[c - 'a'];
        }
        return curr.isEnd();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (curr.getChildren()[c - 'a'] == null) {
                return false;
            }
            curr = curr.getChildren()[c - 'a'];
        }
        return true;
    }
}