/*
Time limit: 1.00 s
Memory limit: 512 MB

Consider an n \times n grid whose squares may have traps. It is not allowed to move to a square with a trap.
Your task is to calculate the number of paths from the upper-left square to the lower-right square. You can only move right or down.
Input
The first input line has an integer n: the size of the grid.
After this, there are n lines that describe the grid. Each line has n characters: . denotes an empty cell, and * denotes a trap.
Output
Print the number of paths modulo 10^9+7.
Constraints

1 \le n \le 1000

Example
Input:
4
....
.*..
...*
*...

Output:
3
*/

import java.util.Scanner;
import java.util.Arrays;
public class gridPaths {
    
    public static int mod = 1_000_000_007;
    public static int states[][];
    public static int tab(int n, char[][]grid){
        int [][] tabStates = new int[n+1][n+1];
        tabStates[n-1][n-1]=grid[n-1][n-1]=='*'?0:1;
        for(int i=n-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(i>=n||i<0||j>=n||j<0||grid[i][j]=='*')continue;
                int right = tabStates[i][j+1];
                int down = tabStates[i+1][j];

                tabStates[i][j]+=(right+down)%mod;
            }
        }
        return tabStates[0][0];
    }
    public static int memo(int n, char[][]grid, int i, int j){
        if(i>=n||i<0||j>=n||j<0||grid[i][j]=='*')return 0;
        if(i==n-1&&j==n-1)return 1;
        if(states[i][j]!=-1)return states[i][j];
        int right = memo(n,grid,i,j+1);
        int down = memo(n,grid,i+1,j);
        int ans= (right+down)%mod;
        return states[i][j] = ans;

    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // consume leftover newline
        char[][] grid = new char[n][n];
        for(int i=0;i<n;i++){
            grid[i] = sc.nextLine().toCharArray();
        }
        // states = new int[n][n];
        // for(var temp: states)Arrays.fill(temp,-1);
        // System.out.println(memo(n, grid, 0, 0));
        System.out.println(tab(n, grid));
    }
}
