package com.heap;

import java.util.Arrays;

/**
 * @Author: hwj
 * @Date: 2021/11/17
 * @Description: 堆结构
 */


public class Heap {

    public int[] elements;
    public int size;
    public int capacity;

    public Heap() {
        this.capacity = 16;
        this.size = 0;
        this.elements = new int[capacity];
    }

    public Heap(int capacity) {
        elements = new int[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public Heap(int[] elements) {
        this.elements = elements;
        this.size = elements.length;
        this.capacity = elements.length;
        generateHeap();
    }

    public void generateHeap(){
        for (int i = size - 1; i >= 0 ; i--) {
            heapify2(i);
        }
    }

    public boolean isFull(){
        return size >= capacity;
    }

    public void push(int value){
        if(isFull()) elements = Arrays.copyOf(elements, elements.length * 2);
        elements[size] = value;
        heapInsert(size++);
    }

    public void swap(int index1, int index2){
        int temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }

    // 向上冒泡
    public void heapInsert(int index){
        if(index == 0) return;
        int parent_idx = (index - 1) / 2;
        while(elements[index] > elements[parent_idx]){
            swap(index, parent_idx);
            index = parent_idx;
            if(index == 0) break;
            parent_idx = (index - 1) / 2;
        }
    }

    // 向上冒泡
    public void heapInsert2(int index){
        while(elements[index] > elements[(index - 1) / 2]){
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 向下冒泡
    public void heapify(int index, int heapSize){
        while (true) {
            int left_idx = index * 2 + 1;
            int right_idx = index * 2 + 2;
            if (left_idx >= heapSize && right_idx >= heapSize) break;
            int largest = right_idx < heapSize && elements[left_idx] > elements[right_idx] ? left_idx : right_idx;
            if (elements[largest] > elements[index]) swap(largest, index);
            else break;
            index = largest;
        }
    }

    // 向下冒泡
    public void heapify2(int index){
        int left = 2 * index + 1;
        while(left < size){// 有左孩子
            int largest = left + 1 < size && elements[left] < elements[left + 1] ? left + 1 : left; // 找出左右孩子较大的一个的下标
            if(elements[largest] > elements[index]) {
                swap(largest, index); // 如果子节点较大，子节点和父节点交换
                index = largest; // 更新index为父节点
                left = 2 * index + 1; // 更新子节点
            }
            else break; // 没有交换就退出
        }
    }

    public boolean isEmpty(){
        return size <= 0;
    }


    public int pop(){
        int res = elements[0];
        swap(0, --size);
        heapify2(0);
        return res;
    }

    public static void main(String[] args) {
        Heap heap = new Heap(new int[]{7,8,9,6,45,2,2,3,4,5,5});
        heap.push(100);
        while (!heap.isEmpty()){
            System.out.println(heap.pop());
        }
    }


}




