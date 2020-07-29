package leet;

import java.util.HashSet;

public class Solution208 {
    public static void main(String[] args) {
        Trie trie2 = new Trie();
        trie2.insert("world");
    }
}

class Trie {
    TrieNode2 root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode2('#');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode2 mov = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (mov.children[c - 'a'] == null){
                mov.children[c - 'a'] = new TrieNode2(c);
            }
            mov = mov.children[c-'a'];
        }
        mov.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null)return false;
        TrieNode2 mov = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (mov.children[c-'a'] == null){
                return false;
            }
            mov = mov.children[c-'a'];
        }
        return mov.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode2 mov = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (mov.children[c-'a'] == null){
                return false;
            }
            mov = mov.children[c-'a'];
        }
        return true;
    }
}

class TrieNode2{
    char val;
    TrieNode2[] children;
    boolean isEnd;

    public TrieNode2( char v){
        val = v;
        children = new TrieNode2[26];
        isEnd = false;
    }
}