#include <bits/stdc++.h>
using namespace std;

int main() {
    int N; cin >> N;
    vector<pair<int,int>> V(N);
    for (auto &[x, y] : V) cin >> x >> y;
    
    stack<int> S;
    int cnt = 0;
    for (auto &[_, y] : V) {
        while(S.size() && S.top() > y) S.pop();
        if (y && (S.empty() || S.top() < y)) {
            S.push(y);
            cnt+=1;
        }
    }
    cout << cnt << '\n';
    return 0;
}