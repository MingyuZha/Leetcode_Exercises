# Leetcode 236. Lowest Common Ancestor of a Binary Tree

## Description

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the [definition of LCA on Wikipedia](https://en.wikipedia.org/wiki/Lowest_common_ancestor): “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow **a node to be a descendant of itself**).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

![img](https://assets.leetcode.com/uploads/2018/12/14/binarytree.png)

**Example 1:**

```python
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
```

**Example 2:**

```python
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
```

## Solution

### Recursion Method

通过深度优先的方式遍历二叉树，当找到p和q中的任意一个节点时返回```True```。p和q的最近公共祖先其实就是从该节点出发的左右子树返回值都为True，即p和q两个节点分别存在于该节点的左右子树中。当然，还有一种特殊情况，就是该子树的根节点就是p或q中的任意一个节点，为了把这种特殊情况也考虑进去，我们额外设置一个变量```mid```来记录根节点就是我们所要找的目标节点的情况。

核心代码：

```java
private static boolean dfs(TreeNode n, TreeNode p, TreeNode q) {
  if (n == null) return false;
  int left = dfs(n.left, p, q) ? 1 : 0; //左子树中包含p或q
  int right = dfs(n.right, p, q) ? 1 : 0; //右子树中包含p或q
  int mid = (n == p || n == q) ? 1 : 0;  //该节点本身就是p或q
  if (left + right + mid >= 2) ans = n;  //只有上述三种情况中的任意两种满足，即可判断该节点就是最近公共祖先
  return (left+right+mid) > 0; //只要找到p或q节点，就返回true
}
```

### Iterative Method

该方法的思想是如果我们在深度优先遍历二叉树时，会记录每个节点的父节点，那么当找到p和

q两个节点之后，我们就有了从p和q各自回溯的路径。我们将其中一个节点（假设是p）的回溯路径上的所有节点（即它的所有父节点）push进一个Set中，然后开始从另一个目标节点(对应前面，就是q)出发，开始回溯，过程中如果发现节点在之前保存的set中出现，就停止回溯过程，返回这个节点

核心代码：

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
  Map<TreeNode, TreeNode> parents = new HashMap<>(); //用来存储p和q节点的父节点指针
  Stack<TreeNode> stack = new Stack<>(); 
  stack.push(root);
  parents.put(root, null);
  while (!parents.containsKey(p) || !parents.containsKey(q)) { //当我们通过dfs找到了p和q两个节点之后就退出循环
    TreeNode cur = stack.pop();
    if (cur.left != null) {
      stack.push(cur.left);
      parents.put(cur.left, cur);
    }
    if (cur.right != null) {
      stack.push(cur.right);
      parents.put(cur.right, cur);
    }
  }
  Set<TreeNode> set = new HashSet<>();
  while (p != null) {
    set.add(p);
    p = parents.get(p);
  }
  while (!set.contains(q)) {
    q = parents.get(q);
  }
  return q;
}
```



