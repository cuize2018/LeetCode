package leet;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.HashMap;

public class Solution166 {
    public static void main(String[] args){
        int numerator = 1;
        int denominator = 6;
        System.out.println(fractionToDecimal(numerator,denominator));
    }

    /**
     * 核心思想是当余数出现循环的时候，对应的商也会循环。
     *
     * ... 余数为 1，标记 1 出现在位置 0。
     * ... 余数为 4，标记 4 出现在位置 1。
     * ... 余数为 4，在位置 1 出现过，所以循环节从位置 1 开始，为 1(6)。
     * ​
     * 算法
     *
     * 需要用一个哈希表记录余数出现在小数部分的位置，当你发现已经出现的余数，就可以将重复出现的小数部分用括号括起来。
     *
     * 再出发过程中余数可能为 0，意味着不会出现循环小数，立刻停止程序。
     */
    public static String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0)return null;
        if (numerator == 0)return "0";

        StringBuilder fraction = new StringBuilder();
        if (numerator < 0 ^ denominator < 0) {
            fraction.append("-");
        }

        long numeratorLong = Math.abs(Long.valueOf(numerator));
        long denominatorLong = Math.abs(Long.valueOf(denominator));

        fraction.append(String.valueOf(numeratorLong/denominatorLong));
        long remainder = numeratorLong % denominatorLong;
        if (remainder == 0)return fraction.toString();

        fraction.append(".");
        HashMap<Long, Integer> map = new HashMap<>();

        while (remainder != 0){
            if (map.containsKey(remainder)){
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }

            map.put(remainder, fraction.length());
            remainder *= 10;

            fraction.append(remainder/denominatorLong);
            remainder = remainder%denominatorLong;
        }
        return fraction.toString();
    }
}
