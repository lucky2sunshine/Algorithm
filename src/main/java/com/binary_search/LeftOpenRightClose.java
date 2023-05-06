package com.binary_search;

/**
 * @Author: hwj
 * @Date: 2022/8/24
 * @Description:
 */
public class LeftOpenRightClose {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,5,7,8,9,23,96,100};
        int target = 3;
        System.out.println(binarySearch(nums, target));
    }

    public static int binarySearch(int[] nums, int target){
        int left = -1;
        int right = nums.length - 1;
        while(left < right){
            int middle = (left + right) / 2;
            if(target < nums[middle]){
                right = middle - 1;
            }else if(target > nums[middle]){
                left = middle;
            }else{
                return middle;
            }
        }
        return -1;
    }
}
