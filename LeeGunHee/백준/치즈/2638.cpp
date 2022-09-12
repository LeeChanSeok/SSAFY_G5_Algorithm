#include <iostream>
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

// row col
int r, c;

// arr
int map[101][101];
bool visit[101][101];

// direction
int dx[] = {0, 0, -1, 1};
int dy[] = {1, -1, 0, 0};

// count, area
int cnt, area;

void bfs(){
    queue<pair<int, int>> q;
    queue<pair<int, int>> temp;
    q.push(make_pair(0, 0));
   
    while(!q.empty()){
        area = q.size(); 

        while(!q.empty()){
            int x = q.front().first;
            int y = q.front().second;

            q.pop();

            for (int i = 0; i < 4;i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
 
                if (nx >= 0 && nx < c && ny >=0 && ny <r){
                    if(!visit[ny][nx] && map[ny][nx] == 1){
                        temp.push(make_pair(nx, ny)); 
                    }
                    else if(!visit[ny][nx] && map[ny][nx] == 0){
                        q.push(make_pair(nx, ny));
                    }

                    visit[ny][nx] = true;
                }
            }
        }

        while(!temp.empty()) {
            q.push(temp.front());
            temp.pop();
        }
        cnt++;
    }   
}

int main(){
    cin >> r >> c;
    for (int i = 0; i < r;i++){
        for (int j = 0; j < c;j++){
            cin >> map[i][j];
        }
    }

    visit[0][0] = true;

    bfs();

    cout << cnt-1 << '\n'; 
    cout << area << '\n';

    return 0;
}
