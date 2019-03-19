import java.util.*;
class Solution {
    public int findCircleNum(int[][] M) {
        int ans = 0;
        int m = M.length, n = M[0].length;
        for (int i = 0; i < m; i++) {
            if (M[i][i] == 1) {
                dfs(map, i);
                ans++;
            }
        }
        return ans;
    }
    private void dfs(int[][] M, int i) {
        if (M[i][i] == 0) return;
        M[i][i] = 0;
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1) {
                M[i][j] = 0;
                dfs(M, j);
            }
        }
    }
}