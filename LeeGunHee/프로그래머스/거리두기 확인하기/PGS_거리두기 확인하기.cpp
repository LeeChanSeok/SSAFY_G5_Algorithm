#include <string>
#include <vector>
#include<cstring>
#include <iostream>
using namespace std;

bool flag;
bool visited[5][5];
int x[4] = {-1, 0, 0, 1};
int y[4] = {0, 1, -1, 0};
vector<vector<string>> place;

void DFS(int o, int i, int j, int depth) {
    visited[i][j] = true;
    depth++;
    
    for(int k = 0; k < 4; k++) {
        int i_ = i + y[k];
        int j_ = j + x[k];

        if(i_ < 0 || j_ < 0 || i_ >= 5 || j_ >= 5) {
            continue;
        }
        
        if(place[o][i_][j_] == 'X') {
            continue;
        }
        
        if(visited[i_][j_] == true) {
            continue;
        }
        
        if(place[o][i_][j_] == 'P') {
            flag = false;
        }
        
        if(place[o][i_][j_] == 'O' && depth < 2) {
            DFS(o, i_, j_, depth);
        }
    }
}
vector<int> solution(vector<vector<string>> places) {
    vector<int> answer;
    place = places;
    // 대기실 별
    for(int i = 0; i < 5; i++) {

        bool check = false;
        memset(visited,false,sizeof(visited));
        flag = true;
        
        for(int j = 0; j < 5; j++) {
            for(int k = 0; k < 5; k++) {
                if(visited[j][k] == false && place[i][j][k] == 'P') {


                    DFS(i, j, k, 0);
                    if(flag == false) {
                        check = true;
                        break;
                    }
                }
            }
        }
        if(check == true) {
            answer.push_back(0);
        }
        else {
            answer.push_back(1);
        }
    }
    return answer;
}