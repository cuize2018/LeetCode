package leet;

import org.omg.CORBA.MARSHAL;

import java.util.*;

public class Solution89 {
    private List<Integer> out = new ArrayList<>();
    private Stack<String> binary = new Stack<>();
    private Queue<String> temp = new ArrayDeque<>();

    public static void main(String[] args){
        Solution89 s = new Solution89();

        System.out.println(s.grayCode2(5));
        //System.out.println(s.binary);
    }

    public List<Integer> grayCode(int n) {
        if (n == 0){
            out.add(0);
            return out;
        }

        backTrack(n-1, "0");
        while (temp.size() > 0){
            if (check(temp.peek(), binary.peek())){
                String t = temp.remove();
                out.add(trans(t));
                binary.push(t);
            }
            else {
                String t = temp.remove();
                temp.add(t);
            }
        }
        backTrack(n-1, "1");
        while (temp.size() > 0){
            if (check(temp.peek(), binary.peek())){
                String t = temp.remove();
                out.add(trans(t));
                binary.push(t);
            }
            else {
                String t = temp.remove();
                temp.add(t);
            }
        }

        return out;
    }

    public void backTrack(int rest_len, String already_num){
        if (rest_len == 0){
            if (out.isEmpty()){
                out.add(trans(already_num));
                binary.push(already_num);
            }
            else {
                if (check(already_num, binary.peek())){
                    out.add(trans(already_num));
                    binary.push(already_num);
                }
                else {
//                    Queue<String> qt = new ArrayDeque<>();
//                    while (temp.size() > 0){
//                        if (check(temp.peek(), binary.peek())){
//                            String t = temp.remove();
//                            out.add(trans(t));
//                            binary.push(t);
//                            break;
//                        }
//                        else {
//                            String t = temp.remove();
//                            qt.add(t);
//                        }
//                    }
//                    temp.addAll(qt);
                    temp.add(already_num);
                }
            }
        }
        else {
            backTrack(rest_len-1, already_num+"0");
            backTrack(rest_len-1, already_num+"1");
        }
    }

    private int trans(String s){
        int len = s.length();
        int sum = 0;
        for (int i = 1;i <= len;i++){
            sum += Integer.parseInt(s.substring(i-1,i))*Math.pow(2,len-i);
        }
        return sum;
    }

    private boolean check(String s, String last){
        int count = 0;
        for (int i = 0;i<s.length();i++){
            if (s.charAt(i) != last.charAt(i)){
                count++;
                if (count > 1){
                    return false;
                }
            }
        }
        return true;
    }

    /**动态规划, 根据n-1的解得到n的解
     * https://leetcode-cn.com/problems/gray-code/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--12/
     * 所以如果知道了 n = 2 的解的话，如果是 { 0, 1, 3, 2}，那么 n = 3 的解就是
     * { 0, 1, 3, 2, 2 + 4, 3 + 4, 1 + 4, 0 + 4 }，即 { 0 1 3 2 6 7 5 4 }。之前的解直接照搬过来，然后倒序把每个数加上 1 << ( n - 1) 添加到结果中即可。
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode2(int n) {
        if (n == 0){
            out.add(0);
            return out;
        }

        out.add(0);
        for (int i = 0;i < n;i++){
            int add_num = (int)Math.pow(2,i);
            for (int j = out.size()-1;j>=0;j--){
                int tmp = out.get(j);
                out.add(tmp+add_num);
            }
        }

        return out;
    }
}
