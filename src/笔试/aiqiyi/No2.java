package 笔试.aiqiyi;

import java.util.*;

public class No2 {
    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int n = s.length();

//        Set<Integer> lset = new HashSet<>();
//        Set<Integer> rset = new HashSet<>();

        Set<String> set = new HashSet<>();

        int x = 0;
        int y = 0;
        int flag = 0;
        set.add(x + "^^" + y);

        for (int k = 0; k < n; k++) {
            char dir = s.charAt(k);
            if (dir == 'N'){
                x = x - 1;
            }
            else if (dir == 'W'){
                y = y-1;
            }
            else if (dir == 'S'){
                x = x+1;
            }
            else if (dir == 'E'){
                y = y+1;
            }

            if (set.contains(x +"^^"+y)){
                flag = 1;
               break;
            }
            set.add(x+"^^"+y);
        }
        if (flag == 1) System.out.println("False");
        else  System.out.println("True");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int n = s.length();

        Set<Pair> set = new HashSet<>();

        int x = 0;
        int y = 0;
        int flag = 0;
        set.add(new Pair(x,y));

        for (int k = 0; k < n; k++) {
            char dir = s.charAt(k);
            if (dir == 'N'){
                x = x - 1;
            }
            else if (dir == 'W'){
                y = y-1;
            }
            else if (dir == 'S'){
                x = x+1;
            }
            else if (dir == 'E'){
                y = y+1;
            }
            Pair temp = new Pair(x,y);

            if (set.contains(temp)){
                flag = 1;
                break;
            }
            set.add(temp);
        }
        if (flag == 1) System.out.println("True");
        else  System.out.println("False");
    }
}
class Pair {
    int x;
    int y;

    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode(){
        return x + y;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Pair))
        {
            return false;
        }
        Pair pn = (Pair)o;
        return pn.x == x && pn.y == y;
    }
}