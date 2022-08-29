#include <iostream>
#include <math.h>
#include <string.h>
using namespace std;

int N, X;
int area[20][20] = {0, };
int visit[20] = {0, };
int total_cnt = 0;

void find_route_row(){
    for(int r=0; r<N; r++){
        memset(visit, 0, sizeof(visit));
        bool check = true;
        int before = area[r][0];
        int cnt = 1;
        int c = 1;
        while(c < N){
            if(area[r][c] == before){
                cnt++;
                c++;
            }
            else if(abs(area[r][c]-before) >= 2){
                check = false;
                break;
            }
            else{
                //오르막길
                if(area[r][c] > before){
                    if(cnt < X){
                        check = false;
                        break;
                    }
                    for(int cc=c-X; cc<c; cc++){
                        if(visit[cc]){
                            check = false;
                            break;
                        }
                        visit[cc] = 1;
                    }
                    if(!check) break;
                    before = area[r][c];
                    cnt = 1;
                    c++;
                }
                //내리막길
                else{
                    if(c+X > N){
                        check = false;
                        break;
                    }
                    for(int cc=c; cc<c+X; cc++){
                        if(area[r][cc] == area[r][c]){
                            visit[cc] = 1;
                            continue;
                        }
                        check = false;
                        break;
                    }
                    if(!check) break;
                    before = area[r][c];
                    cnt = X;
                    c++;
                }
            }
        }
        if(check) total_cnt++;
    }
}

void find_route_col(){
    for(int c=0; c<N; c++){
        memset(visit, 0, sizeof(visit));
        bool check = true;
        int before = area[0][c];
        int cnt = 1;
        int r = 1;
        while(r < N){
            if(area[r][c] == before){
                cnt++;
                r++;
            }
            else if(abs(area[r][c]-before) >= 2){
                check = false;
                break;
            }
            else{
                //오르막길
                if(area[r][c] > before){
                    if(cnt < X){
                        check = false;
                        break;
                    }
                    for(int rr=r-X; rr<r; rr++){
                        if(visit[rr]){
                            check = false;
                            break;
                        }
                        visit[rr] = 1;
                    }
                    if(!check) break;
                    before = area[r][c];
                    cnt = 1;
                    r++;
                }
                //내리막길
                else{
                    if(r+X > N){
                        check = false;
                        break;
                    }
                    for(int rr=r; rr<r+X; rr++){
                        if(area[rr][c] == area[r][c]){
                            visit[rr] = 1;
                            continue;
                        }
                        check = false;
                        break;
                    }
                    if(!check) break;
                    before = area[r][c];
                    cnt = X;
                    r++;
                }
            }
        }
        if(check) total_cnt++;
    }
}

int main(int argc, const char * argv[]) {
    int test_case;
    int T;
    cin >> T;
    for(test_case=1; test_case<=T; ++test_case){
        total_cnt = 0;
        cin >> N >> X;
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                cin >> area[r][c];
            }
        }
        find_route_row();
        find_route_col();
        cout << "#" << test_case << " " << total_cnt << endl;
    }
    return 0;
}