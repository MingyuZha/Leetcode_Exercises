# Leetcode 11. Container With Most Water

## Description

Given *n* non-negative integers *a1*, *a2*, ..., *an* , where each represents a point at coordinate (*i*, *ai*). *n* vertical lines are drawn such that the two endpoints of line *i* is at (*i*, *ai*) and (*i*, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

**Note:** You may not slant the container and *n* is at least 2.

![img](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/17/question_11.jpg)

The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

**Example:**

```python
Input: [1,8,6,2,5,4,8,3,7]
Output: 49
```

## Solution 

要求解这个问题，首先我们应该认清楚两个事实：

1. "木桶"的高度取决较短的那条线
2. 如果两条线之间的跨度越大，那么就越有可能容纳更多的水，当然，只是可能，因为只有当"木桶"的高度相同的情况下，跨度更大(宽度越大)的"木桶"才可以容纳更多水

这时候，我们就只需要维护两个指针，一个指针从数组的起始位置出发，另一个指针从数组的末尾出发。同时，维护一个变量```max_area```，用以记录当前我们所能存储的最大水量。在判断是更新第一个指针的位置还是第二个指针的位置时，我们只需要比较两个指针所对应的数组元素的大小，更新小的那个即可。理由是：如果更新代表元素更大的那个指针(即指针指向的line更长)，那么即使下一步得到的line比之前更长，由于**短板**理论，我们依旧无法获得更大的存储容量，所以只有当"短板"变得更长，我们才可能获得更大的存储量。

核心代码：

```java
public static int maxArea(int[] height) {
  int left = 0, right = height.length-1;
  int max_area = Integer.MIN_VALUE;
  while (left < right) {
    max_area = Math.max(max_area, Math.min(height[left], height[right])*(right-left));
    if (height[left] < height[right]) left++;
    else right--;
  }
  return max_area;
}
```

