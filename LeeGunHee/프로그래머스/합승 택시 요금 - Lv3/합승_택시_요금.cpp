#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

// 맵 생성
int map[201][201];

// 솔루션
int solution(int n, int s, int a, int b, vector<vector<int>> fares) {

    // 맵 정보 초기화
    for (int i = 0; i < 201; i++) {
        for (int j = 0; j < 201; j++) {
           map[i][j] = 1e8;
            if (i == j)
                map[i][j] = 0;
        }
    }

    // 맵에 이동비용 넣기
    for (int i = 0; i < fares.size(); i++) {
        map[fares[i][0]][fares[i][1]] = fares[i][2];
        map[fares[i][1]][fares[i][0]] = fares[i][2];
    }

    // 플로이드 와샬을 사용
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= n; j++) {
            for (int k = 0; k <= n; k++) {
                map[j][k] = min(map[j][k], map[j][i] + map[i][k]);
            }
        }
    }

    int answer = 1e8;

    // 모든 수 탐색 최소값 탐색
    for (int i = 0; i <= n; i++) {
        answer = min(answer, map[s][i] + map[i][a] + map[i][b]);
    }

    return answer;
}
