#include<iostream>
#include<queue>
#include<vector>
using namespace std;

struct cell{
    int x;
    int y;
    int power;
    int timer;
};

struct greater_p{
    bool operator()(cell c1, cell c2){
        return c1.power < c2.power;
    }
};

int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};

int main(int argc, char** argv) {
    int test_case;
    int T;
    int N, M, K;
    int num_dead, num_alive;
    vector<cell> cells;

    cin>>T;
    for(test_case = 1; test_case <= T; ++test_case) {
        int cont[400][400] = {0,};
        priority_queue<cell, vector<cell>, greater_p> active_cell;
        cin >> N >> M >> K;
        cells.clear();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                int c_power;
                cin >> c_power;
                cont[200+i][200+j] = c_power;
                if(c_power > 0){
                    cells.push_back({200+j, 200+i, c_power, c_power});
                }
            }
        }
        
        num_alive = cells.size();
        num_dead = 0;
        int time = 0;
        while(time < K){
            time++;
            for(int i=0; i<num_alive; i++){
                cells[i].timer--;
                if(cells[i].timer == -1) active_cell.push(cells[i]);
                if(cells[i].timer == -cells[i].power) num_dead++;
            }
            while(!active_cell.empty()){
                cell now_cell = active_cell.top();
                for(int i=0; i<4; i++){
                    int nx = now_cell.x + dx[i];
                    int ny = now_cell.y + dy[i];
                    if(cont[ny][nx] == 0){
                        num_alive ++;
                        cont[ny][nx] = now_cell.power;
                        cells.push_back({nx, ny, now_cell.power, now_cell.power});
                    }
                }
                active_cell.pop();
            }
        }
        cout << "#" << test_case << " " << num_alive - num_dead << endl;
    }
    return 0;
}