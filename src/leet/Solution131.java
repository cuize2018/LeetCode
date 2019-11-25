package leet;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution131 {
    private List<List<String>> out = new ArrayList<>();
    private Stack<String> one = new Stack<>();

    public static void main(String[] args){
        String s = "abbcd";
        Solution131 sol = new Solution131();

        System.out.println(sol.partition(s).toString());
    }

    public List<List<String>> partition(String s) {
        part(s);
        return out;
    }

    public void part(String s){
        int len = s.length();
        if (len == 0){
            out.add(new ArrayList<>(one));
        }

        for (int i = 1 ;i <= len;i++){
            String tmp = s.substring(0,i);
            if (this.isRight(tmp)){
                one.push(tmp);
                part(s.substring(i));
            }
            else {
                continue;
            }
            one.pop();
        }
    }

    private boolean isRight(String sub){
        StringBuilder s = new StringBuilder(sub);
        String rev = s.reverse().toString();

        return sub.equals(rev);
    }
}
