#include <iostream>
#include <algorithm>
#include <cstring>
#include <math.h>

using namespace std;

int D, W, K;
int answer;

int map[13][20];
int tmp[13][20];

int visited[13];

/// Function
// 확인
bool check(int arr[][20]);
// 조합
void DFS(int start, int depth);
// 카피맵
void change(int idx, int val, bool flag);

// 메인 함수
int main() {
	int T;

	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		memset(map, 0, sizeof(map));
		memset(tmp, 0, sizeof(tmp));
		memset(visited, false, sizeof(visited));
		answer = 1e9;

		cin >> D >> W >> K;

		for (int row = 0; row < D; row++) {
			for (int col = 0; col < W; col++) {
				cin >> map[row][col];
				tmp[row][col] = map[row][col];
			}
		}

		if (check(map) || K == 1) {
			answer = 0;
		}
		else {
			DFS(0, 0);
		}

		cout << "#" << tc << " " << answer << '\n';
	}
}

bool check(int arr[][20]) {
	int A, B;

	for (int col = 0; col < W; col++) {
		int A = 0, B = 0;
		bool check = false;

		for (int row = 0; row < D; row++) {
			if (arr[row][col] == 0) {
				A++;
				B = 0;
			}
			else {
				B++;
				A = 0;
			}

			if (A == K || B == K) {
				check = true;
				break;
			}

		}
		if (!check) {
			return false;
		}
	}
	return true;
}

void change(int idx, int val, bool flag) {
	if (flag) {
		for (int col = 0; col < W; col++) {
			tmp[idx][col] = val;
		}
	}
	else {
		for (int col = 0; col < W; col++) {
			tmp[idx][col] = map[idx][col];
		}
	}
}

void DFS(int start, int depth) {
	if (K < depth || answer <= depth) {
		return;
	}

	if (check(tmp)) {
		answer = min(answer, depth);
		return;
	}

	for (int i = start; i < D; i++) {
		if (visited[i])
			continue;

		visited[i] = true;
		for (int val = 0; val < 2; val++) {
			change(i, val, true);
			DFS(i, depth + 1);
			change(i, val, false);
		}
		visited[i] = false;
	}
}

