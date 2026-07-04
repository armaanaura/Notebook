#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> buildAncestors(int n, vector<int>& parents) {
    int maxLevel = (int)(log2(n)) + 1;

    vector<vector<int>> ancestors(maxLevel, vector<int>(n + 1, 0));

    for (int i = 2; i < parents.size(); i++) {
        ancestors[0][i] = parents[i];
    }

    for (int level = 1; level < maxLevel; level++) {
        for (int node = 0; node <= n; node++) {
            int upperMid = ancestors[level - 1][node];
            ancestors[level][node] = ancestors[level - 1][upperMid];
        }
    }

    return ancestors;
}

int findKthAncestor(int node, int k, vector<vector<int>>& ancestors) {
    for (int level = 0; level < ancestors.size() && node != 0; level++) {
        if ((k & 1) == 1) {
            node = ancestors[level][node];
        }
        k >>= 1;
    }

    return node;
}

int lca(int first, int second, vector<vector<int>>& ancestors, vector<int>& depth) {
    if (depth[first] < depth[second]) {
        swap(first, second);
    }

    if (depth[first] != depth[second]) {
        first = findKthAncestor(first, depth[first] - depth[second], ancestors);
    }

    for (int level = ancestors.size() - 1; level >= 0; level--) {
        if (first == second) return first;

        if (ancestors[level][first] != ancestors[level][second]) {
            first = ancestors[level][first];
            second = ancestors[level][second];
        }
    }

    return ancestors[0][first];
}

vector<int> buildDepth(vector<int>& parents) {
    vector<int> depth(parents.size(), 0);

    depth[1] = 0;

    for (int node = 2; node < parents.size(); node++) {
        depth[node] = depth[parents[node]] + 1;
    }

    return depth;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, q;
    cin >> n >> q;

    vector<int> parents(n + 1);

    for (int p = 2; p <= n; p++) {
        cin >> parents[p];
    }

    vector<vector<int>> ancestors = buildAncestors(n, parents);
    vector<int> depth = buildDepth(parents);

    while (q--) {
        int first, second;
        cin >> first >> second;

        cout << lca(first, second, ancestors, depth) << '\n';
    }

    return 0;
}