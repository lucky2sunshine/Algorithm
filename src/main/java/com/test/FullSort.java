package com.test;

import com.sun.tools.corba.se.idl.InterfaceGen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author: hwj
 * @Date: 2022/2/17
 * @Description:
 */
public class FullSort {


    public static void swap(int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static LinkedList<Integer> ans = new LinkedList<>();

    public static void fullSort(int start){ // 前面顺序定好了，后面随便
        // base line
        if(start == nums.length){
            System.out.println(Arrays.toString(ans.toArray()));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            ans.add(nums[i]);
            swap(start, i);
            fullSort(start + 1);
            swap(i, start);
            ans.pollLast();
        }
    }

    /**
     * [1 2 3 4]
     * 分析子过程   下标begin之前的已经确定了，从begin开始后边的随便选
     * 结束条件
     */
    static int[] nums = new int[]{1, 2, 3, 4};
    static LinkedList<Integer> res = new LinkedList<>();
    public static void function(int begin){
        // base line
        if(begin == nums.length){
            // 结束
            System.out.println(Arrays.toString(res.toArray()));
            return;
        }

        // 对begin进行处理
        // 不选begin下标
        function(begin + 1);

        // 选begin下标
        res.add(nums[begin]);
        function(begin + 1);
        res.pollLast();

    }


    public static void main(String[] args) {
        function(0);
    }
}
