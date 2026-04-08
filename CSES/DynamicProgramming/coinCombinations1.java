/*
Consider a money system consisting of n coins. Each coin has a positive integer value. Your task is to calculate the number of distinct ways you can produce a money sum x using the available coins.
For example, if the coins are \{2,3,5\} and the desired sum is 9, there are 8 ways:

2+2+5
2+5+2
5+2+2
3+3+3
2+2+2+3
2+2+3+2
2+3+2+2
3+2+2+2

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
8

*/

// NOTE : This code will not get submitted because it is in java... cpp solution is getting submitted


import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class coinCombinations1 {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }

    //  LOGIC STARTS HERE =========================================================================
    public static int mod = 1000000007;
    public static int[] states;
    public static int tab(int coins[], int amount){
        states = new int[amount+1];
        states[0]=1;
        for(int i=1;i<states.length;i++){
            for(int coin:coins){
                if(i-coin<0)continue;
                if(coin>i)break;
                states[i]=(states[i]+states[i-coin])%mod;
            }
        }
        return states[amount];
    }
    public static int memo(int coins[], int amount){
        if(amount==0)return 1;
        if(amount<0)return 0;
        if(states[amount]!=-1)return states[amount];
        int ans = 0;
        for(int coin:coins){
            ans = (ans+memo(coins,amount-coin))%mod;
        }

        return states[amount] = ans;
    }
    public static void main(String args[])throws Exception{
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int amount = sc.nextInt();
        int[] coins = new int[n];
        for(int i=0;i<coins.length;i++)coins[i]=sc.nextInt();
        Arrays.sort(coins);
        // states= new int[amount+1];
        // Arrays.fill(states,-1);
        // System.out.println(memo(coins,amount));
        System.out.println(tab(coins, amount));
    }
}
