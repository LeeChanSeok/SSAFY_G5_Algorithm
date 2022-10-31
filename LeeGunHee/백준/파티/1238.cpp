#include<iostream>
#include<queue>
#include<vector>
#include<algorithm>
#include<memory.h>
using namespace std;
 
struct info {
    int end;
    int time;
};
 
vector<info> v[1001];
int N, M, X;
int ans;
int total_time[1001];
int temp_ans;
 
void printt() {    
    for (int i = 0; i < N; i++) {
        cout << total_time[i] << " ";
    }
    cout << '\n'l;
}
 
void dijkstra(int s,int cur) {
    for (int i = 0; i < N; i++) 
	total_time[i] = 999999999;

    total_time[s] = 0;
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push(make_pair(0,s));

    while (!pq.empty()) {
        int temp_time = pq.top().first;
        int pos = pq.top().second;
        pq.pop();
        
 
        if (total_time[pos] < temp_time) continue;
 
        for (int i = 0; i < v[pos].size(); i++) {
            int des = v[pos][i].end;
            int next_time = v[pos][i].time + temp_time;

            if (total_time[des] > next_time) {
                total_time[des] = next_time;
                pq.push(make_pair(next_time,des));
                
            }
        }
        int end = pq.size();
    }
    
    if (s != X-1) 
    	temp_ans += total_time[X - 1]; 
    else 
    	temp_ans += total_time[cur]; //X->i
}
 
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0); 
    cout.tie(0);

    cin >> N >> M >> X;

    for (int i = 0; i < M; i++) {
        int s, e, t;
        cin >> s >> e >> t;

        info j;

        j.end = e-1; 
        j.time = t;

        v[s-1].push_back(j);
    }
    ans = -1;

    for (int i = 0; i < N; i++) {
        dijkstra(i,i);
        dijkstra(X-1,i);

        ans = max(ans, temp_ans);

        temp_ans = 0;
        
    }
    cout << ans;
}
