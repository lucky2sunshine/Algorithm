package com.list;

/**
 * @Author: hwj
 * @Date: 2021/11/19
 * @Description:
 */
class Node{
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
        this.next = null;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}

public class LinkedList {
    static Node head = new Node(0);

    public static Node generateLinkedList(int[] arr){
        Node cur = head;
        for (int i = 0; i < arr.length; i++) {
            cur.next = new Node(arr[i]);
            cur = cur.next;
            head.val++;
        }
        return head;
    }

    public static boolean existCircle(Node head){
        if(head == null) return false;
        Node slow = head;
        Node fast = head;
        while(slow.next != null && fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }

    public static Node findCircleEntrance(Node head){
        if(!existCircle(head)) return null;
        Node slow = head;
        Node fast = head;
        while(slow.next != null && fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }
        fast = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        generateLinkedList(new int[]{1,2,3,4,5,6,7});
        System.out.println(findCircleEntrance(head));
    }

}
