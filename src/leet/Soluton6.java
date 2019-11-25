package leet;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 */
public class Soluton6 {
    public static void main(String[] args){
        String s = "AB";
        int n = 1;
        Ans6_2 ans = new Ans6_2();
        String out = ans.convert(s, n);
        System.out.println(out);
    }
}

/**
 * 放入二维矩阵中
 */
class Ans6_1{
    public String convert(String s, int numRows){
        if (s.equals("")){
            return s;
        }
        if(numRows == 1){
            return s;
        }

        int len = s.length();
        int sub = numRows + numRows - 2;
        int num_subs = len / sub;
        int left = len % sub;

        int numCols = num_subs*(numRows-2) + num_subs;
        if (left > 0){
            numCols = numCols + 1;
            if (left-numRows > 0){
                numCols += (left-numRows);
            }
        }

        char[][] mat = new char[numRows][numCols];
        int col_last = 0;
        for (int i = 0;i<num_subs;i++){
            char[] sub_str = get_sub(s, i, sub);

            for (int j = 0;j<numRows;j++){
                mat[j][i*(numRows-1)] = sub_str[j];
                col_last = i*(numRows-1);
            }
            for (int j = numRows-2; j>0; j--){
                mat[j][i*(numRows-1)+(sub-numRows-j+1)] = sub_str[sub-j];
                col_last = i*(numRows-1)+(sub-numRows-j+1);
            }
        }

        if (num_subs > 0){ col_last++;}
        if (left > 0){
            for (int i = 0;i<Math.min(left,numRows);i++){
                mat[i][col_last] = s.charAt(len - left + i);
            }
            for (int j = 0;j<Math.max(left-numRows,0);j++){
                mat[numRows-2-j][col_last+j+1] = s.charAt(len-(left - numRows - j));
            }
        }

        String str_out = "";
        for (int i = 0;i<numRows;i++){
            for (int j = 0;j<numCols;j++){
                if (mat[i][j] != '\0'){
                    str_out += String.valueOf(mat[i][j]);
                }
            }
        }
        return str_out;
    }

    char[] get_sub(String s, int index, int sub_len){
        char[] temp = new char[sub_len];
        for (int i = 0;i<sub_len;i++){
            temp[i] = s.charAt(index*sub_len + i);
        }
        return temp;
    }
}

/**
 * 按行放置字符，并设置一个标记判断是向上还是向下，每一行里存储的是i一个StringBuilder
 */
class Ans6_2{
    public String convert(String s, int numRows){
        if (numRows == 1)return s;
        boolean goDown = false;
        int currentRow = 0;
        List<StringBuilder> rows = new ArrayList<>();

        for (int i = 0; i<Math.min(numRows, s.length()); i++){
            rows.add(new StringBuilder());
        }

        for (char c: s.toCharArray()){
            rows.get(currentRow).append(c);

            if (currentRow == 0 || currentRow == numRows-1){
                goDown = !goDown;
            }
            currentRow += goDown? 1:-1;

        }

        StringBuilder s_t = new StringBuilder();
        for (StringBuilder str : rows){
            s_t.append(str);
        }
        return s_t.toString();
    }
}
