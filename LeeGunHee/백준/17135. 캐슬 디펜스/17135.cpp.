#include <string>
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <cmath>
#include <cstring>

// std:: 접두사
using namespace std;

// 맵, 배열 크기, 타겟 지정 방문 배열, 타겟 체크 배열
int N, M, D;
int map[17][17] = { 0, };
bool visit[16][16] = { false, };
bool attack_check[16][16] = { false, };
queue<pair<int, int>> q;

// 행 열 이동
int dr[3] = { 0, -1, 0 }; 
int dc[3] = { -1, 0, 1 };

// 정답
int answer = 0;

bool range_check(int row, int col) {
	if (row >= 1 && row <= N && col <= M && col >= 1)
		return true;
	else
		return false;
}

// BFS
void BFS(int a, int b, int c) {
		int map_copy[17][17];
		bool check[16][16];
		bool visit[16][16];
		int archer[3] = { a, b, c }; // 현재 궁수 위치 (x축 좌표)
		int pos = N + 1; // 현재 궁수 위치 (y축 좌표)
		int kill = 0;

		// 맵 복사 및 체크 표시 초기화
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= M; j++)
			{
				map_copy[i][j] = map[i][j];
				check[i][j] = false;
			}


		while (pos > 1)	{
			for (int i = 0; i < 3; i++)	{

				for (int j = 1; j <= N; j++) {
					for (int k = 1; k <= M; k++) {
						visit[j][k] = false;
					}
				}

				queue<pair<int, int>> q; 
				int init_x = archer[i];
				int init_y = pos - 1;

				q.push({ init_x, init_y });

				while (!q.empty()) {
					int now_x = q.front().first;
					int now_y = q.front().second;
					
					q.pop();

					visit[now_y][now_x] = true;

					if (map_copy[now_y][now_x] == 1) {
						check[now_y][now_x] = true;
						break;
					}

					for (int j = 0; j < 3; j++)	{
						int next_x = now_x + dc[j];
						int next_y = now_y + dr[j];
						
						if (!range_check(next_y, next_x))
							continue;

						if ((abs(archer[i] - next_x) + abs(pos - next_y) <= D)) {
							if (!visit[next_y][next_x]) {
								q.push({ next_x, next_y });
							}
						}
					}
				}
			}

			// 공격 표시된 적들 제거
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (check[i][j]) {
						map_copy[i][j] = 0;
					}
				}
			}

			pos--; // 궁수들 한칸 위로 이동
		}

		// 공격당한 적들 카운트
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (check[i][j]) {
					kill++;
				}
			}
		}

		answer = max(answer, kill);
}


int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> M >> D;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			cin >> map[i][j];
		}
	}

	for (int i = 1; i <= M; i++) {
		for (int j = i + 1; j <= M; j++) {
			for (int k = j + 1; k <= M; k++) {
				// 함수
				BFS(i, j, k);
			}
		}
	}		


	cout << answer << '\n';

	// 정상 종료
	return 0;
}
