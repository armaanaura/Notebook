/*
Time limit: 1.00 s
Memory limit: 512 MB

Given two arrays of integers, find their longest common subsequence.
A subsequence is a sequence of array elements from left to right that can contain gaps. A common subsequence is a subsequence that appears in both arrays.
Input
The first line has two integers n and m: the sizes of the arrays.
The second line has n integers a_1,a_2,\dots,a_n: the contents of the first array.
The third line has m integers b_1,b_2,\dots,b_m: the contents of the second array.
Output
First print the length of the longest common subsequence.
After that, print an example of such a sequence. If there are several solutions, you can print any of them.
Constraints

1 \le n,m \le 1000
1 \le a_i, b_i \le 10^9

Example
Input:
8 6
3 1 3 2 7 4 8 2
6 5 1 2 3 4

Output:
3
1 2 4
*/
import java.util.*;
public class longestCommonSubsequence{
    public static int tab(int arr1[], int arr2[]){
        int states[][] = new int[arr1.length+1][arr2.length+1];
        for(int i=1;i<states.length;i++){
            for(int j=1;j<states[0].length;j++){
                if(arr1[i-1] == arr2[j-1]){
                    states[i][j] = states[i-1][j-1] + 1;
                }else{
                    states[i][j] = Math.max(states[i-1][j] , states[i][j-1]);
                }
            }
        }
        ArrayList<Integer> lcs = new ArrayList<>();
        int i=states.length-1, j=states[0].length-1;
        while(i>=1 || j>=1){
            
        }
    }
    public static int memo(int i, int j, int[]arr1, int[]arr2, int[][]states){
        if(i==arr1.length || j==arr2.length)return 0;
        if(states[i][j]!=-1)return states[i][j];
        int length = 0;
        if(arr1[i] == arr2[j]){
            length = 1 + memo(i+1, j+1, arr1, arr2, states);
        }else{
            length = Math.max(memo(i+1,j,arr1,arr2,states),memo(i, j+1, arr1, arr2, states));
        }
        return states[i][j] = length;
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] states = new int[n][m];
        for(var temp:states)Arrays.fill(temp,-1);
        int arr1[] = new int[n];
        int arr2[] = new int[m];

        for(int i=0;i<arr1.length;i++)arr1[i]=sc.nextInt();
        for(int i=0;i<arr2.length;i++)arr2[i]=sc.nextInt();

        int lcs = memo(0,0,arr1,arr2,states);
        System.out.println(lcs);
    }
}