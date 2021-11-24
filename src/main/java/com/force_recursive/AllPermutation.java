package com.force_recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Author: hwj
 * @Date: 2021/11/21
 * @Description:
 */
public class AllPermutation {

    public static void allPermutation(char[] chars, int i){
        if(i == chars.length){
            System.out.println(String.valueOf(chars));
            return;
        }
        for (int j = i; j < chars.length; j++) {
            swap(chars, i, j);
            allPermutation(chars, i + 1);
            swap(chars, i, j);
        }

    }

    public static void swap(char[] arr, int index1, int index2){
        char temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void main(String[] args) {
        allPermutation("123".toCharArray(), 0);
    }
}
