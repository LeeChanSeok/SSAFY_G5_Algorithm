#include <iostream>
#include <vector>
#include <cstring>
#include <queue>
using namespace std;

typedef struct a {
	int y;
	int x;
}pos;

int n, m, f;
int arr[21][21] = { 0, };
int visited[21][21];
pos taxi;
vector <pos> people;
vector <pos> destination;

int y_ar[4] = { 0,1,0,-1 };
int x_ar[4] = { 1,0,-1,0 };

void bfs(int y, int x) {
	queue <pair<int, int>> que;
	que.push(make_pair(y, x));
	visited[y][x] = 1; // -1 해야함 

	while (!que.empty()) {
		int cy = que.front().first;
		int cx = que.front().second;
		que.pop();

		for (int i = 0; i < 4; i++) {
			int ny = cy + y_ar[i];
			int nx = cx + x_ar[i];

			if (ny >= 1 && ny <= n && nx >= 1 && nx <= n) {
				if (arr[ny][nx] == 0 && visited[ny][nx] == 0) {
					que.push(make_pair(ny, nx));
					visited[ny][nx] = visited[cy][cx] + 1;
				}
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	cin >> n >> m >> f;
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			cin >> arr[i][j];
	cin >> taxi.y >> taxi.x;
	for (int i = 0; i < m; i++) {
		int t1, t2, t3, t4;
		cin >> t1 >> t2 >> t3 >> t4;
		people.push_back({ t1,t2 });
		destination.push_back({ t3,t4 });
	}

	for (int k = 0; k < m; k++) {
		//1. 이동 거리 측정
		memset(visited, 0, sizeof(visited));
		bfs(taxi.y, taxi.x);
		/*
		for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++)
		cout << visited[i][j] << ' ';
		cout << endl;
		}*/

		//2. 가장 가까운 사람 선택
		int dis = 1000000000;
		int ny = 0, nx = 0, index = -1;
		for (int i = 0; i < people.size(); i++) {
			int y = people[i].y;
			int x = people[i].x;
			if (visited[y][x] < dis) {
				ny = y;
				nx = x;
				index = i;
				dis = visited[y][x];
			}
			else if (visited[y][x] == dis) {
				if (ny > y) {
					ny = y;
					nx = x;
					index = i;
				}
				else if (nx > x && ny == y) {
					ny = y;
					nx = x;
					index = i;
				}
			}
		}
		people.erase(people.begin() + index);
		taxi.y = ny, taxi.x = nx;
		f -= (visited[ny][nx] - 1);
		if (visited[ny][nx] == 0) {
			f = -1;
			break;
		}
		if (f <= 0)
			break;
		//cout << "y " << ny << "x " << nx << "i " << index << endl;

		//3. 목적지까지 거리 도출
		memset(visited, 0, sizeof(visited));
		bfs(taxi.y, taxi.x);
		//4. 목적지로 이동 
		int dy = destination[index].y;
		int dx = destination[index].x;
		if (visited[dy][dx] == 0) {
			f = -1;
			break;
		}
		taxi.y = dy, taxi.x = dx;
		destination.erase(destination.begin() + index);
		f -= (visited[dy][dx] - 1);
		if (f < 0)
			break;
		f += ((visited[dy][dx] - 1) * 2);
	}


	if (f <= 0)
		cout << -1 << endl;
	else
		cout << f << endl;
	return 0;
}