#include <iostream>
#include <algorithm>
#include <vector>
#include <tuple>

using namespace std;

int N, C, S, sum = 0;

// color sum, size sum
int color_sum[200001] = { 0, };
int size_sum[200001] = { 0, };
int answer[200001] = { 0, };

// ball vector
vector<tuple<int, int, int>> ball;

// compare func def
bool compare(tuple<int, int, int> a, tuple<int, int, int> b);

int main() {

	// ball
	cin >> N;

	// ball size color input
	for (int i = 0; i < N; i++) {
		cin >> C >> S;
		ball.push_back(make_tuple( S, C, i ));
	}

	// ball vector sort
	sort(ball.begin(), ball.end(), compare);

	// solve
	for (int i = 0; i < ball.size(); i++) {
		// 이중반복문으로 했을 경우 시간복잡도가 터짐 O(n^2) :: n = 200,000
		// DP느낌의 점화식을 사용해야함
		// 사이즈 기준으로 내림차순 정렬 후 반복문 도는 동안 전체합, 컬러합, 사이즈합을 미리 구함
		// 전체합 - 컬러합 - 사이즈합 + 본인 사이즈하면 답이 나옴
		
		// 만약 앞의 거랑 완전 같다면
		if (i != 0 && get<1>(ball[i - 1]) == get<1>(ball[i]) && get<0>(ball[i - 1]) == get<0>(ball[i])) {
			answer[get<2>(ball[i])] = answer[get<2>(ball[i - 1])];
			continue;
		}

		// color 배열에 같은 color의 합을 구해놓음
		color_sum[get<1>(ball[i])] += get<0>(ball[i]);
		// size 배열에 같은 color의 합을 구해놓음
		size_sum[get<0>(ball[i])] += get<0>(ball[i]);
		// 전체 합 구하기
		sum += get<0>(ball[i]);

		answer[get<2>(ball[i])] = sum - color_sum[get<1>(ball[i])] - size_sum[get<0>(ball[i])] + get<0>(ball[i]);
	}

	// print
	for (int i = 0; i < N; i++) {
		cout << answer[i] << '\n';
	}

	return 0;
}

bool compare(tuple<int, int, int> a, tuple<int, int, int> b) {

	if (get<0>(a) == get<0>(b))
		return get<1>(a) < get<1>(b);

	return get<0>(a) < get<0>(b);
}