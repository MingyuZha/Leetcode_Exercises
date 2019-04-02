# Leetcode 41. First Missing Positive (Hard)

## Description

Given an unsorted integer array, find the smallest missing positive integer.

**Example 1:**

```
Input: [1,2,0]
Output: 3
```

**Example 2:**

```
Input: [3,4,-1,1]
Output: 2
```

**Example 3:**

```
Input: [7,8,9,11,12]
Output: 1
```

## Solution

本题的难点在于如何使用O(N)的时间复杂度和O(1)的空间复杂度来求解该问题。

如果要是时间复杂度为O(N)，那么意味着我们在遍历数组的过程中不能有内层嵌套循环。那么我们可以在遍历的过程中，一旦发现小于数组长度n的数字，就做一步交换操作，使得该数字处于数组中的"正确"位置。

**核心代码：**

```java
public int firstMissingPositive(int[] nums) {
  int n = nums.length;
  for (int i = 0; i < n; i++) {
    if (nums[i] == i+1) continue;
    while (nums[i] > 0 && nums[i] <= n && nums[i] != i+1) {
      int temp = nums[nums[i]-1];
      nums[nums[i]-1] = nums[i];
      nums[i] = temp;
    }         

  }
  for (int i = 0; i < n; i++) {
    if (nums[i] != i+1) return i+1;
  }
  return n;
}
```

