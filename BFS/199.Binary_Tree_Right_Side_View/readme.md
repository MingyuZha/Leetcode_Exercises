# Leetcode 199. Binary Tree Right Side View

## Description

Given a binary tree, imagine yourself standing on the *right* side of it, return the values of the nodes you can see ordered from top to bottom.

**Example:**

```
Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
```

## Solution

```java
/**
    BFS Solution
*/
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
                if (i == n-1) ans.add(cur.val);
            }
        }
        return ans;
    }
}
```

```java
/**
    DFS Solution
*/
class Solution {
    private int cur_level;
    private List<Integer> ans = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        ans.clear();
        cur_level = -1;
        dfs(root, 0);
        return ans;
    }
    private void dfs(TreeNode node, int level) {
        if (node == null) return;
        if (level > cur_level) {
            cur_level = level;
            ans.add(node.val);
        }
        dfs(node.right, level+1);
        dfs(node.left, level+1);
    }
}
```

