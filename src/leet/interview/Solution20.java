package leet.interview;

public class Solution20 {
    public static void main(String[] args) {
        String s = "0..";
        System.out.println(isNumber(s));
    }

    public static boolean isNumber(String s) {
        if (s.contains("f") || s.contains("D"))return false;
        try {
            double v = Double.parseDouble(s);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
