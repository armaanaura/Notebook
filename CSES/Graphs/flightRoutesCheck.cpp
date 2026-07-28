#include <bits/stdc++.h>
using namespace std;

void dfs(
    int curr,
    const vector<vector<int>>& graph,
    stack<int>& order,
    vector<bool>& visited
) {
    if (visited[curr]) return;

    visited[curr] = true;

    for (int next : graph[curr]) {
        dfs(next, graph, order, visited);
    }

    order.push(curr);
}

void iterateSCC(
    int curr,
    const vector<vector<int>>& graph,
    vector<bool>& visited
) {
    if (visited[curr]) return;

    visited[curr] = true;

    for (int next : graph[curr]) {
        iterateSCC(next, graph, visited);
    }
}

pair<int, int> findSCC(
    const vector<vector<int>>& graph,
    const vector<vector<int>>& reverseGraph
) {
    int n = graph.size() - 1;

    stack<int> order;
    vector<bool> visited(n + 1, false);

    for (int city = 1; city <= n; city++) {
        if (!visited[city]) {
            dfs(city, graph, order, visited);
        }
    }

    fill(visited.begin(), visited.end(), false);

    int sccCount = 0;
    int first = 0;
    int second = 0;

    while (!order.empty()) {
        int curr = order.top();
        order.pop();

        if (visited[curr]) continue;

        if (first == 0) {
            first = curr;
        } else if (second == 0) {
            second = curr;
        }

        iterateSCC(curr, reverseGraph, visited);
        sccCount++;
    }

    if (sccCount == 1) {
        return {0, 0};
    }

    return {second, first};
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int numberOfCities, numberOfFlights;
    cin >> numberOfCities >> numberOfFlights;

    vector<vector<int>> graph(numberOfCities + 1);
    vector<vector<int>> reverseGraph(numberOfCities + 1);

    for (int i = 0; i < numberOfFlights; i++) {
        int a, b;
        cin >> a >> b;

        graph[a].push_back(b);
        reverseGraph[b].push_back(a);
    }

    pair<int, int> result = findSCC(graph, reverseGraph);

    if (result.first == 0 && result.second == 0) {
        cout << "YES\n";
    } else {
        cout << "NO\n";
        cout << result.first << " " << result.second << '\n';
    }

    return 0;
}