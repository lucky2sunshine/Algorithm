package com.other;

/**
 * @Author: hwj
 * @Date: 2021/11/24
 * @Description: KMP算法
 */
public class KMP {

    public static int kmp(String str1, String str2){
        if(str1.length() < str2.length()) return -1;
        int[] next = getNextArray(str2);
        int i = 0;
        int j = 0;
        while(i < str1.length() && j < str2.length()){
            if(str1.charAt(i) == str2.charAt(j)){
                i++;
                j++;
            }else if(j == 0){
                i++;
            }else{
                j = next[j];
            }
        }
        return j == str2.length() ? i - j : -1;
    }

    public static int[] getNextArray(String str){
        char[] strs = str.toCharArray();
        assert strs.length > 0;
        if(strs.length == 1) return new int[]{-1};
        int[] next = new int[strs.length];
        next[0] = -1;
        next[1] = 0;
        int cur = 2;
        int prefix = 0;
        while (cur < strs.length){
            if(strs[cur - 1] == strs[prefix]) next[cur++] = ++prefix;
            else if(prefix > 0) prefix = next[prefix];
            else next[cur++] = 0;
        }
        return next;
    }

    public static void main(String[] args) {
        System.out.println(kmp("abcabcdabc", "abcd"));
    }

}
