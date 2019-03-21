# Leetcode 45. Jump Game II (Hard)

## Description

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

**Example:**

```python
Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
```

## Solution

该题的求解思路为贪心算法。

我们在遍历数组的过程中需要维护两个变量：

* cur_max_index：用来记录当前所能到达的最远index，即在不增加跳跃次数的条件下，我们可以到达的最远的位置。
* max_index：从当前位置（或之前所能到达的位置）起，所能够到达的最远位置。这个变量和cur_max_index的区别在于要到达max_index，我们必须再进行**一次**跳跃。

核心代码：

```java
public int jump(int[] nums) {
  int cur_max_index = nums[0];
  int max_index = nums[0];
  int steps = 1;
  for (int i = 0; i < nums.length; i++) {
    if (i > cur_max_index) {
      cur_max_index = max_index;
      steps++;
    }
    max_index = Math.max(max_index, nums[i]+i);
  }
  return steps;
}
```

