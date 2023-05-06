package com.binary_search;

/**
 * @Author: hwj
 * @Date: 2022/8/24
 * @Description:
 */
public class IsBlueBinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,5,7,8,9,23,96,100};
        int target = 3;
        System.out.println(binarySearch(nums, target));
    }

    public static int binarySearch(int[] nums, int target){
        int left = -1;
        int right = nums.length;
        while(left + 1 != right){
            int middle = (left + right) / 2;
            if(nums[middle] <= target){
                left = middle;
            }else{
                right = middle;
            }
        }
        return left;
    }
}
