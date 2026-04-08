/*
Time limit: 1.00 s
Memory limit: 512 MB

You are given an integer n. On each step, you may subtract one of the digits from the number.
How many steps are required to make the number equal to 0?
Input
The only input line has an integer n.
Output
Print one integer: the minimum number of steps.
Constraints

1 \le n \le 10^6

Example
Input:
27

Output:
5

Explanation: An optimal solution is 27 \rightarrow 20 \rightarrow 18 \rightarrow 10 \rightarrow 9 \rightarrow 0.

*/


import java.util.Scanner;
import java.util.Arrays;
public class removingDigits {
    public static int tab(int number){
        int states[] = new int[number+1];
        Arrays.fill(states,1000001);
        states[0]=0;
        for(int i=1;i<states.length;i++){
            int curr = i;
            while(curr>0){
                int digit = curr%10;
                states[i] = Math.min(states[i],states[i-digit]+1);
                curr/=10;
            }
        }
        return states[number];
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int ans = tab(number);
        System.out.println(ans);
    }
}
