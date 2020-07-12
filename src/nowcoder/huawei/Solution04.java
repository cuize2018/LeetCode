package nowcoder.huawei;

import java.util.Scanner;

public class Solution04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            String[] nums = s.split(" ");

            int numOfNeg = 0;
            int numOfPos = 0;
            double sum = 0;
            for (String num : nums) {
                int v = Integer.parseInt(num);
                if (v < 0)numOfNeg++;
                else {
                    sum += v;
                    numOfPos++;
                }
            }
            System.out.println(numOfNeg);
            if (numOfPos != 0) {
                System.out.printf("%.1f", sum / numOfPos);
            }
            else {
                System.out.println("0.0");
            }
        }
    }
}
