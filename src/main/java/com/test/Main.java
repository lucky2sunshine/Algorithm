package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static int process01(int[] w, int[] c, int rest, int index){// 从index之后，还剩rest容量，返回最多的钱数
        // base case
        /**
         * 对于无效解的两种方案
         * 1. 不让它走到无效解，在可能走到无效解之前进行判断
         * 2. 无效解返回-1，在递归时如果结果返回-1再进行处理
         */
        if(rest == 0) return 0;
        if(index == w.length) return 0;

        // 背包还有容量，也还有物品可以选
        int p1 = process01(w, c, rest , index + 1); // 不选
        int p2 = rest - c[index] >= 0 ? w[index] + process01(w, c, rest - c[index],  index + 1) : 0; // 选

        return Math.max(p1,p2);

    }

    public static int process01_dp(int[] w, int[] c, int rest, int index, int[][] dp){// 从index之后，还剩rest容量，返回最多的钱数
        if(dp[index][rest] != -2) return dp[index][rest];

        if(rest == 0) {
            dp[index][rest] = 0;
            return dp[index][rest];
        }
        if(index == w.length) {
            dp[index][rest] = 0;
            return dp[index][rest];
        }

        // 背包还有容量，也还有物品可以选
        int p1 = process01_dp(w, c, rest , index + 1,dp); // 不选
        int p2 = rest - c[index] >= 0 ? w[index] + process01_dp(w, c, rest - c[index],  index + 1, dp) : 0; // 选

        dp[index][rest] = Math.max(p1,p2);
        return dp[index][rest];

    }

    public static int process01_dp_table(int[] w, int[] c, int N, int V, int[][] dp){// 从index之后，还剩rest容量，返回最多的钱数
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 1; j < V + 1; j++) {
//                if(j - c[i] >= 0) dp[i][j] = Math.max(dp[i + 1][j], w[i] + dp[i + 1][j - c[i]]);
//                else dp[i][j] = dp[i + 1][j];
                dp[i][j] = j - c[i] >= 0 ? Math.max(dp[i + 1][j], w[i] + dp[i + 1][j - c[i]]) : dp[i + 1][j];
            }
        }
        return dp[0][V];
    }

    public static int process01_scroll_array(int[] w, int[] c, int N, int V, int[] dp){// 从index之后，还剩rest容量，返回最多的钱数
        for (int i = N - 1; i >= 0; i--) {
            for (int j = V; j >= 0; j--) {
                dp[j] = j - c[i] >= 0 ? Math.max(dp[j], w[i] + dp[j - c[i]]) : dp[j];
            }
        }
        return dp[V];
    }

    public static void process01_test_scroll_array(){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int V = scanner.nextInt();
        int[] w = new int[N];
        int[] c = new int[N];
        for (int i = 0; i < N; i++) {
            c[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }
        int[] dp = new int[V + 1];
        System.out.println(process01_scroll_array(w,c,N,V,dp));
    }

    public static void process01_test_dp_table(){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int V = scanner.nextInt();
        int[] w = new int[N];
        int[] c = new int[N];
        for (int i = 0; i < N; i++) {
            c[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }
        int[][] dp = new int[N + 1][V + 1];
        for (int i = 0; i < (N + 1); i++) {
            for (int j = 0; j < (V +1); j++) {
                dp[i][j] = -2;
            }
        }
        for (int i = 0; i < N + 1; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i < V + 1; i++) {
            dp[N][i] = 0;
        }

//        System.out.println(process01(w,c,V,0));
        System.out.println(process01_dp_table(w,c,N,V,dp));
    }

    public static void process01_test(){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int V = scanner.nextInt();
        int[] w = new int[N];
        int[] c = new int[N];
        for (int i = 0; i < N; i++) {
            c[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }
        int[][] dp = new int[N + 1][V + 1];
        for (int i = 0; i < (N + 1); i++) {
            for (int j = 0; j < (V +1); j++) {
                dp[i][j] = -2;
            }
        }
//        System.out.println(process01(w,c,V,0));
        System.out.println(process01_dp(w,c,V,0,dp));
    }

    public static int process02(int[] w, int[] c, int rest){ // 背包容量还剩rest，剩下的随便选，可以装的最大价值
        // base case
        if(rest == 0) return 0;

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < w.length; i++) {
            max = Math.max(max, rest - c[i] >= 0? w[i] + process02(w, c, rest - c[i]) : 0);
        }
        return max;
    }
    

    public static int process02_dp(int[] w, int[] c, int rest, int[] dp){ // 背包容量还剩rest，剩下的随便选，可以装的最大价值
        // base case
        if(dp[rest] != -1) return dp[rest];

        if(rest == 0) {
            dp[rest] = 0;
            return dp[rest];
        }

        for (int i = 0; i < w.length; i++) {
            dp[rest] = Math.max(dp[rest], (rest - c[i] >= 0 ? w[i] + process02_dp(w, c, rest - c[i], dp) : 0));
        }

        return dp[rest];
    }

    public static int process02_dp_table(int[] w, int[] c, int N, int V, int[][] dp){ // 背包容量还剩rest，剩下的随便选，可以装的最大价值
        for (int j = V - 1; j >= 0; j--) {
             for (int i = N - 1; i >= 0; i--) {
                dp[i][j] = Math.max(dp[i][j], (j - c[i] >= 0 ? w[i] + dp[i + 1][j - c[i]] : 0));
            }
        }

        return dp[0][V];
    }

    public static void process02_test_dp_table(){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int V = scanner.nextInt();
        int[] w = new int[N];
        int[] c = new int[N];
        for (int i = 0; i < N; i++) {
            c[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }
        int[][] dp = new int[N + 1][V + 1];
        for (int i = 0; i < (N + 1); i++) {
            for (int j = 0; j < (V +1); j++) {
                dp[i][j] = 0;
            }
        }
        for (int i = 0; i < N + 1; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i < V + 1; i++) {
            dp[N][i] = 0;
        }
        System.out.println(process02_dp_table(w,c,N,V,dp));
    }

    public static void process02_test(){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int V = scanner.nextInt();
        int[] w = new int[N];
        int[] c = new int[N];
        for (int i = 0; i < N; i++) {
            c[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }
        int[] dp = new int[V + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        System.out.println(process02_dp(w,c,V,dp));
    }

    public static int process03_dp(int[] w, int[] c, int rest, int index, int[][] dp){
        return process01_dp(w, c, rest, index, dp);
    }



    public static void process03_test(){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int V = scanner.nextInt();
        int[] w = new int[N];
        int[] c = new int[N];
        int[] s = new int[N];
        for (int i = 0; i < N; i++) {
            c[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
            s[i] = scanner.nextInt();
        }

        List<Integer> w_ = new ArrayList<>();
        List<Integer> c_ = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            int count = s[i];
            for (int j = 0; j < count; j++) {
                w_.add(w[i]);
                c_.add(c[i]);
            }
        }

        int[] w_array = w_.stream().mapToInt(Integer::valueOf).toArray();
        int[] c_array = c_.stream().mapToInt(Integer::valueOf).toArray();

        int[][] dp = new int[w_array.length + 1][V + 1];
        for (int i = 0; i < (w_array.length + 1); i++) {
            for (int j = 0; j < (V +1); j++) {
                dp[i][j] = -2;
            }
        }
        System.out.println(process03_dp(w_array,c_array,V,0,dp));
    }

    public static void process03_binary_test(){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int V = scanner.nextInt();
        int[] w = new int[N];
        int[] c = new int[N];
        int[] s = new int[N];
        for (int i = 0; i < N; i++) {
            c[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
            s[i] = scanner.nextInt();
        }

        List<Integer> w_ = new ArrayList<>();
        List<Integer> c_ = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            int count = s[i];
            int[] sArray = getSArray(count);
            for (int value : sArray) {
                w_.add(value * w[i]);
                c_.add(value * c[i]);
            }
        }

        int[] w_array = w_.stream().mapToInt(Integer::valueOf).toArray();
        int[] c_array = c_.stream().mapToInt(Integer::valueOf).toArray();

        int[][] dp = new int[w_array.length + 1][V + 1];
        for (int i = 0; i < (w_array.length + 1); i++) {
            for (int j = 0; j < (V +1); j++) {
                dp[i][j] = -2;
            }
        }
        System.out.println(process03_dp(w_array,c_array,V,0,dp));
    }

    public static int[] getSArray(int s){
        int maxK = 0;
        while((1 << maxK) - 1 <= s) maxK++;
        maxK--;
        int other = s - (1 << maxK) + 1;
        int length = other == 0 ? maxK : maxK + 1;
        int[] ans = new int[length];
        for (int i = 0; i < maxK; i++) ans[i] = 1 << i;
        if(other != 0) ans[maxK] = other;
        return ans;
    }

    public static int process05(int[] w, int[] c, int[] m, int restV, int restM, int index){// 从index之后，还剩restV容量，restM质量，返回最多的钱数
        // base case
        /**
         * 对于无效解的两种方案
         * 1. 不让它走到无效解，在可能走到无效解之前进行判断
         * 2. 无效解返回-1，在递归时如果结果返回-1再进行处理
         */
        if(restV == 0 || restM == 0 || index == w.length) return 0;

        // 背包还有容量，也还有物品可以选
        int p1 = process05(w, c, m, restV, restM, index + 1); // 不选
        int p2 = restV - c[index] >= 0 && restM - m[index] >= 0 ? w[index] + process05(w, c, m, restV - c[index],  restM - m[index], index + 1) : 0; // 选

        return Math.max(p1,p2);
    }

    public static int process05_dp(int[] w, int[] c, int[] m, int restV, int restM, int index, int[][][] dp){// 从index之后，还剩restV容量，restM质量，返回最多的钱数
        // base case
        /**
         * 对于无效解的两种方案
         * 1. 不让它走到无效解，在可能走到无效解之前进行判断
         * 2. 无效解返回-1，在递归时如果结果返回-1再进行处理
         */
        if(dp[index][restV][restM] != -1) return dp[index][restV][restM];

        if(restV == 0 || restM == 0 || index == w.length) {
            dp[index][restV][restM] = 0;
            return dp[index][restV][restM];
        };

        // 背包还有容量，也还有物品可以选
        int p1 = process05(w, c, m, restV, restM, index + 1); // 不选
        int p2 = restV - c[index] >= 0 && restM - m[index] >= 0 ? w[index] + process05(w, c, m, restV - c[index],  restM - m[index], index + 1) : 0; // 选

        dp[index][restV][restM] = Math.max(p1,p2);
        return dp[index][restV][restM];
    }

    public static int process05_dp_table(int[] w, int[] c, int[] m, int V, int M, int N, int[][][] dp){// 从index之后，还剩restV容量，restM质量，返回最多的钱数

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 1; j <= V; j++) {
                for (int k = 1; k <= M; k++) {
                    if(j - c[i] >= 0 && k - m[i] >= 0){
                        dp[i][j][k] = Math.max(dp[i + 1][j][k], w[i] + dp[i + 1][j - c[i]][k - m[i]]);
                    }else{
                        dp[i][j][k] = dp[i + 1][j][k];
                    }
                }
            }
        }
        return dp[0][V][M];
    }

    public static void process05_test_dp_table() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int V = scanner.nextInt();
        int M = scanner.nextInt();
        int[] w = new int[N];
        int[] c = new int[N];
        int[] m = new int[N];
        for (int i = 0; i < N; i++) {
            c[i] = scanner.nextInt();
            m[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }
        int[][][] dp = new int[N + 1][V + 1][M + 1];
        System.out.println(process05_dp_table(w, c, m, V, M, N, dp));
    }

    public static void process05_test(){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int V = scanner.nextInt();
        int M = scanner.nextInt();
        int[] w = new int[N];
        int[] c = new int[N];
        int[] m = new int[N];
        for (int i = 0; i < N; i++) {
            c[i] = scanner.nextInt();
            m[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }
        int[][][] dp = new int[N + 1][V + 1][M + 1];
        for (int i = 0; i < N + 1; i++)
            for (int j = 0; j < V + 1; j++)
                for (int k = 0; k < M + 1; k++)
                    dp[i][j][k] = -1;
        System.out.println(process05_dp(w,c,m,V,M,0,dp));
    }


    public static void main(String[] args) {
        process05_test_dp_table();
    }
}