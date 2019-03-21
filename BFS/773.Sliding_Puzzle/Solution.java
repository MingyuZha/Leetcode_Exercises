import java.util.*;
class Solution {
    public int slidingPuzzle(int[][] board) {
        String target = "123450";
        String cur = "";
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                cur += board[i][j];
            }
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(cur);
        visited.add(cur);
        int step = 0;
        int[][] dir = {{1, 3}, {0,2,4}, {1,5}, {0,4}, {1,3,5}, {2,4}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                cur = queue.poll();
                if (cur.equals(target)) return step;
                int zero = cur.indexOf('0');
                for (int d : dir[zero]) {
                    String next = swap(cur, zero, d);
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }
    private String swap(String str, int i, int j) {
        StringBuilder builder = new StringBuilder(str);
        builder.setCharAt(i, str.charAt(j));
        builder.setCharAt(j, str.charAt(i));
        return builder.toString();
    }
    public static void main(String[] args) {
        Solution test = new Solution();
        int[][] board = {{1,2,3}, {5,4,0}};
        System.out.println(test.slidingPuzzle(board));
    }
}