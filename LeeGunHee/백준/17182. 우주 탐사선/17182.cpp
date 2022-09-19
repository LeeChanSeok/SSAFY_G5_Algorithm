#include <iostream>
#include <algorithm>

using namespace std;

int node, start, ans = 987654321;
int arr[10][10];
bool visit[10] = { false, };

// DFS 실행
void dfs(int idx, int dist, int planet) {
	// 이미 정답보다 거리가 커서 돌 필요 없음
	if (ans < dist)
		return;

	// 행성 방문을 다했어요
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
	ios_base::sync_with_stdio(false); 
	cin.tie(NULL); 
	cout.tie(NULL);
	
	// 입력
	cin >> node >> start;
	for (int i = 0; i < node; i++)
		for (int j = 0; j < node; j++)
			cin >> arr[i][j];
	
	// DFS 처음에 돌기 전에
	visit[start] = true;

	//플로이드 와샬
	for (int k = 0; k < node; k++)
		for (int i = 0; i < node; i++)
			for (int j = 0; j < node; j++)
				if (arr[i][j] > arr[i][k] + arr[k][j])
					arr[i][j] = arr[i][k] + arr[k][j];
	
	//DFS 실행
	dfs(start, 0, 1);

	//정답 출력
	cout << ans;

	return 0;
}
