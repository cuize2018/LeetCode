package leet;

import java.util.*;

public class Solution841 {
    public static void main(String[] args) {

    }


    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] visited = new boolean[rooms.size()];
        visited[0] = true;
        int cnt = 1;

        while (!queue.isEmpty()) {
            Integer room = queue.remove();
            List<Integer> neighbors = rooms.get(room);
            for (Integer neighbor : neighbors) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    cnt++;
                }
            }
        }
        return cnt == rooms.size();
    }
}
