package leet;

/**
 * 前缀树节点
 */
public class TrieNode {
    private char val;
    private TrieNode[] children;
    private boolean isEnd;

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd() {
        isEnd = true;
    }

    public TrieNode(char val) {
        this.val = val;
        children = new TrieNode[26];
    }

    public TrieNode() {
        children = new TrieNode[26];
    }

    public char getVal() {
        return val;
    }

    public TrieNode[] getChildren() {
        return children;
    }
}
