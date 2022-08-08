#include <cstring>
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, m, k;

int a[51][51];
int cop[51][51];
bool check[51][51];

struct g {
	int r, c, s;
};

const int dy[4] = { 0,1,0,-1 };
const int dx[4] = { 1,0,-1,0 };

int ans = -1;


void rotate(int bef, int y, int x, int d, int ran, int ini)
{
	if (check[y][x]) return;

	int temp = cop[y][x];
	cop[y][x] = bef;
	int ny = y + dy[d];
	int nx = x + dx[d];
	bef = temp;

	check[y][x] = true;

	if (ran == 0) {
		int ny = y + dy[d + 1];
		int nx = x + dx[d + 1];
		return rotate(bef, ny, nx, d + 1, ini - 1, ini);
	}

	return rotate(bef, ny, nx, d, ran - 1, ini);
}

void solve(vector<int> order, vector<g> v)
{
	for (int i = 0; i < order.size(); ++i)
	{
		int r = v[order[i]].r;
		int c = v[order[i]].c;
		int s = v[order[i]].s;

		for (int j = -s; j < 0; ++j) {
			memset(check, 0, sizeof(check));
			rotate(cop[r + j + 1][c + j], r + j, c + j, 0, 2 * (-j), 2 * (-j));
		}
		/*cout << endl;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j)
				cout << cop[i][j] << " ";
			cout << "\n";
		}*/
	}

	int ret = -1;
	// 최소값 검사
	for (int i = 0; i < n; ++i) {
		int sum = 0;
		for (int j = 0; j < m; ++j)
		{
			sum += cop[i][j];
		}
		if (ret == -1 || ret > sum)
			ret = sum;
	}
	if (ans == -1 || ans > ret)
		ans = ret;

	return;

}


void init()
{
	for (int i = 0; i < n; ++i)
		for (int j = 0; j < m; ++j)
			cop[i][j] = a[i][j];
}


int main()
{
	cin >> n >> m >> k;

	for (int i = 0; i < n; ++i)
		for (int j = 0; j < m; ++j) {
			cin >> a[i][j];
		}

	vector<g> v(k);

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

	cout << ans << "\n";
	return 0;
}