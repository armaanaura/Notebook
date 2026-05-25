/*
Time limit: 1.00 s
Memory limit: 512 MB

Your task is to build a tower whose width is 2 and height is n. You have an unlimited supply of blocks whose width and height are integers.
For example, here are some possible solutions for n=6:

Given n, how many different towers can you build? Mirrored and rotated towers are counted separately if they look different.
Input
The first input line contains an integer t: the number of tests.
After this, there are t lines, and each line contains an integer n: the height of the tower.
Output
For each test, print the number of towers modulo 10^9+7.
Constraints

1 \le t \le 100
1 \le n \le 10^6

Example
Input:
3
2
6
1337

Output:
8
2864
640403945
*/


import java.util.Scanner;

public class countingTowers {
    public static final int MOD = 1_000_000_007;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        int maxHeight = 0;
        int[] inputs = new int[t];

        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            maxHeight = Math.max(maxHeight, n);
            inputs[i] = n;
        }

        long[][] states = new long[maxHeight][2];

        states[0][0] = 1;
        states[0][1] = 1;

        for (int i = 1; i < maxHeight; i++) {
            states[i][0] = (4 * states[i - 1][0] + states[i - 1][1]) % MOD;
            states[i][1] = (states[i - 1][0] + 2 * states[i - 1][1]) % MOD;
        }

        StringBuilder sb = new StringBuilder();

        for (int query : inputs) {
            long ans = (states[query - 1][0] + states[query - 1][1]) % MOD;
            sb.append(ans).append('\n');
        }

        System.out.print(sb);
    }
}