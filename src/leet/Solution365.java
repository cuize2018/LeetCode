package leet;

import java.util.*;

/**
 * 这道问题其实可以转换为有一个很大的容器，我们有两个杯子，容量分别为x和y，问我们通过用两个杯子往里倒水，和往出舀水，
 * 问能不能使容器中的水刚好为z升。那么我们可以用一个公式来表达：
 * z = m * x + n * y
 * 其中m，n为舀水和倒水的次数，正数表示往里舀水，负数表示往外倒水，那么题目中的例子可以写成:
 * 4 = (-2) * 3 + 2 * 5，即3升的水罐往外倒了两次水，5升水罐往里舀了两次水。
 * 那么问题就变成了对于任意给定的x,y,z，存不存在m和n使得上面的等式成立。
 * 根据裴蜀定理，ax + by = d的解为 d = gcd(x, y)，那么我们只要只要z % d == 0，上面的等式就有解，所以问题就迎刃而解了，
 * 我们只要看z是不是x和y的最大公约数的倍数就行了，别忘了还有个限制条件x + y >= z，因为x和y不可能称出比它们之和还多的水，
 */
public class Solution365 {
    public static void main(String[] args) {
        int x = 2;
        int y = 6;
        int z = 5;
        System.out.println(canMeasureWater2(x, y, z));
    }

    public static boolean canMeasureWater(int x, int y, int z) {
        if (z == 0) {
            return true;
        }

        if (x + y >= z) {
            int gcd_val = gcd(x, y);
            return (z % gcd_val) == 0;
        } else {
            return false;
        }

    }

    private static int gcd(int x, int y) {
        if (y == 0) {
            return x;
        } else {
            return gcd(y, x % y);
        }
    }

    /**
     * BFS
     * 注意到这道题操作水壶的时候，两个水壶不可能同时都是半满的。
     * 如果某个水壶是半满的，另外一个肯定是满的或者空的。
     * 而且如果某个水壶是半满的（此时另外一个就是空的或者满的），就不能直接把这个水壶填满，也不能把这个半满的水倒掉，因为这会回到初始状态，这么做没有意义。
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static boolean canMeasureWater2(int x, int y, int z) {

        Queue<Pair> unVisited = new ArrayDeque<>();
        Set<Pair> visited = new HashSet<>();

        Pair start = makePair(0, 0);
        unVisited.add(start);
        visited.add(start);

        while (!unVisited.isEmpty()) {
            Pair point = unVisited.remove();
            int currX = point.getX();
            int currY = point.getY();

            if (currX + currY == z || currX == z || currY == z) {
                return true;
            }

//          边表示操作方法：分别为倒满1/2，倒空1/2，1倒入2，2倒入1 六种方法。
            if (currX == 0){
                //把第一个桶填满
                Pair temp = makePair(x, currY);
                if (!visited.contains(temp)){
                    unVisited.add(temp);
                    visited.add(temp);
                }
            }

            if (currY == 0){
                //把第二个桶填满
                Pair temp = makePair(currX, y);
                if (!visited.contains(temp)){
                    unVisited.add(temp);
                    visited.add(temp);
                }
            }

            if (currX < x){
                //把第二个桶倒空
                Pair temp = makePair(currX, 0);
                if (!visited.contains(temp)){
                    unVisited.add(temp);
                    visited.add(temp);
                }
            }

            if (currY < y){
                //把第一个桶倒空
                Pair temp = makePair(0, currY);
                if (!visited.contains(temp)){
                    unVisited.add(temp);
                    visited.add(temp);
                }
            }

            // y - curY是第二个桶还可以再加的水的升数，但是最多只能加curX升水。
            int moveSize = Math.min(y-currY, currX);
            // 把第一个桶里的curX升水倒到第二个桶里去。
            Pair temp = makePair(currX-moveSize, currY+moveSize);
            if (!visited.contains(temp)){
                unVisited.add(temp);
                visited.add(temp);
            }
            // 反过来同理，x - curX是第一个桶还可以再加的升数，但是最多只能加curY升水。
            int moveSize2 = Math.min(x-currX, currY);
            // 把第一个桶里的curX升水倒到第二个桶里去。
            Pair temp2 = makePair(currX+moveSize2, currY-moveSize2);
            if (!visited.contains(temp2)){
                unVisited.add(temp2);
                visited.add(temp2);
            }
        }
        return false;
    }

    private static Pair makePair(int x, int y) {
        return new Pair(x,y);
    }

}

