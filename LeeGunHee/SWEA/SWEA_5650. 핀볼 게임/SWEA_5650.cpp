#include <iostream>
#include <algorithm>
#include <cstring>

using namespace std;

int map[100][100];

int arr_r, arr_c;

int dr[] = { -1, 0, 1, 0 };
int dc[] = { 0, 1, 0, -1 };

int N;
int answer;
int result;

int search(int row, int col, int dir);
int isInside(int row, int col);
int change_dir(int block, int dir);

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		result = 0;

		memset(map, -2, sizeof(map));

		cin >> N;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				cin >> map[row][col];
			}
		}

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (map[row][col] == 0) {
					for (int i = 0; i < 4; i++) {
						int cmp = search(row, col, i);
						result = max(cmp, result);
					}
				}
			}
		}

		cout << "#" << tc << " " << result << '\n';
	}
	return 0;
}

int search(int start_row, int start_col, int dir) {
	arr_r = start_row;
	arr_c = start_col;

	int row = start_row;
	int col = start_col;

	answer = 0;
	
	while (1) {

		row += dr[dir];
		col += dc[dir];

		//cout << row <<  " " << col << '\n';

		// 벽
		if (!isInside(row, col)) {
			if (dir == 0) {
				dir = 2;
			}
			else if (dir == 1) {
				dir = 3;
			}
			else if (dir == 2) {
				dir = 0;
			}
			else if (dir == 3) {
				dir = 1;
			}
			answer++;
		}

		// 빈칸
		else if (map[row][col] == 0) {
			if (row == arr_r && col == arr_c) {
				//cout << "Start in" << '\n';
				return answer;
			}
		}

		// 블랙홀
		else if (map[row][col] == -1) {
			//cout << "Hole in" << '\n';
			return answer;
		}
		
		// 블록
		else if (0 < map[row][col] && map[row][col] < 6) {
			dir = change_dir(map[row][col], dir);
			answer++;
		}
		
		// 웜홀
		else if (map[row][col] < 11 && 5 < map[row][col]) {
			bool flag = false;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == map[row][col] && !(row == r && col == c)) {
						row = r; col = c;
						flag = true;
						break;
					}
				}
				if (flag) {
					break;
				}
			}
		}
	}
}

int isInside(int row, int col) {
	if (row < 0 || N <= row || col < 0 || N <= col) {
		return false;
	}
	return true;
}

int change_dir(int block, int dir) {
	if (block == 1) {
		if (dir == 0) {
			return 2;
		}
		else if (dir == 1) {
			return 3;
		}
		else if (dir == 2) {
			return 1;
		}
		else if (dir == 3) {
			return 0;
		}
	}
	else if (block == 2) {
		if (dir == 0) {
			return 1;
		}
		else if (dir == 1) {
			return 3;
		}
		else if (dir == 2) {
			return 0;
		}
		else if (dir == 3) {
			return 2;
		}
	}
	else if (block == 3) {
		if (dir == 0) {
			return 3;
		}
		else if (dir == 1) {
			return 2;
		}
		else if (dir == 2) {
			return 0;
		}
		else if (dir == 3) {
			return 1;
		}
	}
	else if (block == 4) {
		if (dir == 0) {
			return 2;
		}
		else if (dir == 1) {
			return 0;
		}
		else if (dir == 2) {
			return 3;
		}
		else if (dir == 3) {
			return 1 ;
		}
	}
	else if (block == 5) {
		if (dir == 0) {
			return 2;
		}
		else if (dir == 1) {
			return 3;
		}
		else if (dir == 2) {
			return 0;
		}
		else if (dir == 3) {
			return 1;
		}
	}
}
