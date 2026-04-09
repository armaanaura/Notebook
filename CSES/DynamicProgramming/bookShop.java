/*
You are in a book shop which sells n different books. You know the price and number of pages of each book.
You have decided that the total price of your purchases will be at most x. What is the maximum number of pages you can buy? You can buy each book at most once.
Input
The first input line contains two integers n and x: the number of books and the maximum total price.
The next line contains n integers h_1,h_2,\ldots,h_n: the price of each book.
The last line contains n integers s_1,s_2,\ldots,s_n: the number of pages of each book.
Output
Print one integer: the maximum number of pages.
Constraints

1 \le n \le 1000
1 \le x \le 10^5
1 \le h_i, s_i \le 1000

Example
Input:
4 10
4 8 5 3
5 12 8 1

Output:
13

Explanation: You can buy books 1 and 3. Their price is 4+5=9 and the number of pages is 5+8=13.
*/


import java.util.Arrays;
import java.util.Scanner;
public class bookShop {
    public static int[][] states;
    public static int tab(int prices[], int pages[], int budget){
        states = new int[prices.length+1][budget+1];
        for(int i=prices.length-1;i>=0;i--){
            for(int j=0;j<=budget;j++){
                if(j>=prices[i]){
                    int buy = pages[i] + states[i+1][j-prices[i]];
                    int ignore = states[i+1][j];
                    states[i][j] = Math.max(buy,ignore);
                }else{
                    states[i][j]=states[i+1][j];
                }
            }
        }
        return states[0][budget];
    }
    public static int memo(int prices[], int pages[], int index , int budget){
        if(index==prices.length)return 0;
        if(states[index][budget]!=-1)return states[index][budget];

        int ans = 0;
        if(budget>=prices[index]){
            int buy = pages[index] + memo(prices,pages,index+1,budget-prices[index]);
            int ignore = memo(prices,pages,index+1,budget);
            ans = Math.max(buy,ignore);
        }else{
            ans = memo(prices,pages,index+1,budget);
        }
        return states[index][budget] = ans;
    }


    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int budget = sc.nextInt();
        int prices[] = new int[n];
        int pages[] = new int[n];
        for(int i=0;i<n;i++){
            prices[i]=sc.nextInt();
        }
        for(int i=0;i<n;i++){
            pages[i]=sc.nextInt();
        }
        // states = new int[n][budget+1];
        // for(int[] temp:states)Arrays.fill(temp,-1);
        // System.out.println(memo(prices, pages, 0, budget));
        System.out.println(tab(prices, pages, budget));
    }
}
