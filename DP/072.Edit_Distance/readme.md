# Leetcode 72. Edit Distance

## Description

Given two words *word1* and *word2*, find the minimum number of operations required to convert *word1* to *word2*.

You have the following 3 operations permitted on a word:

1. Insert a character
2. Delete a character
3. Replace a character

**Example 1:**

```
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
```

## Solution

```java
/**
    Solved by DP
dp[i][j]: represents the minimum steps needed to convert word1[0:i] to word2[0:j]
Base cases:
    1. i = 0. The only way to edit an empty string into word2[0:j] is to insert j characters. Therefore, dp[0][j] = j
    2. j = 0. The only way to edit word1 into an empty string is to delete all characters. Therefore, dp[i][0] = i
Suppose we've already known dp[i-1][j-1], there are two conditions:
    1. word1[i] == word2[j], then dp[i][j] = dp[i-1][j-1]
    2. word1[i] != word2[j], then there can be three different ways to edit word1[0:i] into word2[0:j]:
        a. edit word1[0:i-1] into word2[0:j-1], then directly replace the word1[i] into word2[j], therefore, dp[i][j] = dp[i-1][j-1]+1
        b. edit word1[0:i-1] into word2[j], then delete the word1[i], therefore, dp[i][j] = dp[i-1][j]+1
        c. edit word1[0:i] into word2[0:j-1], then insert word2[j] into word1, therefore, dp[i][j] = dp[i][j-1]+1
        The minimum steps in this condition should be the minimum value of the three ways listed above
*/
public int minDistance(String word1, String word2) {
  int m = word1.length(), n = word2.length();
  int[][] dp = new int[m+1][n+1];
  for (int i = 0; i <= m; i++) dp[i][0] = i;
  for (int i = 0; i <= n; i++) dp[0][i] = i;
  for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
      if (word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
      else {
        dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
      }
    }
  }
  return dp[m][n];
}
```

