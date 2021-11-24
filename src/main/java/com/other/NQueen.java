package com.other;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hwj
 * @Date: 2021/11/19
 * @Description:
 */
public class NQueen {

    public static List<List<String>> nQueen(int n){
        List<List<String>> result = new ArrayList<>();
        if(n <= 0) return result;
        int[] record = new int[n];
        process(0, record, n, result);
        return result;
    }

    public static int process(int i, int[] record, int n,List<List<String>> result) {
        /**
         * i 表示现在考察第i行
         * record 表示现在防止的皇后位置
         * n 表示这是一个n皇后问题
         */
        if(i == n) {
            addToResult(record, result);
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) { // 考察第i行所有位置
            if(isValid(record, i, j)){ // 如果有位置
                record[i] = j;
                res += process(i + 1, record, n, result);
            }
        }
        return res;
    }

    private static void addToResult(int[] record, List<List<String>> result) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < record.length; i++) {
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < record[i]; j++) str.append(".");
            str.append("Q");
            for (int k = record[i] + 1; k < record.length; k++) str.append(".");
            list.add(str.toString());
        }
        result.add(list);
    }

    private static boolean isValid(int[] record, int i, int j) {
        // (i,j) (k, record[k])
        for (int k = 0; k < i; k++) if(j == record[k] || Math.abs(i - k) == Math.abs(j - record[k])) return false;
        return true;
    }

    public static void main(String[] args) {
        nQueen(5);
    }

}
