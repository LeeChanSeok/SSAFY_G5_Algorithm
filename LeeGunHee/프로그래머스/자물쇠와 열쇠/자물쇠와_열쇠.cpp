#include <iostream>
#include <algorithm>
#include <string>
#include <vector>

using namespace std;
 
int keysize, locksize, boardsize;
 
void rotateKey(vector<vector<int>> &key) {        
    vector<vector<int>> tmp(keysize, vector<int>(keysize));
 
    for (int j = keysize - 1; j >= 0; j--) {
        for (int i = 0; i < keysize; i++) {
            tmp[i][j] = key[keysize - j - 1][i];
        }
    }
 
    key = tmp;
}
 
 
bool putKey(int x, int y, vector<vector<int>> key, vector<vector<int>> board) {
    for (int i = x; i < x + keysize; i++) {
        for (int j = y; j < y + keysize; j++) {
            board[i][j] += key[i - x][j - y];
        }
    }
 
    for (int i = keysize - 1; i <= boardsize - keysize; i++) {
        for (int j = keysize - 1; j <= boardsize - keysize; j++) {
            if (board[i][j] == 1) continue;
 
            return false;
        }
    } 
    return true;
}
 
bool solution(vector<vector<int>> key, vector<vector<int>> lock) {
    bool answer = false;
 
    keysize = key.size();
    locksize = lock.size();
 
    boardsize = locksize + (keysize - 1) * 2;
    vector<vector<int>> board(boardsize, vector<int>(boardsize, 0));

    for (int i = keysize - 1; i <= boardsize - keysize; i++) {
        for (int j = keysize - 1; j <= boardsize - keysize; j++) {
            board[i][j] = lock[i - keysize + 1][j - keysize + 1];
        }
    }
 
    for (int r = 0; r < 4; r++) {        
        for (int i = 0; i <= boardsize - keysize; i++) {
            for (int j = 0; j <= boardsize - keysize; j++) { 
                //i,j 를 key의 시작칸으로 자물쇠 홈에 맞춰본다.
                if (putKey(i, j, key, board)) {
                    answer = true;
                    return answer;
                } 
            }
        } 
 
        rotateKey(key); 
    } 
 
    return answer;
}
