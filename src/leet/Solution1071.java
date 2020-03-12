package leet;

public class Solution1071 {
    public static void main(String[] args) {
        String str1 = "ABABAB";
        String str2 = "ABAB";
        String s = gcdOfStrings(str1, str2);
        System.out.println(s);
    }

    /**
     * 从两个字符串中最大的作为窗口开始比较，若当前window大小不合适则window减1
     * @param str1
     * @param str2
     * @return
     */
    public static String gcdOfStrings(String str1, String str2) {
        int window = Math.min(str1.length(), str2.length());

        while (window >= 1) {
            if (str1.length() % window != 0 || str2.length() % window != 0) {
                window--;
                continue;
            }
            boolean isSuccessful = true;
            int i = 0;
            for (; (i <= str1.length() - window) && (i <= str2.length() - window); i += window) {
                String sub1 = str1.substring(i, i + window);
                String sub2 = str2.substring(i, i + window);
                if (!sub1.equals(sub2)) {
                    isSuccessful = false;
                    break;
                }
            }
            if (isSuccessful) {
                String temp_out = str1.substring(0, window);
                if (check(str1, str2, i, window, temp_out)) {
                    return temp_out;
                }
            }
            window--;
        }
        return "";
    }

    private static boolean check(String str1, String str2, int i, int window, String temp_out) {
        String str;
        str = str1.length() >= str2.length() ? str1 : str2;

        for (int j = i; j <= str.length() - window; j += window) {
            String str_temp = str.substring(j, j + window);
            if (!str_temp.equals(temp_out)) {
                return false;
            }
        }
        return true;
    }
}
