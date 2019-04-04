# Leetcode 904. Fruit Into Baskets

## Description

In a row of trees, the `i`-th tree produces fruit with type `tree[i]`.

You **start at any tree of your choice**, then repeatedly perform the following steps:

1. Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
2. Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.

Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.

What is the total amount of fruit you can collect with this procedure?

**Example 1:**

```
Input: [1,2,1]
Output: 3
Explanation: We can collect [1,2,1].
```

**Example 2:**

```
Input: [0,1,2,2]
Output: 3
Explanation: We can collect [1,2,2].
If we started at the first tree, we would only collect [0, 1].
```

**Example 3:**

```
Input: [1,2,3,2,2]
Output: 4
Explanation: We can collect [2,3,2,2].
If we started at the first tree, we would only collect [1, 2].
```

**Example 4:**

```
Input: [3,3,3,1,2,1,1,2,3,3,4]
Output: 5
Explanation: We can collect [1,2,1,1,2].
If we started at the first tree or the eighth tree, we would only collect 4 fruits.
```

## Solution 

**我的解法：**

一共使用到3个指针：

```ptr1```：代表了subarray的起点

```ptr2```：代表了subarray的终点，也代表遍历到了数组中的哪一个点

```ptr3```：代表如果ptr1要update的话，更新的位置，可以理解为```tree[ptr3:ptr2]```内的元素都是相同的。例如：```tree```=[1,1,1,2,2,2,3,3]，若```ptr2```=5，则```ptr3```就等于3，这样一来，当```ptr2```更新为6时，我们就可以将```ptr1```更新为3，这样一来就可以使得更新后，```tree[ptr1:ptr2]```为满足条件的subarray

```x```：代表了当前果篮中的第一类水果

```y```：代表了当前果篮中的第二类水果

```ans```：代表最长的subarray长度

```java
public int totalFruit(int[] tree) {
  int ptr1 = 0, ptr2 = 0, ptr3 = 0, x = -1, y = -1;
  int ans = Integer.MIN_VALUE;
  for (; ptr2 < tree.length; ptr2++) {
    if (x == -1) x = tree[ptr2]; //当果篮中一样水果都没有时，更新x
    else if (y == -1 && tree[ptr2] != x) y = tree[ptr2]; //当果篮中只有一样水果时，且当前水果种类不同于果篮中水果种类，更新y
    else if (x != -1 && y != -1) {
      if (tree[ptr2] != x && tree[ptr2] != y) { //当果篮中已经有两种类型的水果，且当前的水果类型不同于果篮中的两种水果类型
        int temp = ptr2 - ptr1; //计算果篮中现有水果的数量
        ans = Math.max(ans, temp); //更新ans
        ptr1 = ptr3;  //更新ptr1
        x = tree[ptr1]; //更新果篮中水果种类
        y = tree[ptr2];
      }
    }
    if (tree[ptr2] != tree[ptr3]) ptr3 = ptr2; //因为要保证ptr3到ptr2之间的水果都是同一种类的，因此如果不同，就要更新ptr3
  }
  ans = Math.max(ans, ptr2-ptr1);
  return ans;
}
```

**论坛里别人的解法:**

这个问题其实可以转换为：

> Find out the longest length of subarrays with at most 2 different numbers - 求数组中只包含两类元素的最长subarray

我们需要设置三个不同的变量：

```a```, ```b```：last two different types of fruit that we met

```c```：the type of fruit we currently meet

在loop through all fruit in ```tree```的过程中，我们可能遇到三种不同的情况：

**第一类情况：**```c == b```

fruit ```c```已经在果篮中了，并且它和last type of fruit相同

```cur += 1```

```count_b += 1```

**第二类情况：**```c == a```

fruit ```c```已经在果篮中了，但是和last type of fruit不同

```cur += 1```

```count_b = 1```

```a = b, b = c```

**第三类情况：**```c != b && c != a```

fruit ```c```并不在果篮中，且果篮中已经存在有两种不同类型的水果了

```cur = count_b + 1```

```count_b = 1```

```a = b, b = c```

```java
public int totalFruit(int[] tree) {
  int a = 0, b = 0, count_b = 0, cur = 0;
  int ret = 0;
  for (int c : tree) {
    if (c == b){
      cur++;
      count_b++;
    }
    else if (c == a){
      cur++;
      count_b = 1;
      a = b; b = c;
    }
    else if (c != a && c != b) {
      cur = count_b+1;
      a = b; b = c;
      count_b = 1;
    }
    ret = Math.max(ret, cur);
  }
  return ret;
}
```

