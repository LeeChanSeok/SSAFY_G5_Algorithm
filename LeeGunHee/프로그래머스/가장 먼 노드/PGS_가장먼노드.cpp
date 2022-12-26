#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int dp[20001];

vector<int> v[50001];
int maxi;

void bfs(int start) {
    queue<int> q;
    q.push(start);

    while(!q.empty()) {
        int current = q.front();
        q.pop();

        for(int i = 0; i < v[current].size(); i++){
            if(dp[v[current][i]] == 0 && v[current][i] != 1){
                dp[v[current][i]] = dp[current] + 1;
                q.push(v[current][i]);
                maxi = max(maxi, dp[v[current][i]]);
                }
        }
    }


}

int solution(int n, vector<vector<int>> edge) {
    int answer = 0;
    for(int i = 0; i < edge.size(); i++) {
        v[edge[i][0]].push_back(edge[i][1]);
        v[edge[i][1]].push_back(edge[i][0]);
    }

    bfs(1);

    for(int i = 1; i <= n; i++){
        if(maxi == dp[i])
            answer++;
    }

    return answer;
}