//Solution #1: Recursion
class Solution {
    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }
    private static TreeNode ans;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }
    private static boolean dfs(TreeNode n, TreeNode p, TreeNode q) {
        if (n == null) return false;
        int left = dfs(n.left, p, q) ? 1 : 0;
        int right = dfs(n.right, p, q) ? 1 : 0;
        int mid = (n == p || n == q) ? 1 : 0;
        if (left + right + mid >= 2) ans = n;
        return (left+right+mid) > 0;
    }
}

//Solution #2: Iterative
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        parents.put(root, null);
        while (!parents.containsKey(p) || !parents.containsKey(q)) {
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
}