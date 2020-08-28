package 笔试.meituan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class No2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int cnt = 0;
        List<String> left = new ArrayList<>();
        List<String> right = new ArrayList<>();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] s = line.split(" ");
            left.add(s[0]);
            right.add(s[1]);
        }

        String currentLoc = "##";

        if (!left.isEmpty()){
            currentLoc = left.get(0);
        }
        else {
            System.out.println(0);
        }

        for (int i = 0; i < right.size(); i++) {
            if (currentLoc.equals(right.get(i))){
                cnt++;
                if (i != right.size()-1){
                    currentLoc = left.get(i+1);
                }
            }
        }
        System.out.println(cnt);
    }
}
