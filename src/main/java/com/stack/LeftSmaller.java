package com.stack;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * @Author: hwj
 * @Date: 2022/8/27
 * @Description:
 */
public class LeftSmaller {

    public static void main(String[] args) {
        int[] array = new Random().ints(10, 1, 100).toArray();
        int[] ans = leftSmaller(array);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(ans));
    }

    public static int[] leftSmaller(int[] nums){
        int[] ans = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while(!stack.empty() && stack.peek() >= nums[i]) stack.pop();
            ans[i] = stack.empty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return ans;
    }

}
