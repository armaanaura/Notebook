
/*
Your task is to count the number of ways to construct sum n by throwing a dice one or more times. Each throw produces an outcome between 1 and  6.
For example, if n=3, there are 4 ways:

1+1+1
1+2
2+1
3

Input
The only input line has an integer n.
Output
Print the number of ways modulo 10^9+7.
Constraints

1 <= n <= 10^6

Example
Input:
3

Output:
4
*/
import java.util.*;

public class diceCombinations {
    private static int states[];
    public static int tab(int n){
        states = new int[n+1];
        states[0]=1;
        for(int i=1;i<states.length;i++){
            int ans = 0;
            for(int j=i-1;j>=0&&j>=i-6;j--){
                ans = (ans+states[j])%1000000007;
            }
            states[i]=ans;
        }
        return states[n];
    }
    public static int memo(int n) {
        if (n == 0)
            return 1;
        if (n < 0)
            return 0;
        if (states[n] != -1)
            return states[n];
        int ans = 0;
        for (int i = 1; i <= 6; i++) {
            ans = (ans + memo(n - i)) % 1000000007;
        }
        return states[n] = ans;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // states = new int[n + 1];
        // Arrays.fill(states, -1);
        // System.out.println(memo(n));
        System.out.println(tab(n));
    }
}

/*
Stack overflow risk happens before memoization fully helps.
On the first evaluation, the recursion can still go from n down to 0, creating O(n) call depth.
After states are filled, future calls are shallow and fast, but the program may already have failed during that first deep chain.

One more subtle point

Even after some states are filled, if a not-yet-computed large state triggers another long unresolved chain, stack depth can still grow a lot. But in your specific recurrence, the first descent is the main danger.

So the clean answer is:

The stack issue mainly happens while states are still unresolved, especially during the first deep recursive descent. Once memo values are filled, later calls usually do not cause that problem.

In practice for Java

For Java, recursion depth in the range of a few thousand to maybe tens of thousands may work depending on environment.

But 10^6 definitely is not safe.

So for your problem:

n = 100 safe
n = 1000 usually safe
n = 10000 risky / often fails
n = 10^6 not realistic at all

These are not guarantees, just practical intuition.

So go for tabulation*********************************************************
*/