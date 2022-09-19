#include <string>
#include <iostream>
#include <vector>
#include <algorithm>

// std:: 접두사
using namespace std;

// 맵, 배열크기
int N;
int arr[16][16];

// 행 열 이동
int dr[3] = {0, 1, 1};
int dc[3] = {1, 0, 1};

//
int answer = 0;

// range check

bool range_check(int r, int c) {
	if (r >= 0 && r < N && c >= 0 && c < N && arr[r][c] == 0)
		return true;
	else
		return false;
}


// DFS
void DFS(int row, int col, int dir) {
	//cout << "DFS ing " << row << " " << col << '\n';
	// 수식 길이만큼 돌면 탈출

	// 목적지 도착
	if (row == N - 1 && col == N - 1) {
		//cout << "DFS end" << row << " " << col << '\n';
		answer++;
		return;
	}

	// 탐색 시작
	for (int i = 0; i < 3; i++) {
		//cout << "DFS loof " << i << '\n';
		// 세로 방향이면 가로방향 이동 X, 반대도 마찬가지
		if ((dir == 1 && i == 0) || (dir == 0 && i == 1))
			continue;
	
		// 다음 좌표
		int nrow = row + dr[i];
		int ncol = col + dc[i];

		// 배열 범위 안인지
		if (!range_check(nrow, ncol))
			continue;
		
		//
		if (i == 2 && (arr[row + 1][col] == 1 || arr[row][col + 1] == 1))
			continue;

		// 정상 탐색
		DFS(nrow, ncol , i);
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	DFS(0, 1, 0);

	cout << answer << '\n';

	// 정상 종료
	return 0;
}
