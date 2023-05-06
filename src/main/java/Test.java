import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hwj
 * @Date: 2022/8/9
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        List[] lists = new List[23];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList<String>(){{
                add(String.valueOf(1));
            }};
        }
        for (int i = 0; i < lists.length; i++) {
            System.out.println(lists[i]);
        }
    }

    public static int minDistance(String word1, String word2) {
        char[] chs1 = word1.toCharArray();
        char[] chs2 = word2.toCharArray();
        int rows = chs1.length + 1;
        int cols = chs2.length + 1;
        int[][] dp = new int[rows][cols];
        dp[0][0] = 0;

        for(int col = 1; col < cols; col++) dp[0][col] = 1;

        for(int row = 1; row < rows; row++) dp[row][0] = 1;

        for(int row = 1; row < rows; row++){
            for(int col = 1; col < cols; col++){
                int insertOrDelete = Math.min(dp[row][col - 1], dp[row - 1][col]) + 1;
                int replace = dp[row - 1][col - 1] + (chs1[row - 1] == chs2[col - 1] ? 0 : 1);
                dp[row][col] = Math.min(insertOrDelete, replace);
            }
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return dp[rows - 1][cols - 1];
    }

}
