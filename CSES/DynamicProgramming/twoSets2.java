/*
Time limit: 1.00 s
Memory limit: 512 MB

Your task is to count the number of ways numbers 1,2,\ldots,n can be divided into two sets of equal sum.
For example, if n=7, there are four solutions:

\{1,3,4,6\} and \{2,5,7\}
\{1,2,5,6\} and \{3,4,7\}
\{1,2,4,7\} and \{3,5,6\}
\{1,6,7\} and \{2,3,4,5\}

Input
The only input line contains an integer n.
Output
Print the answer modulo 10^9+7.
Constraints

1 \le n \le 500

Example
Input:
7

Output:
4
*/

import java.util.Scanner;

public class twoSets2 {
    static int MOD = 1000000007;
    public static long memo(int n, int i , int sum, int target, long[][] dp){
        if(sum > target) return 0;
        if(i == n){
            if(target == sum)return 1;
            return 0;
        }
        if(dp[i][sum] != -1)return dp[i][sum];
        long ignore = memo(n,i+1,sum,target,dp);
        long take = memo(n,i+1,sum+i,target,dp);

        return dp[i][sum] = (ignore + take) % MOD;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = (n*(n+1))/2;
        if(sum%2!=0){
            System.out.println(0);
            return;
        }
        int target = sum/2;

        long[][]dp = new long[n+1][target + 1];
        for(int i = 0 ; i <= n ; i++){
            for(int j = 0 ; j <= target ; j++){
                dp[i][j] = -1;
            }
        }
        System.out.println(memo(n,1,0,target,dp));
    }

}
// MISTAKES
/*
1. DP initialization mistake:
    Java initializes long[][] with 0, but I was using -1 to represent “not calculated”.
    Fix: initialize every dp[i][sum] with -1.
2. Double-counting mistake:
    Each partition was counted twice because both sets have the same target sum.
    Example:
    {1,2} | {3}
    {3} | {1,2}
    represent the same partition.
3. Incorrect division after modulo:
    I used answer / 2 after applying modulo during DP.
    (x % MOD) / 2 is not always equal to (x / 2) % MOD.
4. Final fix:
    Avoid double counting completely by fixing n in one set and only finding subsets from 1 to n-1.

Use:
memo(n - 1, 1, 0, target, dp)

instead of:
memo(n, 1, 0, target, dp) / 2
 */
