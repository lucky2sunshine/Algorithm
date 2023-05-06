package com.stack;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * @Author: hwj
 * @Date: 2022/8/27
 * @Description:
 */
public class LeftBigger {

    public static void main(String[] args) {
        int[] array = new Random().ints(10, 1, 100).toArray();
        int[] ans = leftBigger2(array);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(ans));
    }

    public static int[] leftBigger(int[] nums){
        int[] ans = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while(!stack.empty() && stack.peek() <= nums[i]) stack.pop();
            ans[i] = stack.empty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return ans;
    }

    public static int[] leftBigger2(int[] nums){
        int[] ans = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < nums.length; i++){
            while(!stack.isEmpty() && stack.peek() <= nums[i]) stack.pop();
            ans[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return ans;
    }
}
