#include <iostream>
#include <vector>
using namespace std;

class coinCombinations1 {
public:
    static const int mod = 1000000007;

    static int tab(const vector<int>& coins, int amount) {
        vector<int> states(amount + 1, 0);
        states[0] = 1;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                states[i] = (states[i] + states[i - coin]) % mod;
            }
        }

        return states[amount];
    }
};

int main() {
    int n, amount;
    cin >> n >> amount;

    vector<int> coins(n);
    for (int i = 0; i < n; i++) cin >> coins[i];

    cout << coinCombinations1::tab(coins, amount) << '\n';
    return 0;
}