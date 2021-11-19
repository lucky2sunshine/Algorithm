package com.sort;

import com.sun.tools.classfile.ConstantPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: hwj
 * @Date: 2021/11/17
 * @Description: 堆结构
 */


public class Heap {

    public static void generateHeap(int[] arr){
        for (int i = arr.length - 1; i >= 0 ; i--) {
            heapify2(arr, i, arr.length);
        }
    }


    public static void swap(int[] arr, int index1, int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }


    public static void heapInsert(int[] arr, int index){
        if(index == 0) return;
        int parent_idx = (index - 1) / 2;
        while(arr[index] > arr[parent_idx]){
            swap(arr, index, parent_idx);
            index = parent_idx;
            if(index == 0) break;
            parent_idx = (index - 1) / 2;
        }
    }

    public static void heapInsert2(int[] arr, int index){
        while(arr[index] > arr[(index - 1) / 2]){
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize){
        while (true) {
            int left_idx = index * 2 + 1;
            int right_idx = index * 2 + 2;
            if (left_idx >= heapSize && right_idx >= heapSize) break;
            int largest = right_idx < heapSize && arr[left_idx] > arr[right_idx] ? left_idx : right_idx;
            if (arr[largest] > arr[index]) swap(arr, largest, index);
            else break;
            index = largest;
        }
    }

    public static void heapify2(int[] arr, int index, int heapSize){
        int left = 2 * index + 1;
        while(left < heapSize){// 有左孩子
            int largest = left + 1 < heapSize && arr[left] > arr[left + 1] ? left : left + 1; // 找出左右孩子较大的一个的下标
            if(arr[largest] > arr[index]) {
                swap(arr, largest, index); // 如果子节点较大，子节点和父节点交换
                index = largest; // 更新index为父节点
                left = 2 * index + 1; // 更新子节点
            }
            else break; // 没有交换就退出
        }
    }

    public static int pop(int[] arr, int heapSize){
        int res = arr[0];
        swap(arr, 0, --heapSize);
        heapify2(arr, 0, heapSize);
        return res;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        int[] arr = new int[]{8, 10, 11,5,5,7,12};
        generateHeap(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }


}




