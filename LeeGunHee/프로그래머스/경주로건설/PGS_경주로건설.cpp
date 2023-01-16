#include <string>
#include <vector>
#include <iostream>
#define MINI(A,B) A>B ? B : A

#define INF 99999999

using namespace std;

int N;
int p_x[4]={0,0,1,-1};
int p_y[4]={1,-1,0,0};
bool visit[26][26];
int dist[26][26];
int answer=INF;

void DFS(int x,int y,int r_case,int cost) {
    if(dist[x][y]!=INF && dist[x][y]<cost) {
        return;
    }
    dist[x][y]=MINI(dist[x][y],cost);
    if(cost>=answer) return;

    if(x==N-1 && y==N-1) {
        answer=MINI(answer,cost);
        return;
    }
    int next_x,next_y;
    for(int i=0;i<4;i++) {
        next_x=x+p_x[i];
        next_y=y+p_y[i];
        if(next_x<0 || next_x==N || next_y<0 || next_y==N) continue;
        if(visit[next_x][next_y]) continue;
        if(r_case!=i+1 && r_case!=5) { 
            visit[next_x][next_y]=true;
            DFS(next_x,next_y,i+1,cost+600);
            visit[next_x][next_y]=false;
        }
        else {
            visit[next_x][next_y]=true;
            DFS(next_x,next_y,i+1,cost+100);
            visit[next_x][next_y]=false;
        }
    }
}

int solution(vector<vector<int>> board) {
    N=board.size();
    for(int i=0;i<N;i++) {
        for(int j=0;j<N;j++) {
            dist[i][j]=INF;
            if(board[i][j]==1) visit[i][j]=true;
        }
    }
    visit[0][0]=true;
    DFS(0,0,5,0);
    return answer;
}
