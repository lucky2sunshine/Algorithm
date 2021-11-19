package com.sort;

import java.util.Arrays;

public class MergeSort {
    public static int[] merge(int[] arr1, int[] arr2){
        int left = 0;
        int right = 0;
        int index = 0;
        int[] arr = new int[arr1.length + arr2.length];
        while(left < arr1.length && right < arr2.length){
            if(arr1[left] > arr2[right]) arr[index++] = arr2[right++];
            else arr[index++] = arr1[left++];
        }
        while(left < arr1.length) arr[index++] = arr1[left++];
        while(right < arr2.length) arr[index++] = arr2[right++];
        return arr;
    }

    public static int[] merge_sort(int[] arr, int left, int right){
        if(right - left == 0) return new int[]{};
        if(right - left == 1) return new int[]{arr[left]};
        int middle = (right + left) / 2;
        int[] leftarr = merge_sort(arr, left, middle);
        int[] rightarr = merge_sort(arr, middle, right);
        return merge(leftarr, rightarr);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,5,7,9,3,21,2,4,7,8,42};
        System.out.println(Arrays.toString(merge_sort(arr, 0, arr.length)));
    }
}
