#include <iostream>
#include <algorithm>
#include <string>
#include <queue>
#include <cstring>

using namespace std;

// 1 = ¼­ 
// 2 = ºÏ 
// 4 = µ¿ 
// 8 = ³²
int dr[4] = { 0, -1, 0, 1 };
int dc[4] = { -1, 0, 1, 0 };

int R, C;

int map[50][50];
bool visited[50][50];

int room_cnt = 0;
int room_large = 0;
int room_wallbreak_large = 0;

int BFS(int row, int col);
int range_check(int row, int col);

int main() {
	cin >> C >> R;

	for (int r = 0; r < R; r++) {
		for (int c = 0; c < C; c++) {
			cin >> map[r][c];
		}
	}

	for (int r = 0; r < R; r++) {
		for (int c = 0; c < C; c++) {
			if (!visited[r][c]) {		
				int room_size = BFS(r, c);

				if (room_large < room_size) {
					room_large = room_size;
				}
				room_cnt++;
			}
		}
	}

	for (int r = 0; r < R; r++) {
		for (int c = 0; c < C; c++) {
			for (int wall = 1; wall <= 8; wall *= 2) {
				memset(visited, false, sizeof(visited));
				
				map[r][c] -= wall;
				int c_size = BFS(r, c);
				map[r][c] += wall;

				room_wallbreak_large = c_size > room_wallbreak_large ? c_size : room_wallbreak_large;
			}
		}
	}

	cout << room_cnt << "\n" << room_large << "\n" << room_wallbreak_large << '\n';

	return 0;
}

int BFS(int row, int col) {
	queue<pair<int, int>> q;

	int size = 1;

	q.push({ row, col });
	visited[row][col] = true;

	while (!q.empty()) {
		int cr = q.front().first;
		int cc = q.front().second;

		int wall = 1;

		q.pop();

		for (int i = 0; i < 4; i++) {
			if ((map[cr][cc] & wall) != wall) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];

				if (!range_check(nr, nc)) {
					continue;
				}

				if (!visited[nr][nc]) {
					visited[nr][nc] = true;
					q.push({ nr, nc });
					size++;
				}
			}
			wall = wall * 2;
		}
	}
	return size;
}

int range_check(int row, int col) {
	if (row < 0 || R <= row || col < 0 || C <= col) {
		return false;
	}

	return true;
}