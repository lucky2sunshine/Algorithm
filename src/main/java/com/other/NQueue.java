package com.other;

import java.util.Map;

/**
 * @Author: hwj
 * @Date: 2021/11/19
 * @Description:
 */
public class NQueue {

    public static int nQueue(int n){
        if(n <= 0) return 0;
        int[] record = new int[n];
        return process(0, record, n);
    }

    public static int process(int i, int[] record, int n) {
        /**
         * i 表示现在考察第i行
         * record 表示现在防止的皇后位置
         * n 表示这是一个n皇后问题
         */
        if(i == n) return 1;
        int res = 0;
        for (int j = 0; j < n; j++) { // 考察第i行所有位置
            if(isValid(record, i, j)){ // 如果有位置
                record[i] = j;
                res += process(i + 1, record, n);
            }
        }
        return res;
    }

    private static boolean isValid(int[] record, int i, int j) {
        // (i,j) (k, record[k])
        for (int k = 0; k < i; k++) if(j == record[k] || Math.abs(i - k) == Math.abs(j - record[k])) return false;
        return true;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 11; i++) {
            System.out.println(i + ": " + nQueue(i));
        }
    }

}
