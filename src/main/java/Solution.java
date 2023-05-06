class Solution {
    private int ans = 0;
    private int max = -1;

    public int longestSubarray(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            int tmp = 0x7FFFFFFF;
            if(i - 1 >= 0 && i + 1 < nums.length && nums[i] == nums[i - 1] && nums[i] == nums[i + 1]) continue;
            for(int j = i; j < nums.length; j++){
                tmp = tmp & nums[j];
                if(tmp == ans){
                    max = Math.max(max, j - i + 1);
                }else if(tmp > ans){
                    ans = tmp;
                    max = j - i + 1;
                }
                if(tmp == 0) break;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestSubarray(new int[]{96317,96317,96317,96317,96317,96317,96317,96317,96317,279979}));
    }
}