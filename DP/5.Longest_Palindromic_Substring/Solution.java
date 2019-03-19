import java.util.*;
class Solution {
    public static String longestPalindrom(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int max_len = Integer.MIN_VALUE;
        String ans = "";
        int n = s.length();
        for (int i = n-1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j <= i+1 || dp[i+1][j-1]);
                if (dp[i][j] && j-i+1 > max_len) {
                    max_len = j-i+1;
                    ans = s.substring(i, j+1);
                }
            }
        }
        return ans;
    }
    public static String longestPalindrom2(String s) {
        int n = s.length();
        int max_len = Integer.MIN_VALUE;
        String ans = "";
        for (int i = 0; i < n; i++) {
            int len1 = expandFromCenter(s, i, i);
            if (len1 > max_len) { max_len = len1; ans = s.substring(i-len1/2, i+len1/2+1); }
            int len2 = expandFromCenter(s, i, i+1);
            if (len2 > max_len) { max_len = len2; ans = s.substring(i-(len2-2)/2, i+(len2-2)/2+2); }
        }
        return ans;
    }
    private static int expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right-left-1;
    }
    public static void main(String[] args) {
        String s = args[0];
        System.out.println(longestPalindrom2(s));
    }
}