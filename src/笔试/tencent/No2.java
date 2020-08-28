package 笔试.tencent;

import java.util.*;

public class No2 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        String s = scanner.next();
        int k = scanner.nextInt();

        String res = func2(s, k);

//        char[] chars = s.toCharArray();
//        Arrays.sort(chars);
//        System.out.println(chars[k-1]);

        System.out.println(res);
    }

    private static String func(String s, int k) {
        char[] chars = s.toCharArray();
        TreeSet<String> temp = new TreeSet<>();
        for (char c : chars) {
            temp.add(String.valueOf(c));
        }

        if (temp.size() == k){
            ;
        }
        else {
            for (int len = 1; len <= s.length(); len++) {
                for (int i = 0; i <= s.length() - len; i++) {
                    temp.add(s.substring(i, i + len));
                }
                if (temp.size() == k)break;
            }
        }

        int i = 0;
        Iterator<String> iterator = temp.iterator();
        String res = null;
        while (iterator.hasNext() && i < k){
            res = iterator.next();
            i++;
        }
        return res;
    }

    private static String func2(String s, int k) {
        TreeSet<String> temp = new TreeSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 1; j <= 5; j++) {
                if (i+j < s.length()) {
                    temp.add(s.substring(i, i + j));
                }
            }
        }
        int i = 0;
        Iterator<String> iterator = temp.iterator();
        String res = null;
        while (iterator.hasNext() && i < k){
            res = iterator.next();
            i++;
        }
        return res;
    }

}
