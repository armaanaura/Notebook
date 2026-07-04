/*
Time limit: 1.00 s
Memory limit: 512 MB

A company has n employees, who form a tree hierarchy where each employee has a boss, except for the general director.
Your task is to process q queries of the form: who is the lowest common boss of employees a and b in the hierarchy?
Input
The first input line has two integers n and q: the number of employees and queries. The employees are numbered 1,2,\dots,n, and employee 1 is the general director.
The next line has n-1 integers e_2,e_3,\dots,e_n: for each employee 2,3,\dots,n their boss.
Finally, there are q lines describing the queries. Each line has two integers a and b: who is the lowest common boss of employees a and b?
Output
Print the answer for each query.
Constraints

1 \le n,q \le 2 \cdot 10^5
1 \le e_i \le i-1
1 \le a,b \le n

Example
Input:
5 3
1 1 3 3
4 5
2 5
1 4

Output:
3
1
1
*/

import java.util.Scanner;
public class companyQueries2 {
    public static int[][] buildAncestors(int n, int[] parents){
        int maxLevel = (int)(Math.floor(Math.log(n)/Math.log(2)))+1;
        int [][]ancestors = new int[maxLevel][n+1];
        for(int i=2;i<parents.length;i++)ancestors[0][i]=parents[i];

        for(int level = 1 ; level<maxLevel;level++){
            for(int node = 0 ; node <= n ; node++){
                int upperMid = ancestors[level-1][node];
                ancestors[level][node]= ancestors[level-1][upperMid];
            }
        }
        return ancestors;
    }
    public static int findKthAncestor(int node, int k, int[][] ancestors){
        for(int level = 0 ; level<ancestors.length&&node!=0;level++){
            if((k&1)==1){
                node = ancestors[level][node];
            }
            k>>=1;
        }
        return node;
    }
    public static int lca(int first, int second, int[][]ancestors, int[] depth){
        if(depth[first]<depth[second]){
            int temp = first;
            first = second;
            second = temp;
        }
        if(depth[first]!=depth[second]){
            first = findKthAncestor(first, depth[first]-depth[second], ancestors);
        }

        // now , both the nodes are on same level

        for(int level = ancestors.length-1;level>=0;level--){
            if(first==second)return first;
            if(ancestors[level][first]!=ancestors[level][second]){
                first = ancestors[level][first];
                second = ancestors[level][second];
            }
        }
        return ancestors[0][first];

    }
    public static int[] buildDepth(int[] parents){
        int[]depth = new int[parents.length];
        depth[1] = 0;

        for (int node = 2; node < parents.length; node++) {
            depth[node] = depth[parents[node]] + 1;
        }
        return depth;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] parents = new int[n+1];
        for(int p=2;p<=n;p++)parents[p]=sc.nextInt();

        int[][] ancestors = buildAncestors(n, parents);

        int depth[] = buildDepth(parents);

        for(int i=0;i<q;i++)System.out.println(lca(sc.nextInt(),sc.nextInt(),ancestors,depth));
    }
}
