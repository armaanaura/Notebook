/*
Time limit: 1.00 s
Memory limit: 512 MB

You know that an array has n integers between 1 and  m, and the absolute difference between two adjacent values is at most 1.
Given a description of the array where some values may be unknown, your task is to count the number of arrays that match the description.
Input
The first input line has two integers n and m: the array size and the upper bound for each value.
The next line has n integers x_1,x_2,\dots,x_n: the contents of the array. Value 0 denotes an unknown value.
Output
Print one integer: the number of arrays modulo 10^9+7.
Constraints

1 \le n \le 10^5
1 \le m \le 100
0 \le x_i \le m

Example
Input:
3 5
2 0 2

Output:
3

Explanation: The arrays [2,1,2], [2,2,2] and [2,3,2] match the description.
*/

import java.util.Scanner;
import java.util.Arrays;

public class arrayDescription {
    public static int[][]states;
    public static int mod = 1_000_000_007;
    public static int tab(int n, int m, int[]array){
        // tabulation is pending
        return 0;
    }
    public static int memo(int n, int m, int[]array, int index, int prev){
        if(index>=n)return 1;
        if(Math.abs(array[index]-prev)>1&&array[index]!=0)return states[index][prev] = 0;
        if(states[index][prev]!=-1) return states[index][prev];
        if(array[index]!=0)return memo(n,m,array,index+1,array[index]);

        int smaller = 0;
        int larger = 0;
        int same = memo(n,m,array,index+1,prev);
        if(prev-1>0&&(prev-1)<=m)smaller = memo(n,m,array,index+1,prev-1);
        if(prev+1>0&&(prev+1)<=m)larger = memo(n,m,array,index+1,prev+1);

        int ans = ((smaller+larger)%mod+same)%mod;

        return states[index][prev] = ans;


    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int [] array = new int[n];
        for(int i=0;i<n;i++){
            array[i]=sc.nextInt();
        }
        states = new int[n][m+1];
        for(int[] temp:states)Arrays.fill(temp,-1);
        int ans = 0;
        if(array[0]!=0){
            ans = memo(n, m, array, 0, array[0]);
        }else{
            for(int i=1;i<=m;i++)ans=(ans+memo(n,m,array,1,i))%mod;
        }
        System.out.println(ans);
        // System.out.println(tab(prices, pages, budget));
    }
}
