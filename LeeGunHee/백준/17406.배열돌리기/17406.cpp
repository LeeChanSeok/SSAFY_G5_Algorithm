#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>

using namespace std;

int n, m, k;

def struct {
	int r, c, s;
}rotate;

int dy[4] = { 0,1,0,-1 };
int dx[4] = { 1,0,-1,0 };

int arr[51][51];
int copy_arr[51][51];
bool check[51][51];

int answer = -987654321;

void init() {
	for (int i = 0; i < n; ++i)
		for (int j = 0; j < m; ++j)
			copy[i][j] = arr[i][j];
}

void solve(vector<int> order, vector<rotate> v) {
	for (int i = 0; i < order.size(); ++i)
	{
		int r = v[order[i]].r;
		int c = v[order[i]].c;
		int s = v[order[i]].s;

		for (int j = -s; j < 0; ++j) {
			memset(check, 0, sizeof(check));
			rotate(copy[r + j + 1][c + j], r + j, c + j, 0, 2 * (-j), 2 * (-j));
		}
	}

	int ret = -1;

	for (int i = 0; i < n; ++i) {
		int sum = 0;
		for (int j = 0; j < m; ++j)
		{
			sum += copy[i][j];
		}
		if (ret == -1 || ret > sum)
			ret = sum;
	}
	if (answer == -1 || answer > ret)
		answer = ret;

	return;

}

void rotate(int before, int y, int x, int d, int nxt, int ini) {
	if (check[y][x]) return;

	int temp = copy[y][x];
	copy[y][x] = before;
	int ny = y + dy[d];
	int nx = x + dx[d];
	before = temp;

	check[y][x] = true;

	if (nxt == 0) {
		int ny = y + dy[d + 1];
		int nx = x + dx[d + 1];
		return rotate(before, ny, nx, d + 1, ini - 1, ini);
	}

	return rotate(before, ny, nx, d, nxt - 1, ini);
}

int main()
{
	cin >> n >> m >> k;

	for (int i = 0; i < n; ++i)
		for (int j = 0; j < m; ++j) {
			cin >> arr[i][j];
		}

	vector<rotate> v(k);

	for (int i = 0; i < k; ++i) {
		int r, c, s;
		cin >> r >> c >> s;

		r -= 1; c -= 1;

		v[i] = { r,c,s };
	}

	vector<int> order(k);
	for (int i = 0; i < k; ++i)
		order[i] = i;

	do {

		init();
		solve(order, v);

	} while (next_permutation(order.begin(), order.end()));

	cout << answer << "\n";
	return 0;
}
