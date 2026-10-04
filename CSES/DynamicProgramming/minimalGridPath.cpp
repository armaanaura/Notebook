#include <bits/stdc++.h>
using namespace std;

int N;

int encode(int i, int j){
    return i * N + j;
}

vector<int> decode(int key){
    return {key / N, key % N};
}

string bfs(vector<vector<char>>& grid){
    queue<int> queue;
    vector<bool> visited(N * N, false);

    queue.push(encode(0, 0));
    visited[encode(0, 0)] = true;

    string sb = "";
    sb += grid[0][0];

    while(queue.empty() == false){
        int queueSize = queue.size();

        vector<int> keys;
        char minChar = 'Z';

        for(int i = 0; i < queueSize; i++){
            vector<int> currIndices = decode(queue.front());
            queue.pop();

            int currI = currIndices[0];
            int currJ = currIndices[1];

            if(currI + 1 < N){
                int key = encode(currI + 1, currJ);
                char ch = grid[currI + 1][currJ];

                if(ch < minChar){
                    minChar = ch;
                    keys.clear();
                    keys.push_back(key);
                }else if(ch == minChar){
                    keys.push_back(key);
                }
            }

            if(currJ + 1 < N){
                int key = encode(currI, currJ + 1);
                char ch = grid[currI][currJ + 1];

                if(ch < minChar){
                    minChar = ch;
                    keys.clear();
                    keys.push_back(key);
                }else if(ch == minChar){
                    keys.push_back(key);
                }
            }
        }

        if(keys.empty()) break;

        sb += minChar;

        for(int key : keys){
            if(visited[key] == false){
                visited[key] = true;
                queue.push(key);
            }
        }
    }

    return sb;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    N = n;

    vector<vector<char>> grid(n, vector<char>(n));

    for(int i = 0; i < n; i++){
        string s;
        cin >> s;

        for(int j = 0; j < n; j++){
            grid[i][j] = s[j];
        }
    }

    string answer = bfs(grid);

    cout << answer << '\n';

    return 0;
}