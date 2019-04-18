# Leetcode 104. Maximum Depth of Binary Tree

## Description

Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

**Note:** A leaf is a node with no children.

**Example:**

Given binary tree `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

return its depth = 3.

## Solution

```java
/**
		递归法
*/
public int TreeDepth(TreeNode root) {
  if (root == null) return 0;
  int left_depth = TreeDepth(root.left);
  int right_depth = TreeDepth(root.right);
  return Math.max(left_depth, right_depth)+1;
}

/**
		非递归法
*/
public int TreeDepth(TreeNode root) {
  if (root == null) return 0;
  Queue<TreeNode> queue = new LinkedList<>();
  queue.add(root);
  int depth = 0;
  while (!queue.isEmpty()) {
    int q_size = queue.size();
    for (int i = 0; i < q_size; i++) {
      TreeNode cur = queue.poll();
      if (cur.left != null) queue.add(cur.left);
      if (cur.right != null) queue.add(cur.right);
    }
    depth++;
  }
  return depth;
}
```

