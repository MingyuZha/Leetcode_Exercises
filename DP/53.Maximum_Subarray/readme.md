# Leetcode 53. Maximum Subarray

## Description

Given an integer array `nums`, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

**Example:**

```python
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
```

## Solution

这是一道典型的用DP思想来求解的题目，首先初始化dp数组，dp数组中每一个元素代表的含义是：若以当前元素作为maximum subarray的最后一个元素，得到的最大值。那么当我们遍历到下一个节点的时候，需要求解以下一个节点为maximum subarray的最后一个元素时，只需要比较两个值：

1. 该元素本身的值，即nums[i]
2. 该元素本身的值加上以前一个元素为终止点的最大值，即dp[i-1]+nums[i]

我们在遍历的同时，维护一个全局最大值，即我们想要的最终答案。

核心代码：

```java
public static int maxSubArray(int[] nums) {
  int[] dp = new int[nums.length];
  int ans = Integer.MIN_VALUE;
  dp[0] = nums[0];
  for (int i = 1; i < nums.length; i++) {
    dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
    ans = Math.max(ans, dp[i]);
  }
  return ans;
}
```

