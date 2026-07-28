/*
Flight Routes Check
        
Task
Statistics

    
    









addEventListener("DOMContentLoaded", function (e) {
    const mathElements = document.getElementsByClassName("math");
    const macros = {};
    for (let element of mathElements) {
        katex.render(element.textContent, element, {
            displayMode: element.classList.contains("math-display"),
            throwOnError: false,
            globalGroup: true,
            macros,
        });
    }
});


.katex .base:last-child {
    display: inline;
}


Time limit: 1.00 s
Memory limit: 512 MB

There are n cities and m flight connections. Your task is to check if you can travel from any city to any other city using the available flights.
Input
The first input line has two integers n and m: the number of cities and flights. The cities are numbered 1,2,\dots,n.
After this, there are m lines describing the flights. Each line has two integers a and b: there is a flight from city a to city b. All flights are one-way flights.
Output
Print "YES" if all routes are possible, and "NO" otherwise. In the latter case also print two cities a and b such that you cannot travel from city a to city b. If there are several possible solutions, you can print any of them.
Constraints

1 \le n \le 10^5
1 \le m \le 2 \cdot 10^5
1 \le a,b \le n

Example
Input:
4 5
1 2
2 3
3 1
1 4
3 4

Output:
NO
4 2
 */

import java.util.*;
public class flightRoutesCheck{
    public static void dfs(int curr,ArrayList<ArrayList<Integer>>graph,Stack<Integer>stack, boolean[]visited){
        if(visited[curr])return;
        visited[curr]=true;
        for(int next:graph.get(curr)){
            dfs(next,graph,stack,visited);
        }
        stack.push(curr);
    }
    public static void iterateSCC(int curr,ArrayList<ArrayList<Integer>>graph, boolean[]visited){
        if(visited[curr])return;
        visited[curr]=true;
        for(int next:graph.get(curr)){
            iterateSCC(next,graph,visited);
        }
        return;
    }
    public static int[] findSCC(ArrayList<ArrayList<Integer>>graph,ArrayList<ArrayList<Integer>>reverseGraph){
        Stack<Integer>stack = new Stack<>();
        boolean visited[] = new boolean[graph.size()];

        for(int i=1;i<graph.size();i++){
            if(visited[i]==false){
                dfs(i,graph,stack,visited);
            }
        }
        Arrays.fill(visited,false);
        int sccCount = 0;
        int a=0,b=0;
        while(stack.isEmpty()==false){
            int curr = stack.pop();
            if(visited[curr])continue;
            if(a==0){
                a=curr;
            }else if(b==0){
                b=curr;
            }
            iterateSCC(curr, reverseGraph, visited);
            sccCount++;
        }
        if(sccCount==1)return new int[]{0,0};
        return new int[]{b,a};

    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int numberOfCities = sc.nextInt();
        int numberOfFlights = sc.nextInt();
        ArrayList<ArrayList<Integer>>graph = new ArrayList<>();
        ArrayList<ArrayList<Integer>>reverseGraph = new ArrayList<>();
        for(int i=0;i<=numberOfCities;i++){
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());    
        }
        for(int i=0;i<numberOfFlights;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            reverseGraph.get(b).add(a);
        }
        int[]result = findSCC(graph,reverseGraph);
        if(result[0]==0 && result[1]==0){
            System.out.println("YES");
        }else{
            System.out.println("NO");
            System.out.println(result[0]+" "+result[1]);
        }
    }
}