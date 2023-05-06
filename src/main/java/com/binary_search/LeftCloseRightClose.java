package com.binary_search;

/**
 * @Author: hwj
 * @Date: 2022/8/24
 * @Description:
 */
public class LeftCloseRightClose {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,5,7,8,9,23,96,100};
        int target = 101;
        System.out.println(binarySearch(nums, target));
    }

    public static int binarySearch(int[] nums, int target){
        int left = 0;
        int right = nums.length;
        while(left < right){
            int middle = (left + right) / 2;
            if(target < nums[middle]){
                right = middle - 1;
            }else if(target > nums[middle]){
                left = middle + 1;
            }else{
                return middle;
            }
        }
        return -1;
    }
}
