package leet.interview;

public class Solution17 {
    public static void main(String[] args) {
        int n = 4;
        printNumbers(n);
    }

    /**
     * 大数解法
     *
     * @param n
     */
    public static void printNumbers(int n) {
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < n; i++) {
            num.append('0');
        }
        
        while (increase(num)){
            System.out.println(num.toString() + " , ");
        }
    }

    private static boolean increase(StringBuilder num) {
        int add = 0;
        for (int i = num.length() - 1; i >=0 ; i--) {
            int val = num.charAt(i) - '0';
            if (i == num.length() - 1)val++;
            val = val + add;
            if (val >= 10){
                val -= 10;
                add = 1;
            }
            else {
                add = 0;
            }
            num.setCharAt(i, (char) (val + '0'));
            if (add == 0)return true;
        }
        if (add != 0)return false;
        return true;
    }
}
