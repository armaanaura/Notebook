/*
Time limit: 1.00 s
Memory limit: 512 MB

You are given an n \times n grid whose each square contains a letter.
You should move from the upper-left square to the lower-right square. You can only move right or down.
What is the lexicographically minimal string you can construct?
Input
The first line has an integer n: the size of the grid.
After this, there are n lines that describe the grid. Each line has n letters between A and Z.
Output
Print the lexicographically minimal string.
Constraints

1 \le n \le 3000

Example
Input:
4
AACA
BABC
ABDA
AACA

Output:
AAABACA
*/
import java.util.*;
public class minimalGridPath {
    // but here we also have a catch we need the smallest path with smaller lexographical order, not just lexographical order because BA is smaller that AAA. BUT IMPORTANT: in a grid, if we have only down and right possible direction, all the path lengths will be same
    // We can't just directly choose the smallest character because we might see a case where both the current choises are same but the next choises are different, Hence we need to use frontier recursion - if both are same, call recursion for both and choose the one which is smaller.
    public static String recursion(char[][] grid,int i, int j, int n, StringBuffer sb){
        StringBuffer curr = new StringBuffer();
        
    }

    // Lexographically dont mean smallest sum of the path but it means small characters comes first and larger at last hence AZZZ is better that BAAA
    public static String tabulation(char[][]grid, int n){
        int dp[][]=new int[n+1][n+1];
        for(int i=n-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                dp[i][j]=Math.min(dp[i+1][j],dp[i][j+1])+grid[i][j]-'a';
            }
        }
        int i=0,j=0;
        StringBuffer sb = new StringBuffer();
        while (i != n - 1 || j != n - 1) {
            sb.append(grid[i][j]);
                if (i == n - 1) {
                j++;
            } else if (j == n - 1) {
                i++;
            } else if (dp[i + 1][j] < dp[i][j + 1]) {
                i++;
            } else {
                j++;
            }
        }
        sb.append(grid[i][j]);
        return sb.toString();
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[][] grid = new char[n][n];
        for(int i=0;i<n;i++){
            String s = sc.next();
            for(int j=0;j<n;j++){
                grid[i][j] = s.charAt(j);
            }
        }
        StringBuffer sb = new StringBuffer();
        recursion(grid, 0, 0, n, sb);
        System.out.println(sb);
    }
}
