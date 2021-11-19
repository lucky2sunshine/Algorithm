package com.graph;

import java.util.*;

/**
 * @Author: hwj
 * @Date: 2021/11/18
 * @Description: 图结构
 */
class Node{
    public int val;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node() {
        this.val = 0;
        in = 0;
        out = 0;
        nexts = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
    }

    public Node(int val) {
        this.val = val;
        in = 0;
        out = 0;
        nexts = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
    }

    public Node addIn(){
        this.in++;
        return this;
    }

    public Node addOut(){
        this.out++;
        return this;
    }

    public Node addEdge(Edge edge){
        this.edges.add(edge);
        return this;
    }

    public Node addNode(Node node){
        this.nexts.add(node);
        return this;
    }
}

class Edge{
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    public Edge(Node from, Node to) {
        this.weight = 0;
        this.from = from;
        this.to = to;
    }
}

public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<Integer, Node>();
        this.edges = new HashSet<Edge>();
    }

    public void createGraph(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            // 获取节点
            int from = arr[i][0];
            int to = arr[i][1];
            int weight = arr[i].length > 2 ? arr[i][2] : 0;
            // 创建节点并加入节点
            if(!nodes.containsKey(from)) nodes.put(from, new Node(from));
            if(!nodes.containsKey(to)) nodes.put(to, new Node(to));
            // 创建边并加入边
            Edge edge = new Edge(weight, nodes.get(from), nodes.get(to));
            edges.add(edge);
            // 更新节点信息
            nodes.get(from).addEdge(edge).addNode(nodes.get(to));
            nodes.get(from).addOut();
            nodes.get(to).addIn();
        }
    }

    // 深度优先遍历
    public void DFS(Node node){
        Stack<Node> stack = new Stack<Node>();
        HashSet<Node> selectedNodes = new HashSet<Node>();
        stack.push(node);
        selectedNodes.add(node);
        System.out.println(node.val);
        while(!stack.isEmpty()){
            Node cur = stack.lastElement();
            boolean finish = true;
            for (Node next : cur.nexts) {
                if(!selectedNodes.contains(next)){
                    stack.push(next);
                    selectedNodes.add(next);
                    finish = false;
                    System.out.println(next.val);
                    break;
                }
            }
            if(finish) stack.pop();
        }
    }

    // 深度优先遍历
    public void DFS2(Node node){
        Stack<Node> stack = new Stack<Node>();
        HashSet<Node> selectedNodes = new HashSet<Node>();
        stack.push(node);
        selectedNodes.add(node);
        System.out.println(node.val);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if(!selectedNodes.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    selectedNodes.add(next);
                    System.out.println(next.val);
                    break;
                }
            }
        }
    }

    // 广度优先遍历
    public void BFS(Node node){
        Queue<Node> queue = new LinkedList<Node>();
        HashSet<Node> selectedNodes = new HashSet<Node>();
        queue.add(node);
        selectedNodes.add(node);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.val);
            for (Node next : cur.nexts) {
                if(!selectedNodes.contains(next)){
                    queue.add(next);
                    selectedNodes.add(next);
                }
            }
        }
    }

    public void topologicalSort(){
        HashMap<Integer, Integer> indegree = new HashMap<>();
        nodes.forEach((key, value) -> {
            indegree.put(key, value.in);
        });
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> selected = new HashSet<>();
        getInDegreeEqualszero(indegree, queue, selected);
        while(!queue.isEmpty()){
            Integer cur = queue.poll();
            System.out.println(cur);
            for (Node next : nodes.get(cur).nexts) {
                indegree.put(next.val, indegree.get(next.val) - 1);
            }
            getInDegreeEqualszero(indegree, queue, selected);
        }
    }

    private void getInDegreeEqualszero(HashMap<Integer, Integer> indegree, Queue<Integer> queue, HashSet<Integer> selected) {
        indegree.forEach((key, value) -> {
            if(value == 0 && !selected.contains(key)) {
                queue.add(key);
                selected.add(key);
            }
        });
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,3},{1,2},  {2,4}, {3,2}, {4,5}};
        Graph graph = new Graph();
        graph.createGraph(arr);
        graph.topologicalSort();

    }
}
