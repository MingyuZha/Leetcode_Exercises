# 剑指offer：二叉树的下一个节点

## Description

给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。

## Solution

```java
/**

分3种情况来考虑：
	1. 如果一个节点有右子树：那么它的下一个节点就是它的右子树中的最左子节点
	2. 如果一个节点没有右子树，且它是它父节点的左子节点，那么它的下一个节点就是它的父节点
	3. 如果一个节点没有右子树，且是它父节点的右子节点，我们可以沿着指向父节点的指针一直向上遍历，直到找到一个是它父节点的左子节点的节点。如果这样的节点存在，那么这个节点的父节点就是我们要找的下一个节点
	
*/
public class Solution {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode.right != null) {
            TreeLinkNode right_tree = pNode.right;
            while (right_tree.left != null) {
                right_tree = right_tree.left;
            }
            return right_tree;
        } else {
            TreeLinkNode parent = pNode.next;
            if (parent == null) return null;
            else if (pNode == parent.left) return parent;
            else {
                while (parent.next != null && parent.next.left != parent) {
                    parent = parent.next;
                }
                return parent.next;
            }
        }
    }
}
```

