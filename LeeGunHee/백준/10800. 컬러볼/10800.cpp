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
		// ���߹ݺ������� ���� ��� �ð����⵵�� ���� O(n^2) :: n = 200,000
		// DP������ ��ȭ���� ����ؾ���
		// ������ �������� �������� ���� �� �ݺ��� ���� ���� ��ü��, �÷���, ���������� �̸� ����
		// ��ü�� - �÷��� - �������� + ���� �������ϸ� ���� ����
		
		// ���� ���� �Ŷ� ���� ���ٸ�
		if (i != 0 && get<1>(ball[i - 1]) == get<1>(ball[i]) && get<0>(ball[i - 1]) == get<0>(ball[i])) {
			answer[get<2>(ball[i])] = answer[get<2>(ball[i - 1])];
			continue;
		}

		// color �迭�� ���� color�� ���� ���س���
		color_sum[get<1>(ball[i])] += get<0>(ball[i]);
		// size �迭�� ���� color�� ���� ���س���
		size_sum[get<0>(ball[i])] += get<0>(ball[i]);
		// ��ü �� ���ϱ�
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