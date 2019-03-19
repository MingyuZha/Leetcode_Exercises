# Leetcode 486. Predict the Winner (Medium)

## Description

Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

**Example 1:**

```python
Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False.
```

**Example 2:**

```python
Input: [1, 5, 233, 7]
Output: True
Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
```

## Solution

### Recursion Solution

在这个游戏中一共有两个玩家，轮到每个玩家行动时，他都希望最大化自己的收益，因此递归的思路可以设计为玩家在本轮中可以获得的最大收益为：在本轮中pick的element值**减去**下一轮对手的最大收益。这样我们最终只需要判断递归函数的返回值是否**大于等于**零即可。

![img](https://leetcode.com/articles/Figures/486/486_Predict_the_winner_new.PNG)

> 时间复杂度：O(2^N)，因为在每一次选择时候玩家都有两种可执行的选择，即选择队首元素或者队尾元素，这样一来，总共的可能的排列组合数为2^N
>
> 空间复杂度：O(N)，因为递归树的深度最多为N

可做的改进是使用**记忆数组**来减少递归次数，我们使用一个二维的memo数组，来记录起始和结束地址为begin和end时，选手可以取得的最大收益。

核心代码如下：

```java
private int helper(int[] nums, int begin, int end) {
  if (begin == end) {
    dp[begin][end] = nums[begin];
    return nums[begin];
  }
  if (dp[begin][end] != null) return dp[begin][end];
  int left = nums[begin] - helper(nums, begin+1, end);
  int right = nums[end] - helper(nums, begin, end-1);
  dp[begin][end] = Math.max(left, right);
  return dp[begin][end];
}
```

> 时间复杂度：O(N^2)，因为memo数组中每个位置的元素只会被填充一次，所以时间复杂度是O(N^2)
>
> 空间复杂度：O(N^2)

### Dynamic Programming Solution

为了将原问题切割成若干个子问题求解，我们发现，如果给定原数组的一个subarray: nums[x: y]，那么玩家1能得到的最大和减去玩家2所能得到的最大和仅仅取决于num[x:y]中的数组元素。DP思想即可建立与此：dp\[begin][end]代表子问题的解。

核心代码如下：

```java
public boolean PredictTheWinner2(int[] nums) {
  int[][] dp = new int[nums.length][nums.length];
  for (int i = nums.length-1; i >= 0; i--) {
    for (int j = i+1; j < nums.length; j++) {
      if (j == i + 1) dp[i][j] = Math.max(nums[i]-nums[j], nums[j]-nums[i]);
      else dp[i][j] = Math.max(nums[i]-dp[i+1][j], nums[j]-dp[i][j-1]);
    }
  }
  return dp[0][nums.length-1] >= 0;
}
```

