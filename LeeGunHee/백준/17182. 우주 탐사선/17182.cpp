#include <iostream>
#include <algorithm>

using namespace std;

int node, start, ans = 987654321;
int arr[10][10];
bool visit[10] = { false, };

// DFS ����
void dfs(int idx, int dist, int planet) {
	// �̹� ���亸�� �Ÿ��� Ŀ�� �� �ʿ� ����
	if (ans < dist)
		return;

	// �༺ �湮�� ���߾��
	if (planet == node) {
		ans = min(ans, dist);
		return;
	}
	for (int i = 0; i < node; i++) {
		if (visit[i]) continue;
		visit[i] = true;
		dfs(i, dist + arr[idx][i], planet + 1);
		visit[i] = false;
	}
}

int main() {
	ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	
	// �Է�
	cin >> node >> start;
	for (int i = 0; i < node; i++)
		for (int j = 0; j < node; j++)
			cin >> arr[i][j];
	
	// DFS ó���� ���� ����
	visit[start] = true;

	//�÷��̵� �ͼ�
	for (int k = 0; k < node; k++)
		for (int i = 0; i < node; i++)
			for (int j = 0; j < node; j++)
				if (arr[i][j] > arr[i][k] + arr[k][j])
					arr[i][j] = arr[i][k] + arr[k][j];
	
	//DFS ����
	dfs(start, 0, 1);

	//���� ���
	cout << ans;

	return 0;
}