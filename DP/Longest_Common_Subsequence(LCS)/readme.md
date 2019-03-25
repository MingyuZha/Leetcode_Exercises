# Longest Common Subsequence (LCS) 最长公共子序列问题

## Description

如果字符串一的所有字符按其在字符串中的顺序出现在另外一个字符串二中，则字符串一称之为字符串二的子串。注意，并不要求子串（字符串一）的字符必须连续出现在字符串二中。请编写一个函数，输入两个字符串，求它们的最长公共子串，并打印出最长公共子串。

**Example:**

```
输入："BDCABA"和"ABCBDAB"
输出：4
解释：字符串"BCBA"和"BDAB"都是它们的最长公共子序列，因此返回长度值4，并打印其中任意一个子序列
```

## Solution

令 X = <x1,x2,...,xm>和 Y = <y1,y2,...,yn>为两个序列，Z = <z1,z2,z3,...,zk>为X和Y的任意LCS。则

> 如果x_m = y_n，则z_k = x_m = y_n，且Z_k-1是X_m-1和Y_n-1的一个LCS
>
> 如果x_m != y_n，那么z_k != x_m，意味着Z是X_m-1和Y的一个LCS
>
> 如果x_m != y_n，那么z_k != y_n，意味着Z是X和Y_n-1的一个LCS

从上述的结论可以看出，两个序列的LCS问题包含两个序列的前缀的LCS，因此，LCS问题具有**最优子结构**性质。在设计递归算法时，不难看出递归算法具有子问题重叠的性质。

设C\[i,j]表示X_i和Y_j的最长公共子序列LCS的长度。如果i=0或j=0，即一个序列长度为0时，那么LCS的长度为0。根据LCS问题的最优子结构性质，可得如下公式：

![eq](https://latex.codecogs.com/gif.latex?C%5Bi%2Cj%5D%3D%20%5Cbegin%7Bcases%7D%200%2C%20%26%5Ctext%7Bwhen%20i%3D0%20or%20j%3D0%7D%5C%5C%20C%5Bi-1%2C%20j-1%5D&plus;1%2C%20%26%20%5Ctext%7Bwhen%20%7D%20i%2C%20j%3E0%20%5Ctext%7B%20and%20%7D%20x_i%3Dy_j%5C%5C%20MAX%28C%5Bi%2Cj-1%5D%2CC%5Bi-1%2Cj%5D%29%20%26%20%5Ctext%7Bwhen%20%7D%20i%2C%20j%3E0%20%5Ctext%7B%20and%20%7D%20x_i%20%5Cneq%20y_j%20%5Cend%7Bcases%7D)

伪代码实现(递归)：

![img](https://img-blog.csdn.net/20161219154821072?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc29fZ2VpbGk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

伪代码实现(非递归)：

![img](https://img-blog.csdn.net/20161219155011664?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc29fZ2VpbGk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

核心代码：

求解最大公共子序列长度：

```java
public static int longestCommonSubsequence(char[] arr1, char[] arr2) {
  int m = arr1.length, n = arr2.length;
  int[][] dp = new int[m+1][n+1];
  for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
      if (arr1[i-1] == arr2[j-1]) {
        dp[i][j] = dp[i-1][j-1] + 1;
      } else {
        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
      }
    }
  }
  System.out.printf("\n");
  return dp[m][n];
}
```

打印任意一个最长公共子序列：

```java
public static void printLCS(char[] arr1, int[][] dp, int i, int j) {
  if (i == 0 || j == 0) return;
  if (dp[i][j] == dp[i-1][j-1]+1) {
    printLCS(arr1, dp, i-1, j-1);
    System.out.printf("%c",arr1[i-1]);
  } else if (dp[i][j] == dp[i-1][j]) {
    printLCS(arr1, dp, i-1, j);
  } else {
    printLCS(arr1, dp, i, j-1);
  }

}
```

