# Leetcode 110. Balanced Binary Tree

## Description

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

> a binary tree in which the depth of the two subtrees of *every* node never differ by more than 1.

**Example 1:**

Given the following tree `[3,9,20,null,null,15,7]`:

```
    3
   / \
  9  20
    /  \
   15   7
```

Return true.

**Example 2:**

Given the following tree `[1,2,2,3,3,null,null,4,4]`:

```
       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
```

Return false.

## Solution

```java
/**
		自顶而下，计算左右子树的depth，判断是否满足balanced条件，然后递归检查左右子树是否满足balanced条件
		
		该解法存在冗余，许多子树的depth被重复计算
		
		该解法的时间复杂度为O(N^2)
*/
public class Solution {
    public boolean IsBalanced_Solution(TreeNode root) {
      if (root == null) return true;
      int left = TreeDepth(root.left);
      int right = TreeDepth(root.right);
      return Math.abs(left-right) <= 1 && IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }
    private int TreeDepth(TreeNode node) {
      if (node == null) return 0;
      int left = TreeDepth(node.left);
      int right = TreeDepth(node.right);
      return Math.max(left, right)+1;
    }
}
/**
		自底而上，在计算左右子树depth的同时判断是否满足balanced binary tree条件，可以达到O(N)的时间复杂度
*/
public class Solution {
    public boolean IsBalanced_Solution(TreeNode root) {
        return helper(root) != -1;
    }
    private int helper(TreeNode node) {
        if (node == null) return 0;
        int left = helper(node.left);
        if (left == -1) return -1;
        int right = helper(node.right);
        if (right == -1) return -1;
        if (Math.abs(left-right) > 1) return -1;
        return Math.max(left, right)+1;
    }
}
```

