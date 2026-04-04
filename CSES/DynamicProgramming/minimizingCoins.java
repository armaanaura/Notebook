/*
Consider a money system consisting of n coins. Each coin has a positive integer value. Your task is to produce a sum of money x using the available coins in such a way that the number of coins is minimal.
For example, if the coins are \{1,5,7\} and the desired sum is 11, an optimal solution is 5+5+1 which requires 3 coins.
Input
The first input line has two integers n and x: the number of coins and the desired sum of money.
The second line has n distinct integers c_1,c_2,\dots,c_n: the value of each coin.
Output
Print one integer: the minimum number of coins. If it is not possible to produce the desired sum, print -1.
Constraints

1 \le n \le 100
1 \le x \le 10^6
1 \le c_i \le 10^6

Example
Input:
3 11
1 5 7

Output:
3
*/
import java.util.*;
public class minimizingCoins {
    public static int states[];
    public static int tab(int coins[], int amount){
        states = new int[amount+1];
        Arrays.fill(states,1000001);;
        states[0]=0;
        for(int i=1;i<=amount;i++){
            for(int j=0;j<coins.length;j++){
                int coin = coins[j];
                if(i-coin<0)continue;
                states[i]=Math.min(states[i],states[i-coin]+1);
            }
        }
        return states[amount];
    }
    public static int memo(int[] coins, int amount){
        if(amount==0)return 0;
        if(amount<0)return 1000001;
        if(states[amount]!=-1)return states[amount];
        int min = 1000001;
        for(int coin: coins){
            min = Math.min(min, memo(coins, amount-coin));
        }
        if(min<Integer.MAX_VALUE)min += 1;
        return states[amount] = min;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int amount = sc.nextInt();
        int coins[] = new int[n];
        // states = new int[amount+1];
        // Arrays.fill(states,-1);
        // int ans = memo(coins, amount);
        // System.out.println(ans>=1000001||ans==Integer.MAX_VALUE?-1:ans);
        for(int i=0;i<n;i++)coins[i]=sc.nextInt();
        int ans = tab(coins, amount);
        System.out.println(ans>=1000001||ans==Integer.MAX_VALUE?-1:ans);
    }
}
