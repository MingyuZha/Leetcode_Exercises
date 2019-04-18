# Leetcode 142. Linked List Cycle II (链表中环的入口节点)

## Description

给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。

**Example 1:**

```
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

## Solution

```java
/**
* 解决这个问题的第一步是判断链表中有没有环：快慢指针法
* 第二步是如何找到环的入口，还是可以用两个指针来解决这个问题：
	1. 定义两个指针P1，P2指向链表的头节点
	2. 如果链表中的环有n个节点，则指针P1先在链表上向前移动n步
	3. 两个指针以相同的速度向前移动，当第二个指针指向环的入口时，第一个指针已经围绕着环走了一圈，又回到了入口节点
👆可以这么理解，因为P1始终领先P2 n个节点，即绕环一圈的长度，因此，当P2到达环入口时，P1等于套了P2一圈，两个应该都处在环的入口处，这样一来，两个指针接下来不管怎么向前移动，P1永远都领先P2一圈。
* 那么，问题来了，如何求得链表中的环有多少个节点？
	- 解法是利用我们在第一步中得到的快慢指针，当两个指针相遇之后，另其中一个指针继续向前移动(步长为1)，那么当这个指针再次与另一个指针相遇时，即回到出发地，走过的节点数就是环中的节点数。
*/
public class Solution {
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode slow = pHead, fast = pHead;
        do {
            slow = slow.next;
            if (fast.next != null) fast = fast.next.next;
            else return null;
        } while (slow != null && fast != null && slow != fast);
        ListNode head = pHead;
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }
        return slow;
    }
}
```

