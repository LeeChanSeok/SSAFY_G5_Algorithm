#include<iostream>
#include<queue>

using namespace std;

int tc, n, m, k, ans;

bool visited[100][100];
int map[100][100];
int dir[100][100];

int dc[4] = { 0, 0, -1, 1 };
int dr[4] = { -1, 1, 0, 0 };

struct Point {
	int y, x, c, d;
};

bool operator < (Point a, Point b) {
	return a.c < b.c;
}
priority_queue<Point> q;

bool edge(int y, int x) {
	return x == 0 || x == n - 1 || y == 0 || y == n - 1;
}

void opposite(int d, int r, int c) {
	if (d == 1)
		dir[r][c] = 2;
	else if (d == 2)
		dir[r][c] = 1;
	else if (d == 3)
		dir[r][c] = 4;
	else
		dir[r][c] = 3;
}

void simulation() {
	for (int i = 0; i < n; i++)	{
		for (int j = 0; j < n; j++)	{
			visited[i][j] = false;

			if (map[i][j] != 0)	{
				q.push({ i,j,map[i][j],dir[i][j] });
			}
		}
	}

	while (!q.empty())	{
		int y = q.top().y;
		int x = q.top().x;
		int d = q.top().d;
		int c = q.top().c;
		q.pop();
		int nr = y + dr[d - 1];
		int nc = x + dc[d - 1];


		if (!visited[y][x])	{
			map[y][x] = 0;
			dir[y][x] = 0;
		}


		if (edge(nr, nc)) {
			if (visited[nr][nc]) {
				map[nr][nc] += (c / 2);
			}
			else {
				map[nr][nc] = c / 2;
				opposite(d, nr, nc);
				visited[nr][nc] = true;
			}
		}
		else {
			if (visited[nr][nc]) {
				map[nr][nc] += c;
			}
			else {
				map[nr][nc] = c;
				dir[nr][nc] = d;
				visited[nr][nc] = true;
			}
		}
	}
}

int main(void) {
	cin >> tc;

	for (int tc = 1; t <= tc; t++) {

		cin >> n >> m >> k;

		ans = 0;

		for (int i = 0; i < n; i++)	{
			for (int j = 0; j < n; j++)	{
				map[i][j] = dir[i][j] = 0;
			}
		}

		int y, x, c, d;

		for (int i = 0; i < k; i++)	{
			cin >> y >> x >> c >> d;
			map[y][x] = c;
			dir[y][x] = d;
		}
		for (int i = 0; i < m; i++)	{
			simulation();
		}

		for (int i = 0; i < n; i++)	{
			for (int j = 0; j < n; j++)	{
				if (map[i][j] != 0)
					ans += map[i][j];
			}
		}

		cout << '#' << tc << ' ' << ans << '\n';
	}
}