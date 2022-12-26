#include<iostream>
#include<vector>
#include<string>

using namespace std;
 
int T, n;
 
void clockwise(vector<vector<char>>&a) {
    char temp = a[0][0];
    a[0][0] = a[2][0];
    a[2][0] = a[2][2];
    a[2][2] = a[0][2];
    a[0][2] = temp;
 
    temp = a[0][1];
    a[0][1] = a[1][0];
    a[1][0] = a[2][1];
    a[2][1] = a[1][2];
    a[1][2] = temp;

    return;
}
 
void U(vector<vector<vector<char>>>&a) {
    vector<vector<char>> temp = a[5];
    a[5] = a[0];
    a[0] = a[4];
    a[4] = a[1];
    a[1] = temp;
 
    clockwise(a[1]); clockwise(a[1]);
    clockwise(a[4]); clockwise(a[4]);
    clockwise(a[2]);
    clockwise(a[3]); clockwise(a[3]); clockwise(a[3]);

    return;
}
 
void F(vector<vector<vector<char>>>&a) {
    vector<vector<char>> temp = a[2];
    a[2] = a[5];
    a[5] = a[3];
    a[3] = a[4];
    a[4] = temp;
 
    clockwise(a[2]);
    clockwise(a[4]);
    clockwise(a[3]);
    clockwise(a[5]);
    clockwise(a[1]); clockwise(a[1]); clockwise(a[1]);
    clockwise(a[0]);

    return;
}
 
void L(vector<vector<vector<char>>>&a) {
    // 0 -> 2 -> 1 -> 3
    vector<vector<char>> temp = a[0];
    for (int i = 0; i < 3; i++) {
        a[0][i][0] = a[3][i][0];
        a[3][i][0] = a[1][i][0];
        a[1][i][0] = a[2][i][0];
        a[2][i][0] = temp[i][0];
    }
    clockwise(a[4]);

    return;
}
void R(vector<vector<vector<char>>>&a)
{
    // 0 -> 3 -> 1 -> 2
    vector<vector<char>> temp = a[0];
    for (int i = 0; i < 3; i++) {
        a[0][i][2] = a[2][i][2];
        a[2][i][2] = a[1][i][2];
        a[1][i][2] = a[3][i][2];
        a[3][i][2] = temp[i][2];
    }
    clockwise(a[5]);

    return;
}

int main(void) {
    cin >> T;
    string str;
 
    while (T--) {
        vector<vector<vector<char>>> cube;

        cube.push_back(vector<vector<char>>(3, vector<char>(3, 'w')));
        cube.push_back(vector<vector<char>>(3, vector<char>(3, 'y')));
        cube.push_back(vector<vector<char>>(3, vector<char>(3, 'r')));
        cube.push_back(vector<vector<char>>(3, vector<char>(3, 'o')));
        cube.push_back(vector<vector<char>>(3, vector<char>(3, 'g')));
        cube.push_back(vector<vector<char>>(3, vector<char>(3, 'b')));
 
        cin >> n;

        for (int i = 0; i < n; i++) {
            cin >> str;

            int k = 1;

            if (str[1] == '-') 
		k = 3;
 
            if (str[0] == 'U') {
                U(cube);

                for (int j = 0; j < k; j++) 
		R(cube);
                for (int j = 0; j < 3; j++) 
		U(cube);
            }

            else if (str[0] == 'D') {
                U(cube);

                for (int j = 0; j < k; j++) 
		L(cube);
                for (int j = 0; j < 3; j++) 
		U(cube);
            }

            else if (str[0] == 'F') {
                F(cube);

                for (int j = 0; j < k; j++) 
		L(cube);
                for (int j = 0; j < 3; j++) 
		F(cube);
            }

            else if (str[0] == 'B')
            {
                F(cube);

                for (int j = 0; j < k; j++) 
		R(cube);
                for (int j = 0; j < 3; j++) 
		F(cube);
            }

            else if (str[0] == 'L') {
                for(int j = 0; j < k; j++) L(cube);
            }

            else if (str[0] == 'R') {
                for (int j = 0; j < k; j++) 
		R(cube);
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                cout << cube[0][i][j];

            cout << '\n';
        }
    }

    return 0;
}
