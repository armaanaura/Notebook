/*
Time limit: 1.00 s
Memory limit: 512 MB

You have n coins with certain values. Your task is to find all money sums you can create using these coins.
Input
The first input line has an integer n: the number of coins.
The next line has n integers x_1,x_2,\dots,x_n: the values of the coins.
Output
First print an integer k: the number of distinct money sums. After this, print all possible sums in increasing order.
Constraints

1 \le n \le 100
1 \le x_i \le 1000

Example
Input:
4
4 2 5 2

Output:
9
2 4 5 6 7 8 9 11 13
*/

/*
2^n - 1 are the total number of possible cases
, there might be duplicates , so we need to return only distinct number of outputs
*/
import java.util.*;
public class moneySums {
    public static void tab(int[] coins, TreeSet<Integer>set){
        int n = coins.length;
        int total = 0;
        for (int coin : coins) total += coin;
        
        boolean[][] dp = new boolean[n + 1][total + 1];
        
        // Same starting state as memo(coins, 0, 0, ...)
        dp[0][0] = true;

        // todo
        
    }
    public static void memo(int[] coins, int index,int sum, TreeSet<Integer>set, boolean[][]dp){
        if(index == coins.length){
            set.add(sum);
            return;
        }
        if(dp[index][sum])return;

        //take curr coin
        memo(coins,index + 1, sum + coins[index], set, dp);

        //ignore the curr coin
        memo(coins,index + 1, sum, set, dp);

        dp[index][sum] = true;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] coins = new int[n];
        for(int i = 0; i < n; i++){
            coins[i] = sc.nextInt();
        }
        boolean[][] dp = new boolean[n + 1][100001];
        TreeSet<Integer> set = new TreeSet<>();
        memo(coins,0,0,set,dp);
        set.remove(0);
        System.out.println(set.size());
        for(int comb: set){
            System.out.println(comb);
        }

    }
}
