#include <stdio.h>
#include <string.h>


int t, n;
int dy[] = { 1, -1, 0, 0 };
int dx[] = { 0, 0, -1, 1 };
typedef struct st {
	int y, x, dir, K;
}AT;
AT at[1002];

int map[4001+10][4001+10];

int solve() {
	int ret = 0;
	int time = 4001;
	int num = 0;

	memset(map, 0, sizeof(map));

	while (time--) {
		num = 0;
		for (int i = 1; i <= n; i++) {
			
			if (at[i].y > 4000 || at[i].y < 0 || at[i].x >4000 || at[i].x < 0) 
				continue;
		
			map[at[i].y][at[i].x] = 0;

			if (at[i].K == 0) 
				continue;

			
			int ny = at[i].y + dy[at[i].dir];
			int nx = at[i].x + dx[at[i].dir];
		
			if (ny > 4000 || ny < 0 || nx>4000 || nx < 0) {
				at[i].y = ny;
				at[i].x = nx;
				continue;
			}
			
			num++;

			
			if (map[ny][nx]) {		
				ret+=at[map[ny][nx]].K;				
				ret += at[i].K;

				at[map[ny][nx]].K = 0;			
				at[i].K = 0;
				
				at[i].y = ny;
				at[i].x = nx;
			}
			else {
				map[ny][nx] = i;

				at[i].y = ny;
				at[i].x = nx;
			}
			
		}
		if (num <= 1) break;
	}
	return ret;
}

int main() {
	scanf("%d", &t);
	for (int loop = 1; loop <= t; loop++) {
		scanf("%d", &n);
		for (int i = 1; i <= n; i++) {
			scanf("%d %d %d %d", &at[i].x, &at[i].y, &at[i].dir, &at[i].K);

			at[i].y += 1000;
			at[i].x += 1000;

			at[i].y *= 2;
			at[i].x *= 2;
		}
		int ans = solve();
		printf("#%d %d\n", loop, ans);
	}
	return 0;
}