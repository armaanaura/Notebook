/*
Time limit: 1.00 s
Memory limit: 512 MB

The edit distance between two strings is the minimum number of operations required to transform one string into the other.
The allowed operations are:

Add one character to the string.
Remove one character from the string.
Replace one character in the string.

For example, the edit distance between LOVE and MOVIE is 2, because you can first replace L with M, and then add I.
Your task is to calculate the edit distance between two strings.
Input
The first input line has a string that contains n characters between A–Z.
The second input line has a string that contains m characters between A–Z.
Output
Print one integer: the edit distance between the strings.
Constraints

1 \le n,m \le 5000

Example
Input:
LOVE
MOVIE

Output:
2
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class editDistance{

    public static int tab(char arr1[], char arr2[]){
        int states[][] = new int[arr1.length+1][arr2.length+1];
        for(int i=1;i<states.length;i++){
            for(int j=1;j<states[0].length;j++){
                if(arr1[i-1] == arr2[j-1]){
                    states[i][j] = states[i-1][j-1] + 1;
                }else{
                    states[i][j] = Math.max(states[i-1][j] , states[i][j-1]);
                }
            }
        }
        return states[states.length-1][states[0].length-1];
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        int lcs = tab(s1.toCharArray(), s2.toCharArray());
        int distanceToEdit = s1.length() + s2.length() - 2 * lcs;
        System.out.println(distanceToEdit);
    }
}


/*
LCS-based formula works only when allowed operations are insert and delete.

For insert/delete only:
answer = len(s1) + len(s2) - 2 * LCS

Reason:
characters in s1 not part of LCS must be deleted, and characters in s2 not part of LCS must be inserted.

For classic edit distance, replace is also allowed, so LCS is not enough.

Why max(len1, len2) - LCS is wrong:
it assumes all unmatched characters can be fixed optimally by replacement/insertion, but LCS only tells common characters in order; it does not capture exact alignment and replacement decisions.

Counterexample:
s1 = “ab”, s2 = “ba”
LCS = 1
max(2, 2) - 1 = 1
But actual edit distance = 2, because one operation cannot transform “ab” into “ba”.

Interview differentiation:
If operations are only insert/delete, use LCS formula.
If operations include replace, use edit distance DP:
dp[i][j] = min operations to convert first i chars of s1 to first j chars of s2.
At mismatch, take 1 + min(delete, insert, replace).
*/



