#include <string>
#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

// std:: 접두사
using namespace std;

// 수식 길이, 문자열 받기, 정답
int N;
string str;
int answer = INT_MIN;

// Cal 
int Cal(int a, int b, char op) {
	switch (op) {
	case '+':
		return a + b;
	case '-':
		return a - b;
	case '*':
		return a * b;
	}
}

// DFS
void DFS(int index, int num) {

	// cout << "DFS execute" << '\n';
	// 수식 길이만큼 돌면 탈출
	if (N - 1 < index) {
		answer = max(answer, num);
		return;
	}

	// 이전 괄호와 다음 괄호를 계산하기 위함
	char op = (index == 0) ? '+' : str[index - 1];

	// 괄호 묶을 경우 -> 괄호 연산3 연산1 = 4
	if (index + 2 < N) {
		int temp = Cal(str[index] - '0', str[index + 2] - '0', str[index + 1]);
		DFS(index + 4, Cal(num, temp, op));
	}

	// 괄호 안 묶을 경우 -> 숫자1 연산1 = 2
	DFS(index + 2, Cal(num, str[index] - '0', op));
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N;
	cin >> str;

	DFS(0, 0);

	cout << answer << '\n';

	// 정상 종료
	return 0;
}