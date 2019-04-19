# Leetcode 230. Kth Smallest Element in a BST (剑指offer：二叉搜索树的第k个结点)

## Description

Given a binary search tree, write a function `kthSmallest` to find the **k**th smallest element in it.

**Note:** 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

**Example 1:**

```
Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
```

**Example 2:**

```
Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
```

## Solution

```java
/**
* 使用中序遍历来遍历二叉搜索树得到的结果是一个增序数列，通过记录遍历的结点数就可以轻松的得到第k小的节点

* 使用递归写法

*/
public class Solution {
    private TreeNode ans;
    private int k;
    private void inorder(TreeNode node) {
        if (node == null || k == 0) return;
        inorder(node.left);
        k--;
        if (k == 0) ans = node;
        inorder(node.right);
    }
    TreeNode KthNode(TreeNode pRoot, int k) {
        this.k = k;
        inorder(pRoot);
        return ans;
    }
}
/**

* 使用非递归写法

*/
public class Solution {
    TreeNode KthNode(TreeNode pRoot, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (pRoot != null) {
            stack.push(pRoot);
            pRoot = pRoot.left;
        } 
        while (!stack.empty()) {
            TreeNode cur = stack.pop();
            k--;
            if (k == 0) return cur;
            TreeNode right = cur.right;
            while (right != null) {
                stack.push(right);
                right = right.left;
            }
        }
        return null;
    }
}
```

