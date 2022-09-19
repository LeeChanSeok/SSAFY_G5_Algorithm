#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

// �� ����
int map[201][201];

// �ַ��
int solution(int n, int s, int a, int b, vector<vector<int>> fares) {

    // �� ���� �ʱ�ȭ
    for (int i = 0; i < 201; i++) {
        for (int j = 0; j < 201; j++) {
           map[i][j] = 987654321;
            if (i == j)
                map[i][j] = 0;
        }
    }

    // �ʿ� �̵���� �ֱ�
    for (int i = 0; i < fares.size(); i++) {
        map[fares[i][0]][fares[i][1]] = fares[i][2];
        map[fares[i][1]][fares[i][0]] = fares[i][2];
    }

    // �÷��̵� �ͼ��� ���
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= n; j++) {
            for (int k = 0; k <= n; k++) {
                map[j][k] = min(map[j][k], map[j][i] + map[i][k]);
            }
        }
    }

    int answer = 987654321;

    // ��� �� Ž�� �ּҰ� Ž��
    for (int i = 0; i <= n; i++) {
        answer = min(answer, map[s][i] + map[i][a] + map[i][b]);
    }

    return answer;
}