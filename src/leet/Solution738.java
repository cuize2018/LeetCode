package leet;

public class Solution738 {
    public static void main(String[] args) {
        int n = 332;
        System.out.println(monotoneIncreasingDigits(n));
    }

    /**
     * 当找到第一个左大于右的位i时，将该为该为数字num置为num-1;再把[i+1, end]所有位置为9;
     * 此时[i, end]区间即为最大值;
     * 循环直到没有变化为止
     * @param N
     * @return
     */
    public static int monotoneIncreasingDigits(int N) {
        StringBuilder str = new StringBuilder(String.valueOf(N));
        boolean flag = true;

        while (flag) {
            flag = false;
            for (int i = 0; i < str.length() - 1; i++) {
                if (str.charAt(i) > str.charAt(i + 1)) {
                    str.setCharAt(i, (char) (str.charAt(i)-1));
                    for (int j = i + 1; j < str.length(); j++) {
                        str.setCharAt(j, '9');
                    }
                    flag = true;
                    break;
                }
            }
        }

        String s = str.toString();
        return Integer.parseInt(s);
    }
}
