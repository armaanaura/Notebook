/*
Consider a money system consisting of n coins. Each coin has a positive integer value. Your task is to calculate the number of distinct ordered ways you can produce a money sum x using the available coins.
For example, if the coins are \{2,3,5\} and the desired sum is 9, there are 3 ways:

2+2+5
3+3+3
2+2+2+3

Input
The first input line has two integers n and x: the number of coins and the desired sum of money.
The second line has n distinct integers c_1,c_2,\dots,c_n: the value of each coin.
Output
Print one integer: the number of ways modulo 10^9+7.
Constraints

1 \le n \le 100
1 \le x \le 10^6
1 \le c_i \le 10^6

Example
Input:
3 9
2 3 5

Output:
3
*/
import java.util.Arrays;
import java.util.Scanner;
public class coinCombinations2 {
    public static int mod = 1000000007;
    public static int[][] states;
    public static int tab(int coins[], int amount){
        states = new int[amount+1][coins.length+1];
        Arrays.fill(states[0],1);
        for(int i=1;i<states.length;i++){
            for(int j=coins.length-1;j>=0;j--){
                int take = 0;
                if(i>=coins[j])take = states[i-coins[j]][j];
                int next = states[i][j+1];
                states[i][j] = (take+next)%mod;
            }
        }
        return states[amount][0];
    }
    public static int tabOptimized(int coins[], int amount){
        /*
        Why 1D DP is fragile

    In 1D DP, you reduce the state to just:
    dp[a]
    Now the coin index is not stored explicitly anymore.
    So where did that information go?
    It is now encoded only through loop order.
    That is the key.
    My Takeaway: if we are removing one dimension, that dimension should come in outer for loop
        */
        int [] states1d = new int[amount+1];
        states1d[0]=1;
        for(int coin:coins){
            for(int i=1;i<states1d.length;i++){
                if(i-coin<0)continue;
                states1d[i]=(states1d[i]+states1d[i-coin])%mod;
            }
        }
        return states1d[amount];
    }
    public static int memo(int coins[], int amount, int index){
        if(amount==0)return 1;
        if(index>=coins.length || amount<0)return 0;
        
        if( states[amount][index]!=-1)return  states[amount][index];

        int take = memo(coins,amount-coins[index],index);
        int next = memo(coins,amount,index+1);

        return states[amount][index]=(take+next)%mod;

    }
    public static void main(String args[])throws Exception{
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int amount = sc.nextInt();
        int[] coins = new int[n];
        for(int i=0;i<coins.length;i++)coins[i]=sc.nextInt();
        // Arrays.sort(coins);
        // states= new int[amount+1][coins.length];
        // for(var temp:states)Arrays.fill(temp,-1);
        // System.out.println(memo(coins,amount,0));
        // System.out.println(tab(coins, amount));
        System.out.println(tabOptimized(coins, amount));
    }
}
