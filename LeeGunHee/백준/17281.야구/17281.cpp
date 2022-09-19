#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// 이닝 숫자
int N;
int flag = 0;
int answer = 0;
int arr[50][9];

int main() {
	ios::sync_with_stdio(NULL);
	cin.tie(NULL);

	vector<int> v = { 1, 2, 3, 4, 5, 6, 7, 8 };

	cin >> N;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < 9; j++) {
			cin >> arr[i][j];
		}
	}

	// 이닝 돈다
	do {
		vector<int> order = v;
		order.insert(order.begin() + 3, 0);
		int score = 0, flag = 0;


		// 이닝 돈다
		for (int i = 0; i < N; i++) {
			// 아웃, 출루 저장
			vector<bool> ground(3);
			int out = 0;

			while (out < 3) {
				int cur = arr[i][order[flag]];

				if (cur == 0) {
					out++;
				}

				else if (cur < 4) {
					for (int sc_check = 2; sc_check >= 0; sc_check--) {
						if (ground[sc_check] == 1) {
							int next = sc_check + cur;

							if (next > 2) {
								score++;
							}
							else {
								ground[next] = 1;
							}
							ground[sc_check] = 0;

						}
					}
					ground[cur - 1] = 1;
				}

				else {
					for (int sc_check = 2; sc_check >= 0; sc_check--) {
						score += ground[sc_check];
						ground[sc_check] = 0;
					}
					score++;
				}
				flag = (flag + 1) % 9;
			}

		}
		answer = max(answer, score);
	} while (next_permutation(v.begin(), v.end()));

	cout << answer;

	return 0;
}
