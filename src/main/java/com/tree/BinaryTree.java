package com.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: hwj
 * @Date: 2021/11/17
 * @Description:
 */

class Node{
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
        left = null;
        right = null;
    }

    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BinaryTree {
    static Node root = null;
    
    public static void generateBinaryTree(int[] arr){
        Queue<Node> queue = new LinkedList<Node>();
        for (int i = 0; i < arr.length;) {
            if(root == null){
                root = new Node(arr[i++]);
                queue.add(root);
            }else{
                Node cur = queue.poll();
                assert cur != null;
                if(i < arr.length) {
                    cur.left = new Node(arr[i++]);
                    queue.add(cur.left);
                }
                if(i < arr.length) {
                    cur.right = new Node(arr[i++]);
                    queue.add(cur.right);
                }
            }
        }
    }

    // 先序遍历
    public static void preOrderTraversal(Node root){
        if(root == null) return;
        System.out.println(root.val);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    // 中序遍历
    public static void inOrderTraversal(Node root){
        if(root == null) return;
        inOrderTraversal(root.left);
        System.out.println(root.val);
        inOrderTraversal(root.right);
    }

    // 后序遍历
    public static void postOrderTraversal(Node root){
        if(root == null) return;
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.println(root.val);
    }

    // 先序遍历
    public static void preOrderTraversal2(Node root){
        if(root == null) return;
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.val);
            if(cur.right != null) stack.push(cur.right);
            if(cur.left != null) stack.push(cur.left);
        }
    }

    // 中序遍历
    public static void inOrderTraversal2(Node root){
        if(root == null) return;
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while(root.left != null){
            stack.push(root.left);
            root = root.left;
        }
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.val);
            if(cur.right != null){
                cur = cur.right;
                stack.push(cur);
                while(cur.left != null){
                    stack.push(cur.left);
                    cur = cur.left;
                }
            }
        }
    }

    // 后序遍历
    public static void postOrderTraversal2(Node root){
        if(root == null) return;
        Stack<Node> stack = new Stack<Node>();
        Stack<Node> collect = new Stack<Node>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            collect.push(cur);
            if(cur.left != null) stack.push(cur.left);
            if(cur.right != null) stack.push(cur.right);
        }
        while(!collect.isEmpty()){
            System.out.println(collect.pop().val);
        }
    }

    public static void levelTraversal(Node root){
        if(root == null) return;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.val);
            if(cur.left != null) queue.add(cur.left);
            if(cur.right != null) queue.add(cur.right);
        }
    }

    public static int getMaxWidth(Node root){
        if(root == null) return 0;
        Node curend = root;
        Node nextend = null;
        int curLevelNodes = 0;
        int max = Integer.MIN_VALUE;

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            curLevelNodes++;
            if(cur.left != null) {
                queue.add(cur.left);
                nextend = cur.left;
            }
            if(cur.right != null) {
                queue.add(cur.right);
                nextend = cur.right;
            }
            if(curend == cur){
                max = Math.max(max, curLevelNodes);
                curend = nextend;
                curLevelNodes = 0;
            }
        }
        return max;
    }

    public static int getMaxDepth(Node root){
        if(root == null) return 0;
        int left_depth = getMaxDepth(root.left);
        int right_depth = getMaxDepth(root.right);
        return Math.max(left_depth, right_depth) + 1;
    }

    public static int getMinDepth(Node root){
        if(root == null) return 0;
        int left_depth = getMinDepth(root.left);
        int right_depth = getMinDepth(root.right);
        return (left_depth == 0 || right_depth == 0) ? left_depth + right_depth + 1 : Math.min(left_depth, right_depth) + 1;
    }

    public static void main(String[] args) {
        generateBinaryTree(new int[]{1,2,3,4,5,6,7});
        System.out.println(getMinDepth(root));
    }

}
