#include <string>
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <cmath>
#include <cstring>

// std:: 
using namespace std;

// map
int arr[11][11];
int p[6] = { 0, };

// 정답
int answer = 987654321;

// 2 - Matrix range check
bool p_check(int row, int col, int size) {
	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
			if (arr[row + i][col + j] == 0) {
				return false;
			}
		}
	}
	return true;
}

void DFS(int row, int col, int c) {
	
	// 1 search
	while (arr[row][col] == 0) {
		col++;
		if (col >= 10) {
			row++;
			if (row >= 10) {
				answer = min(answer, c);
				return;
			}
			col = 0;
		}
	}

	// 이미 최소값보다 크다면 돌 필요 없음
	if (c >= answer) {
		return;
	}

	for (int i = 5; i > 0; i--) {
		// 종이가 밖으로 나감
		if (row + i > 10 || col + i > 10) {
			continue;
		}

		// 종이 최대개수 초과
		if (p[i] >= 5) {
			continue;;
		}

		// 종이 붙이기 가능
		if (p_check(row, col, i)) {
			p[i]++;

			for (int a = 0; a < i; a++) {
				for (int b = 0; b < i; b++) {
					arr[row + a][col +b] = 0;
				}
			}

			DFS(row, col, c + 1);

			p[i]--;

			for (int a = 0; a < i; a++) {
				for (int b = 0; b < i; b++) {
					arr[row + a][col + b] = 1;
				}
			}
		}
	}
}

int main() {
	//ios::sync_with_stdio(false);
	//cin.tie(NULL);
	//cout.tie(NULL);

	for (int i = 0; i < 10; i++) {
		for (int j = 0; j < 10; j++) {
			cin >> arr[i][j];
		}
	}

	DFS(0, 0, 0);

	if (answer == 987654321)
		answer = -1;

	cout << answer << endl;

	// 정상 종료
	return 0;
}
