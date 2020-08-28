package 笔试.didi;
import java.util.*;

public class No1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int cnt = 0;
        List<Pair> res = new ArrayList<>();

        for (int a = 1; a <= 9; a++) {
            for (int c = 0; c <= 9; c++) {
                int r = a*100 + c*10 + c;
                for (int b = 0; b <= 9; b++) {
                    if (a == b || b == c || a == c)continue;

                    int l = a*100 + b*10 + c;
                    if (l + r == n){
                        res.add(new Pair(l,r));
                        cnt++;
                    }
                }
            }
        }
        if (cnt == 0){
            System.out.println(0);
            return;
        }
        res.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.compare(o1.x, o2.x);
            }
        });

        System.out.println(cnt);
        for (Pair re : res) {
            System.out.println(re.x + " " + re.y);
        }
    }

    static class Pair{
        int x;
        int y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
