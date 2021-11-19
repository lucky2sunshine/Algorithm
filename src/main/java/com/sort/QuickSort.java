package com.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static int split(Integer[] arr, int left, int right){
        int low = left;
        int current = left + 1;
        int sign = arr[left];
        for (int i = current; i < right; i++) {
            if(arr[current] > sign) current++;
            else swap(arr, ++low, current++);
        }
        swap(arr, left, low);
        return low;
    }

    private static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static Integer[] quick_sort(Integer arr[], int left, int right){
        if(right - left == 0 || right - left == 1) return arr;
        int middle = split(arr, left, right);
        quick_sort(arr, left, middle);
        quick_sort(arr, middle + 1, right);
        return arr;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            arr.add(new Random().nextInt(100));
        }

        System.out.println(Arrays.toString(quick_sort(arr.toArray(new Integer[0]), 0, arr.size())));
    }
}
