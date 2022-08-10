#define MAX 10
#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>

using namespace std;

// array
int parent[MAX];
int map[11][11];
int newMap[11][11];

// direction
int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };

// queue
queue<pair<int, int>> q, qq;
vector<vector<pair<int, int>>> island(7);

int n, m;

struct edge {
	int st, end, distance;
};

struct compare {
	bool operator()(edge x, edge y) {
		return x.distance > y.distance;
	}
};

priority_queue<edge, vector<edge>, compare> pq;

int find(int x) {
	if (parent[x] == x)
		return x;

	return parent[x] = find(parent[x]);
}

void merge(int x, int y) {
	x = find(x);
	y = find(y);

	if (x == y)
		return;

	parent[y] = x;
}

bool isUnion(int x, int y) {
	x = find(x);
	y = find(y);

	if (x == y)
		return true;

	return false;
}

void bfs(int cnt) {
	while (!qq.empty()) {
		int x = qq.front().first;
		int y = qq.front().second;

		qq.pop();

		island[cnt].push_back(P(x, y));

		newMap[x][y] = cnt;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (newMap[nx][ny] || map[nx][ny] == 0) continue;
			if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			qq.push(P(nx, ny));
		}
	}
}

void makeBridge(P now, int num) {
	int x = now.first;
	int y = now.second;

	for (int i = 0; i < 4; i++) {
		int nx = x;
		int ny = y;

		int dis = 0;

		while (true) {
			nx = nx + dx[i];
			ny = ny + dy[i];

			if (nx < 0 || nx >= n || ny < 0 || ny >= m)
				break;
			if (newMap[nx][ny] == num) break;
			if (newMap[nx][ny] != newMap[x][y] && newMap[nx][ny] != 0) {
				if (dis > 1)
					pq.push({ num,newMap[nx][ny],dis });
				break;
			}
			dis++;
		}
	}
}

int main() {
	ios::sync_with_stdio(false); 
	cin.tie(NULL); 
	cout.tie(NULL);

	cin >> n >> m;

	for (int i = 0; i < MAX; i++)
		parent[i] = i;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> map[i][j];
			
			if (map[i][j] == 1)
				q.push(P(i, j));
		}
	}
	int cnt = 1;
	while (!q.empty()) {
		if (newMap[q.front().first][q.front().second]) {
			q.pop();
			continue;
		}
		qq.push(P(q.front().first, q.front().second));
		
		bfs(cnt);
		
		cnt++;
		
		q.pop();
	}

	for (int i = 1; i < island.size(); i++) {
		for (int j = 0; j < island[i].size(); j++) {
			makeBridge(island[i][j], i);
		}
	}
	int sum = 0;
	while (!pq.empty()) {
		if (!isUnion(pq.top().st, pq.top().end)) {
			sum += pq.top().distance;
			merge(pq.top().st, pq.top().end);
		}
		pq.pop();
	}
	for (int i = 2; i < cnt; i++) {
		if (!isUnion(1, i)) {
			cout << -1;
			return 0;
		}
	}

	cout << sum;
}
