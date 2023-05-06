import java.util.*;

/**
 * @Author: hwj
 * @Date: 2021/11/22
 * @Description:
 */
public class Test {
    public static int lengthOfLongestSubstring(String s) {
        char[] str = s.toCharArray();
        if(str.length == 0 || str.length == 1) return str.length;
        int left = 0;
        int max = Integer.MIN_VALUE;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < str.length; i++){
            if(!map.containsKey(str[i])){
                map.put(str[i], i);
            }else{
                max = Math.max(max, i - left);
                int newLeft = map.get(str[i]);
                for(int j = left; j <= newLeft; j++){
                    map.remove(str[j]);
                }
                left = newLeft + 1;
                map.put(str[i], i);
            }
        }
        max = Math.max(max, map.size());
        return max;
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        char[] str = s.toCharArray();
        List<String> ans = new ArrayList();
        if(str.length < 10) return ans;
        StringBuilder builder = new StringBuilder();
        HashMap<String, Integer> map = new HashMap<>();
        builder.append('0');
        for (int i = 0; i < 9; i++) builder.append(str[i]);
        for(int i = 9; i < str.length; i++){
            builder.deleteCharAt(0);
            builder.append(str[i]);
            String substr = builder.toString();
            if(!map.containsKey(substr)){
                map.put(substr, 1);
            }else{
                map.put(substr, map.get(substr) + 1);
            }
        }
        map.forEach((key, value) -> {
            if(value > 1) ans.add(key);
        });
        return ans;
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0; i < k + 1 && i < nums.length; i++){
            if(!set.contains(nums[i])){
                set.add(nums[i]);
                queue.add(nums[i]);
            }
            else return true;
        }
        for(int i = k + 1; i < nums.length; i++){
            set.remove(queue.peek());
            if(!set.contains(nums[i])){
                set.add(nums[i]);
                queue.add(nums[i]);
            }else{
                return true;
            }
        }
        return false;
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        Queue<Integer> list = new LinkedList<>();
        int cur = 0;
        int sum = 0;
        while(cur < nums.length){
            sum += nums[cur];
            list.add(nums[cur]);
            if(sum >= target){
                ans = Math.min(ans, list.size());
                sum -= list.poll();
                if(sum >= target) ans = Math.min(ans, list.size());
                while(sum - list.peek() >= target){
                    sum -= list.poll();
                    ans = Math.min(ans, list.size());
                }
            }
            cur++;
        }
        return list.size() == nums.length ? 0 : ans;

    }

    public static String convert(String s, int numRows) {
        char[][] arr = new char[numRows][1000];
        char[] strs = s.toCharArray();
        if(numRows == 1) return s;

        int cur = 0;
        int row = 0;
        int col = 0;
        boolean goingDown = false;
        while(cur < strs.length){
            arr[row][col] = strs[cur++];
            if(row == 0 || row == numRows - 1) goingDown = !goingDown;
            if(goingDown) {row++;}
            else {row--; col++;}
        }
        StringBuilder strings = new StringBuilder();
        for(int rows = 0; rows < numRows; rows++){
            for(int cols = 0; cols <= col; cols++){
                if(arr[rows][cols] != '\0') strings.append(arr[rows][cols]);
            }
        }
        return strings.toString();
    }

    static long maxValue = Integer.MIN_VALUE;
    ArrayList<Integer> maxPath;


    public long process(int[][] grid, int row, int col, int n, int max, ArrayList<Integer> path){// 返回从(row, col) 到 (1, n - 1所能获得的最大点数) path 记录路径， 0 为右 1为下
        if(row == 1 && col == n - 1) {
            max += grid[row][col];
            // judge
            if(maxValue < max){
                maxValue = max;
                maxPath = (ArrayList<Integer>)path.clone();
            }
            max -= grid[row][col];
            return grid[row][col];
        }

        long right = 0;
        if(col + 1 < n){
            path.add(0);
            right = grid[row][col] + process(grid, row, col + 1, n, max, path);
        }
        long down = 0;
        if(row + 1 < 2){
            path.add(1);
            down = grid[row][col] + process(grid, row + 1, col, n, max, path);
        }

        max += grid[row][col];
        // judge
        maxValue = max;
        maxPath = (ArrayList<Integer>)path.clone();
        max -= grid[row][col];
        return Math.max(right, down);
    }

    public static double myPow(double x, int n) {
        // 5 0101 (4 + 1)    10   8 0 0 2 0
        double ans = 1;
        int current = 1;
        for(int i = 0; i < 31; i++){
            if((n & 1) == 1){
                ans *= (x * current);
            }
            n = (n >> 1);
            current = (current << 1);
        }
        return ans;
    }

    public static int reverse(int x) {
        if (x == 0) return 0;
        int ans = 0;
        int num = x;
        while (num != 0) {
            if (ans < Integer.MIN_VALUE / 10 || ans > Integer.MAX_VALUE / 10) return 0;
            int mod = num % 10;
            ans = ans * 10 + mod;
            num = num / 10;
        }
        return ans;
    }

    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int ans = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        for(int price : prices){
            if(stack.isEmpty()){
                stack.addLast(price);
            }else{
                if(stack.peekLast() >= price){
                    stack.addLast(price);
                    ans = Math.max(ans, stack.peekFirst() - stack.peekLast());
                }else{
                    while(!stack.isEmpty() && stack.peekLast() < price){
                        stack.pollLast();
                    }
                    stack.addLast(price);
                }
            }
        }
        return ans;
    }

    public static int numSquares(int n) {
        int ans = 0;
        int count = 10;
        while(count > 0){
            while(n - Math.pow(count, 2) >= 0){
//                ans += (n / Math.pow(count, 2));
//                System.out.println((Math.pow(count, 2) * (n / Math.pow(count, 2))));
                n -= (int)Math.pow(count, 2);
                ans++;
            }
            count--;
        }
        return ans;
    }

    public static void quickSort(int[] nums, int start, int end){
        if(start >= end) return;
        int mid = findMid(nums, start, end);
        quickSort(nums, start, mid - 1);
        quickSort(nums, mid + 1, end);
    }

    private static int findMid(int[] nums, int start, int end) {
        int flag = nums[end];
        int left = start - 1; // < flag
        int current = start;
        while(current < end){
            if(nums[current] < flag){
                swap(nums, current++, ++left);
            }else{
                current++;
            }
        }
        swap(nums, ++left, end);
        return left;
    }

    public static void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static int[] mergeSort(int[] nums, int start, int end){
        if(start > end) return new int[]{};
        if(start == end) return new int[]{nums[start]};

        int mid = start + (end - start) / 2;
        int[] left = mergeSort(nums, start, mid);
        int[] right = mergeSort(nums, mid + 1, end);

        // merge
        int[] temp = new int[left.length + right.length];
        int currentLeft = 0;
        int currentRight = 0;
        int currentTemp = 0;

        while(currentLeft < left.length && currentRight < right.length){
            if(left[currentLeft] < right[currentRight]) temp[currentTemp++] = left[currentLeft++];
            else temp[currentTemp++] = right[currentRight++];
        }

        while(currentLeft < left.length) temp[currentTemp++] = left[currentLeft++];
        while(currentRight < right.length) temp[currentTemp++] = right[currentRight++];

        return temp;
    }




    public static void main(String[] args) {
        int[] nums = new Random().ints(100, 0, 100).toArray();
        System.out.println(Arrays.toString(nums));
        nums = mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }




}
