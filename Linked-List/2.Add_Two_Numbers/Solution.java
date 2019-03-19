import java.util.*;
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        dummy.next = head;
        while (l1 != null || l2 != null) {
            int sum = (l1 == null ? 0 : l1.val) + (l2==null ? 0 : l2.val) + carry;
            head.next = new ListNode(sum % 10);
            carry = (sum) / 10;
            if (l2 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            head = head.next;
        }
        if (carry > 0) {
            head.next = new ListNode(carry);
        }
        return dummy.next;
    }
    public static void main(String[] args) {
        Solution test = new Solution();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode head = test.addTwoNumbers(l1, l2);
        while (head != null) {
            System.out.printf("%d -> ", head.val);
            head = head.next;
        }
    }
}