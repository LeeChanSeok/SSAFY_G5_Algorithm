#include <iostream>
#include <vector>
#include <cstring>
 
using namespace std;
 
int n, m, k;
int r, c;
int mat[41][41];
int sticker[11][11];
int cpy_sticker[11][11];
 
 
void rotate(){    
    for(int i = 0; i < r; i++){
        for(int j = 0; j < c; j++){
            cpy_sticker[j][r - 1 - i] = sticker[i][j];
        }
    }
    
    int tmp = c;
    c = r;
    r=tmp;
    
    for(int i = 0; i < r; i++){
        for(int j = 0; j < c; j++){
            sticker[i][j] = cpy_sticker[i][j];
        }
    }
}
 
 
bool isValid(int y, int x){
    return (x>=0 && x<m) && (y>=0 && y<n);
}
 
 
bool isAvail(int i, int j){
    for(int y = 0; y < r; y++){
        for(int x = 0; x < c; x++){
            if(mat[i + y][j + x] && sticker[y][x]) 
                return false;
        }
    }
    return true;
}
 
 
void stick_to(int i, int j){ 
    for(int y=0; y<r; y++){
        for(int x=0; x<c; x++){
            if(sticker[y][x]==1)
                mat[i+y][j+x] = 1;
        }
    }
}
 
void solution(){
    
    for(int ro=0; ro<4; ro++){
        for(int i=0; i<=n-r; i++){
            for(int j=0; j<=m-c; j++){
                if(isAvail(i, j)){
                    stick_to(i, j);
                    return;
                }
            }
        }
        rotate();
    }
}
 
 
int main(void){
    ios_base::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    
    cin >> n >> m >> k;
    
    while(k--){
        cin >> r >> c;
        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                cin >> sticker[i][j];
            }
        }
        solution();
    }
    
    int answer = 0;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            if(mat[i][j]) answer++;
        }
    }
    cout << answer << endl;
 
}