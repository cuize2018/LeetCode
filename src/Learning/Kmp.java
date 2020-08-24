package Learning;

public class Kmp {
    private final String text;
    private final String pattern;
    private final int[] prefixTable;//前缀表，存有长度为i的前缀子串的最长公共前后缀长度

    public static void main(String[] args) {
        String s = "ABABABCABAABABABACBSD";
        String pattern = "ABABCABAA";
        Kmp kmp = new Kmp(s, pattern);
        kmp.createPrefixTable();
        kmp.movePrefixTable();
        int i = kmp.kmpSearch();
        System.out.println(i);
    }


    public Kmp(String t, String p) {
        text = t;
        pattern = p;
        prefixTable = new int[p.length()];
    }

    //创建前缀表
    private void createPrefixTable() {
        char[] p = pattern.toCharArray();
        int i = 1;
        prefixTable[0] = 0;
        int len = 0;
        while (i < prefixTable.length) {
            if (p[i] == p[len]) {
                len++;
                prefixTable[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = prefixTable[len - 1];
                } else {
                    prefixTable[i] = len;
                    i++;
                }
            }
        }
    }

    //右移前缀表
    private void movePrefixTable() {
        int i = prefixTable.length - 1;
        while (i > 0) {
            prefixTable[i] = prefixTable[i - 1];
            i--;
        }
        prefixTable[0] = -1;
    }

    //kmp搜索
    public int kmpSearch() {
        createPrefixTable();
        movePrefixTable();
        int i = 0;
        int j = 0;

        while (i < text.length()) {
            if (j == pattern.length() - 1) {
                //返回第一次遇到pattern的下标
                return i - j;
                //若还需接着匹配，则执行以下代码
                //j = prefixTable[j];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;j++;
            } else {
                j = prefixTable[j];
                if (j == -1) {
                    i++;j++;
                }
            }
        }
        return -1;
    }
}
