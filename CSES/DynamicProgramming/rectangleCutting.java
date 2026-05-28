/*
Time limit: 1.00 s
Memory limit: 512 MB

Given an a \times b rectangle, your task is to cut it into squares. On each move you can select a rectangle and cut it into two rectangles in such a way that all side lengths remain integers. What is the minimum possible number of moves?
Input
The only input line has two integers a and b.
Output
Print one integer: the minimum number of moves.
Constraints

1 \le a,b \le 500

Example
Input:
3 5

Output:
3
*/



import java.util.Scanner;

public class rectangleCutting {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
            int length = sc.nextInt();
            int breath = sc.nextInt();

            int[][] states = new int[length+1][breath+1];

            states[1][1] = 0;

            for(int i=1;i<=length ; i++){
                for(int j=1;j<=breath ; j++){
                    if(i==j){
                        states[i][j] = 0;
                        continue;
                    }
                    int minCuts = Integer.MAX_VALUE;
                    for(int m = 1 ; m < i ; m++){
                        int firstPart = states[m][j];
                        int secondPart = states[i-m][j];
                        minCuts = Math.min(minCuts, firstPart + secondPart + 1);
                    }
                    for(int n=1;n<j;n++){
                        int firstPart = states[i][n];
                        int secondPart = states[i][j -n];
                        minCuts = Math.min(minCuts , firstPart + secondPart + 1);
                    }
                    states[i][j] = minCuts;
                }
            }
            System.out.println(states[length][breath]);
        }
    }

