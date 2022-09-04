#include <iostream>
#include <queue>
using namespace std;
 
 
int n, w, h, ans;
 
int dx[] = { 0,0,1,-1 };
int dy[] = { 1,-1,0,0 };
 
int count(int newmap[15][12]) {
    int cnt = 0;
    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {

            if (newmap[i][j] != 0) cnt++;
        }
    }
 
    return cnt;
}

void move(int newmap[15][12]) {
    for (int j = 0; j < w; j++) {
        bool flag = false;
        for (int i = h - 1; i >= 0; i--) {
            if (newmap[i][j] != 0) continue;

            for (int k = i - 1; k >= 0; k--) {
                if (newmap[k][j] == 0) continue;
 
                newmap[i][j] = newmap[k][j];
                newmap[k][j] = 0;
                flag = true;
                break;
            }

            if (!flag) break;
 
        }
    }
}
 

void go(int x, int y, int newmap[15][12]) {
 
    queue<pair<int, int>> q;
    q.push(make_pair(x, y));
    int num;
    while (!q.empty()) {
        x = q.front().first;
        y = q.front().second;
        q.pop();
        num = newmap[x][y];
 
        newmap[x][y] = 0;
 
        if (num == 1) continue;

        for (int k = 0; k < 4; k++) {
            int nx = x;
            int ny = y;
            for (int l = 0; l < num - 1; l++) {
                nx += dx[k];
                ny += dy[k];
                
                if (nx < 0 || ny < 0 || nx >= h || ny >= w) break;
 
                if (newmap[nx][ny] > 1) {
                    q.push(make_pair(nx, ny));
                }
                else {
                    newmap[nx][ny] = 0;
                }
 
            }
 
        }
    }
}
 
 
void mapcpy(int map[15][12], int newmap[15][12]) {
    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            newmap[i][j] = map[i][j];
        }
    }
}
 
 
void solve(int index, int map[15][12]) {
    if (index == n) {
        int tmp = count(map);
        if (ans > tmp) ans = tmp;
 
        return;
    }
 
 
    int x, y;

    for (int col = 0; col < w; col++) {
        x = 0;
        y = col;
 
        while (true) {
            if (x >= h) {
                break;
            }
 
            if (map[x][y] != 0) {
                break;
            }

            x++;
        }

        if (x == h) continue;
 
        int newmap[15][12];
        mapcpy(map, newmap);
 

        go(x, y, newmap);

        move(newmap);
 
        solve(index + 1, newmap);
    }
 
}
 
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
 
    int T;
    cin >> T;
    for (int tc = 1; tc <= T; tc++) {
        cin >> n >> w >> h;
        ans = 200;
 
        int map[15][12];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                cin >> map[i][j];
            }
        }
 
        solve(0,map);
 
        if (ans == 200) {
            cout << '#' << tc << ' ' << 0 << '\n';
        }
        else {
            cout << '#' << tc << ' ' << ans << '\n';
        }
 
    }
 
 
    return 0;
}
