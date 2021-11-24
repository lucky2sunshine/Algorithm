package com.force_recursive;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: hwj
 * @Date: 2021/11/21
 * @Description:
 */
public class AllSubStrings {

    public static void allSubStrings(String str){
//        process(str.toCharArray(), 0, "");
//        process2(str.toCharArray(), 0, new LinkedList<>());
        process3(str.toCharArray(), 0);
    }


    public static void process(char[] chars, int i, String curStr){
        if(i == chars.length) {
            System.out.println(curStr);
            return;
        }
        process(chars, i + 1, curStr);
        process(chars, i + 1, curStr + chars[i]);
    }

    public static void process2(char[] chars, int i, LinkedList<Character> curChars){
        if(i == chars.length) {
            System.out.println(Arrays.toString(curChars.toArray()));
            return;
        }
        curChars.offer(chars[i]);
        process2(chars, i + 1, curChars);
        curChars.pollLast();
        process2(chars, i + 1, curChars);
    }

    public static void process3(char[] chars, int i){
        if(i == chars.length) {
            System.out.println(String.valueOf(chars));
            return;
        }
        // 不加当前字符
        process3(chars, i + 1);
        // 加当前字符
        char temp = chars[i];
        chars[i] = 0;
        process3(chars, i + 1);
        chars[i] = temp;
    }

    public static void main(String[] args) {
        allSubStrings("123");
        System.out.println(String.valueOf(new char[]{'1','2','3',0,'1','2','3'}));
    }

}
